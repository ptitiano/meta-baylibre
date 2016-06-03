#!/bin/bash
#
# Copyright (c) 2011-2012 Wind River Systems, Inc.
#

# Let bitbake use the following env-vars as if they were pre-set bitbake ones.
# (BBLAYERS is explicitly blocked from this within OE-Core itself, though...)
export BB_ENV_EXTRAWHITE="http_proxy MACHINE DISTRO DL_DIR"

IFS='
    '

PATH=/usr/local/bin:/bin:/usr/bin
export PATH


UMASK=022
umask $UMASK

scriptdir=$(cd $(dirname ${0}); pwd)
WS="$scriptdir/../poky"
TOPLEVEL="$scriptdir/.."

ALL_ARGS="$*"

usage()
{
    cat << EOF
Usage:
$0 <options ...>

  Global:
    -p <poky_dir>
    -o <meta-openembedded dir>
    -l <meta layer>
    -x <linux repo directory>
    -m <machine type>
    -b <build_dir>
    -t <number of bitbake tasks>
    -j <number of make threads>
    -r (enable preempt-rt kernel <Test-only. Not supported>)
    -a (pass extra options for recipes, key=value separated by ::)
    -i <imagename>

  Task control:
    -c (enable command line mode)
    -d (build the full debug image)
EOF
}

usage_and_exit()
{
    usage
    exit $1
}

if [ $# = 0 ]; then
    usage_and_exit 1
fi

# Default options
BD="$scriptdir/../build"
MACH=beaglebone-acme
DEBUG=false
TASKS=4
THREADS=4
CMD_LINE=false
TOOLCHAIN=false
DISTRO=acme-baylibre
X_OPTS=

while getopts ":p:o:b:l:x:i:m:t:j:w:v:a:F:cdrqsgkh" arg
do
    case $arg in
    p)
        WS=$(readlink -f $OPTARG)
        echo "Poky dir: $WS"
        ;;
    o)
        OE=$(readlink -f $OPTARG)
        echo "OE meta: $OE"
        ;;
    b)
        BD=$(readlink -f $OPTARG)
        echo "Build dir: $BD"
        ;;
    l)
        SWI=$(readlink -f $OPTARG)
        echo "SWI meta dir: $SWI"
        ;;
    x)
        LINUXDIR=$(readlink -f $OPTARG)
        echo "Linux repo dir: $LINUXDIR"
        ;;
    i)
	IMAGENAME=$OPTARG
	echo "IAMGE NAME: $IMAGENAME"
	;;
    m)
        MACH=$OPTARG
        echo "SWI machine: $MACH"
        ;;
    d)
        DEBUG=true
        echo "Enable more packages for debugging"
        ;;
    t)
        TASKS=$OPTARG
        echo "Number of bitbake tasks $TASKS"
        ;;
    j)
        THREADS=$OPTARG
        echo "Number of make threads $THREADS"
        ;;
    a)
        X_OPTS="$X_OPTS $OPTARG"
        echo "Extra options added -  $OPTARG"
        ;;
    c)  CMD_LINE=true
        echo "Enable command line mode"
        ;;
    w)
        WK=$(readlink -f $OPTARG)
        ;;
    ?)
        echo "$0: invalid option -$OPTARG" 1>&2
        usage_and_exit 1
        ;;
    esac
done

. ${WS}/oe-init-build-env $BD

## Conf: bblayers.conf

enable_layer()
{
    LAYER_NAME=$1
    LAYER_PATH=$2
    PREVIOUS_LAYER=$3

    if [ -z "$PREVIOUS_LAYER" ]; then
        PREVIOUS_LAYER='meta-yocto-bsp'
    fi

    echo "+ layer: $LAYER_NAME"

    grep -E "/$LAYER_NAME " $BD/conf/bblayers.conf > /dev/null
    if [ $? != 0 ]; then
        sed -e '/'"$PREVIOUS_LAYER"'/a\  '"$LAYER_PATH"' \\' -i $BD/conf/bblayers.conf
    fi
}

## SYSTEMD MIGRATION ##
#
enable_layer "meta-baylibre" "$TOPLEVEL/meta-baylibre"

if test $MACH = "acme-virt-arm" || test $MACH = "acme-virt-x86"; then
	enable_layer "meta-baylibre-virt" "$TOPLEVEL/meta-baylibre/meta-baylibre-virt"
else
	enable_layer "meta-baylibre-acme" "$TOPLEVEL/meta-baylibre/meta-baylibre-acme"
fi

# Enable the meta-oe layer
enable_layer "meta-oe" "$OE/meta-oe"

## Conf: local.conf

set_option() {
    opt_name=$1
    opt_val=$2

    if grep "$opt_name =" $BD/conf/local.conf > /dev/null; then
        # Update entry
        echo "Updating $opt_name to $opt_val"
        sed -e "s/$opt_name = \".*//g" -i $BD/conf/local.conf
        echo "$opt_name = \"$opt_val\"" >> $BD/conf/local.conf
    else
        # Append
        echo "Adding option $opt_name with value $opt_val"
        echo "$opt_name = \"$opt_val\"" >> $BD/conf/local.conf
    fi
}

# Tune local.conf file
sed -e 's:^\(MACHINE\).*:\1 = \"'$MACH'\":' -i $BD/conf/local.conf
grep -E "SOURCE_MIRROR_URL" $BD/conf/local.conf > /dev/null
if [ $? != 0 ]; then
        sed -e '/^#DL_DIR/a\SOURCE_MIRROR_URL ?= \"file\:\/\/'"$scriptdir"'/../downloads\"\nINHERIT += \"own-mirrors\"\nBB_GENERATE_MIRROR_TARBALLS = \"1\"\nBB_NO_NETWORK = \"0\"\nWORKSPACE = \"'"${WORKSPACE}"'\"\nLINUX_REPO_DIR = \"'"${LINUXDIR}"'\"\nDISTRO = \"'"${DISTRO}"'\"' -i $BD/conf/local.conf
fi
sed -e 's:^#\(BB_NUMBER_THREADS\).*:\1 = \"'"$TASKS"'\":' -i $BD/conf/local.conf
sed -e 's:^#\(PARALLEL_MAKE\).*:\1 = \"-j '"$THREADS"'\":' -i $BD/conf/local.conf

# Add or update extra options
if [ -n "$X_OPTS" ]
then
    EXTRA_OPTS=$(echo $X_OPTS | sed -e "s/\:\:/ /g")
    for OPT in $EXTRA_OPTS
    do
        opt_name=$(echo $OPT | cut -d"=" -f1)
        opt_val=$(echo $OPT | cut -d"=" -f2-)

        set_option $opt_name $opt_val
    done
fi

# Initramfs
#if test $MACH = "beaglebone"; then
#    set_option 'INITRAMFS_IMAGE_BUNDLE' '1'
#    set_option 'INITRAMFS_IMAGE' "acme-image-initramfs"
#fi

cd $BD

# Command line
if [ $CMD_LINE = true ]; then
    /bin/bash
    exit $?
fi

# Images
echo -n "Build image of "
if [ $DEBUG = true ]; then
    echo "dev rootfs (for $MACH)."
    sed -e 's:^\(PACKAGE_CLASSES\).*:\1 = \"package_rpm\":' -i $BD/conf/local.conf
    if test $MACH = "acme-virt-arm" || test $MACH = "acme-virt-x86"; then
        bitbake acme-virt-image-dev
    else
        bitbake baylibre-acme-image-dev
    fi
else
    echo "baylibre-acme-image (for $MACH)."
    sed -e 's:^\(PACKAGE_CLASSES\).*:\1 = \"package_ipk\":' -i $BD/conf/local.conf
    if test $MACH = "acme-virt-arm" || test $MACH = "acme-virt-x86"; then
        echo "No minimal image for $MACH."
        exit 1
    else
        bitbake $IMAGENAME
    fi
fi


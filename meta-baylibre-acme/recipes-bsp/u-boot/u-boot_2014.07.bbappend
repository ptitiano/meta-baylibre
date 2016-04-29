FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "file://uEnv.txt"

## Default suffix is .txt"
#
UBOOT_ENV = "uEnv"

do_deploy_append() {
    cp ${DEPLOYDIR}/zImage-am335x-boneblack.dtb am335x-boneblack.dtb
}


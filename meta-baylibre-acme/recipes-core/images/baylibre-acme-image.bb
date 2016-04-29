inherit core-image
inherit mksdcard

INC_PR = "r0"

IMAGE_FEATURES += "dev-pkgs"

DEPENDS += "linux-yocto-mainline"

FSTYPE_VIRT ?= "ext3"

IMAGE_INSTALL += "util-linux"
IMAGE_INSTALL += "util-linux-blkid"
IMAGE_INSTALL += "util-linux-mount"
IMAGE_INSTALL += "acme-utils"

#do_prepare_sdcard() {
#    echo "Staging: TODO PREPARE SDCARD"
#}
#
#addtask prepare_sdcard after do_rootfs before do_build

inherit core-image
inherit mksdcard

INC_PR = "r0"

IMAGE_FEATURES += "dev-pkgs"

DEPENDS_beaglebone_acme += "linux-yocto-mainline"

FSTYPE_VIRT ?= "ext3"

USBGADGET_IMAGE_INSTALL = "${@base_contains("MACHINE", "raspberrypi0", "acme-usbgadget-init libusbg", "",d)} \
			   ${@base_contains("MACHINE", "beaglebone-acme", "acme-usbgadget-init libusbg", "",d)} \
"

IMAGE_INSTALL += "util-linux"
IMAGE_INSTALL += "util-linux-blkid"
IMAGE_INSTALL += "util-linux-mount"
IMAGE_INSTALL += "tree"

IMAGE_INSTALL += "acme-utils"
IMAGE_INSTALL += "acme-iio-init"
IMAGE_INSTALL += "i2c-tools"
IMAGE_INSTALL += "libiio"

IMAGE_INSTALL += "${USBGADGET_IMAGE_INSTALL}"

IMAGE_INSTALL += "sigrok-cli"

IMAGE_INSTALL += "systemd-analyze"

PREFERRED_VERSION_linux-raspberrypi ?= "4.7.%"

IMAGE_BOOT_FILES_beaglebone_acme += "zImage zImage-am335x-boneblack.dtb uEnv.txt"

# linux-yocto-custom.bb:
#
#   An example kernel recipe that uses the linux-yocto and oe-core
#   kernel classes to apply a subset of yocto kernel management to git
#   managed kernel repositories.
#
#   To use linux-yocto-custom in your layer, copy this recipe (optionally
#   rename it as well) and modify it appropriately for your machine. i.e.:
#
#     COMPATIBLE_MACHINE_yourmachine = "yourmachine"
#
#   You must also provide a Linux kernel configuration. The most direct
#   method is to copy your .config to files/defconfig in your layer,
#   in the same directory as the copy (and rename) of this recipe and
#   add file://defconfig to your SRC_URI.
#
#   To use the yocto kernel tooling to generate a BSP configuration
#   using modular configuration fragments, see the yocto-bsp and
#   yocto-kernel tools documentation.
#
# Warning:
#
#   Building this example without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#
#   defconfig: When a defconfig is provided, the linux-yocto configuration
#              uses the filename as a trigger to use a 'allnoconfig' baseline
#              before merging the defconfig into the build. 
#
#              If the defconfig file was created with make_savedefconfig, 
#              not all options are specified, and should be restored with their
#              defaults, not set to 'n'. To properly expand a defconfig like
#              this, specify: KCONFIG_MODE="--alldefconfig" in the kernel
#              recipe.
#   
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition (for kernel v4.x only):
#            SRC_URI += "file://0001-linux-version-tweak.patch"
#   example feature addition (for kernel v4.x only):
#            SRC_URI += "file://feature.scc"
#

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRCREV = "v4.7"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;nocheckout=1"

LINUX_VERSION = "4.7"
LINUX_VERSION_EXTENSION_append = "-mainline"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# defconfig fragments
SRC_URI += "file://defconfig \
	file://iio.cfg \
	file://0001-eeprom-at24-improve-the-device_id-table-readability.patch \
	file://0002-eeprom-at24-move-at24_read-below-at24_eeprom_write.patch \
	file://0003-eeprom-at24-coding-style-fixes.patch \
	file://0004-eeprom-at24-call-read-write-functions-via-function-p.patch \
	file://0005-eeprom-at24-hide-the-read-write-loop-behind-a-macro.patch \
	file://0006-eeprom-at24-split-at24_eeprom_read-into-specialized-.patch \
	file://0007-eeprom-at24-split-at24_eeprom_write-into-specialized.patch \
	file://0008-eeprom-at24-platform_data-use-BIT-macro.patch \
	file://0009-eeprom-at24-support-reading-the-serial-number-for-24.patch \
	file://0010-eeprom-at24-add-support-for-at24mac-series.patch \
	file://0011-eeprom-at24-tweak-the-loop_until_timeout-macro.patch \
	file://0012-eeprom-at24-check-if-the-chip-is-functional-in-probe.patch \
	file://0013-dt-acme-add-gpio-multiplexer-for-i2c1.patch \
	file://0014-dt-acme-add-cape-EEPROMs.patch \
	file://0015-dt-acme-add-probes-and-their-EEPROMs-and-serial-numb.patch \
"

# Modify SRCREV to a different commit hash in a copy of this recipe to
# build a different release of the Linux kernel.
# tag: v4.2 64291f7db5bd8150a74ad2036f1037e6a0428df2
#SRCREV_machine="64291f7db5bd8150a74ad2036f1037e6a0428df2"

PV = "${LINUX_VERSION}+git${SRCPV}"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "beaglebone"

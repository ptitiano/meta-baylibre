# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)
#
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# NOTE: multiple licenses have been detected; if that is correct you should separate
# these in the LICENSE value using & if the multiple licenses all apply, or | if there
# is a choice between the multiple licenses. If in doubt, check the accompanying
# documentation to determine which situation is applicable.
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=7c13b3376cea0ce68d2d2da0a1b3a72c \
                    file://debian/copyright;md5=1f0205b4f19a5da6f1fc6c69a2bd8d9f"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI = "git://github.com/analogdevicesinc/libiio.git;protocol=https \
	   file://iiod.service"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: Doxygen
# NOTE: the following library dependencies are unknown, ignoring: usb-1 aio serialport
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "avahi"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "iiod.service"

inherit cmake systemd

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""

do_install_append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/iiod.service ${D}${systemd_unitdir}/system
}

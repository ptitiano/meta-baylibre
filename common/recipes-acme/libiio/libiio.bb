LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=7c13b3376cea0ce68d2d2da0a1b3a72c"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI = "git://github.com/analogdevicesinc/libiio.git;protocol=https \
	   file://iiod.service"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "avahi libusb1 libxml2"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "iiod.service"

inherit cmake systemd

EXTRA_OECMAKE = ""

do_install_append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/iiod.service ${D}${systemd_unitdir}/system
}

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=7c13b3376cea0ce68d2d2da0a1b3a72c"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI = "https://github.com/analogdevicesinc/libiio/archive/${PV}.tar.gz \
	   file://iiod.service"
SRC_URI[md5sum] = "fbd6faa9c420659023072eb3c74ed037"
SRC_URI[sha256sum] = "b230cfd5ef5550cebf485950d8b1f4d5cfa7d5f0814aeb7b44e530e9caecb83a"

S = "${WORKDIR}/libiio-${PV}"

DEPENDS = "avahi libusb1 libxml2"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "iiod.service"

inherit cmake systemd

EXTRA_OECMAKE = ""

do_install_append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/iiod.service ${D}${systemd_unitdir}/system
}

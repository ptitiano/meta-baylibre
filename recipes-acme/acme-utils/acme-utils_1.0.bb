#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "ACME Utilities"
SECTION = "acme"
DEPENDS = ""
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=96af5705d6f64a88e035781ef00e98a8"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "v1.0"
SRC_URI = "https://github.com/BayLibre/acme-utils.git"
SRC_URI[md5sum] = "d9d7179a5fa9721806d6ff866bda212f"

S = "${WORKDIR}/git"

do_compile() {
	     make -C api
}

do_install() {
	     install -d ${D}${bindir}
	     install -m 0755 api/dut-* ${D}${bindir}
}


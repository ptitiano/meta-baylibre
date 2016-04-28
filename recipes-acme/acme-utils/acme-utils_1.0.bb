#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

FILESEXTRAPATHS_prepend := "${THISDIR}:"

DESCRIPTION = "ACME Utilities"
SECTION = "acme"
DEPENDS = ""
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=578ecfeb82fabd60bb6310f0bf6af799"

SRCREV = "v1.0"
SRC_URI = "git://github.com/BayLibre/acme-utils.git"
SRC_URI[md5sum] = "d9d7179a5fa9721806d6ff866bda212f"

S = "${WORKDIR}/git"

do_compile() {
	     make -C api
}

do_install() {
	     install -d ${D}${bindir}
	     install -m 0755 api/dut-* ${D}${bindir}
}


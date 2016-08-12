FILESEXTRAPATHS_prepend := "${THISDIR}:"

DESCRIPTION = "ACME Utilities"
SECTION = "acme"
DEPENDS = ""
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=578ecfeb82fabd60bb6310f0bf6af799"

SRC_URI = "git://github.com/bayLibre-acme/acme-utils.git;branch=master"
SRCREV = "master"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "python3-modules"

inherit systemd

SYSTEMD_SERVICE_${PN} = "pyacmed.service"
SYSTEMD_PACKAGES = "${PN}"

do_compile() {
	     make -C api
}

do_install() {
	     install -d ${D}${bindir}
	     install -d ${D}${systemd_unitdir}/system/
	     install -m 0755 api/dut-hard-reset ${D}${bindir}
	     install -m 0755 api/dut-switch-*   ${D}${bindir}
	     install -m 0755 api/dut-dump-probe ${D}${bindir}
	     install -m 0755 pyacmed/pyacmed    ${D}${bindir}
	     install -m 0644 pyacmed/pyacmed.service ${D}${systemd_unitdir}/system
	     sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/pyacmed.service
}

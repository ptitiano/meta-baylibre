SUMMARY = "A service type script to initialize the CAPE GPIO expanders"
DESCRIPTION = "A service type script to initialize the CAPE GPIO expanders"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "acme-cape-init.service"

SRC_URI = "file://acme-cape-init \
           file://acme-cape-init.service \
	   file://acme-cape-modules.conf"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d/
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/modules-load.d/
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/acme-cape-init ${D}${bindir}
    ln -s ${bindir}/acme-cape-init ${D}${sysconfdir}/init.d/acme-cape-init
    install -m 0644 ${WORKDIR}/acme-cape-init.service ${D}${systemd_unitdir}/system
    sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/acme-cape-init.service
    install -m 0644 ${WORKDIR}/acme-cape-modules.conf ${D}${sysconfdir}/modules-load.d/
}


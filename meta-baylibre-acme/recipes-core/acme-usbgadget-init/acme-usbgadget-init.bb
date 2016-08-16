SUMMARY = "A service type script to initialize the USB Gadget Interface"
DESCRIPTION = "A service type script to initialize USB Gedget Interface"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "acme-usbgadget-init.service"

SRC_URI = "file://acme-usbgadget-init \
           file://acme-usbgadget-init.service \
	   file://udhcpd.conf \
	   file://acme-usbgadget-modules.conf"

do_install_append() {
    install -d ${D}${sysconfdir}/
    install -d ${D}${sysconfdir}/init.d/
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/modules-load.d/
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/acme-usbgadget-init ${D}${bindir}
    ln -s ${bindir}/acme-usbgadget-init ${D}${sysconfdir}/init.d/acme-usbgadget-init
    install -m 0644 ${WORKDIR}/acme-usbgadget-init.service ${D}${systemd_unitdir}/system
    sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/acme-usbgadget-init.service
    install -m 0644 ${WORKDIR}/acme-usbgadget-modules.conf ${D}${sysconfdir}/modules-load.d/
    install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/
}


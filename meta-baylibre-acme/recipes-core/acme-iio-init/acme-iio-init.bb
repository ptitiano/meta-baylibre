SUMMARY = "A service type script to initialize the probes"
DESCRIPTION = "A service type script to initialize the probes"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

inherit systemd

#INITSCRIPT_NAME = "acme-iio-init"
#INITSCRIPT_PARAMS = "start 99 . stop 0 ."

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "acme-iio-init.service"

SRC_URI = "file://acme-iio-init \
           file://acme-iio-init.service \
	   file://acme-iio-modules.conf"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d/
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/modules-load.d/
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/acme-iio-init ${D}${bindir}
    ln -s ${bindir}/acme-iio-init ${D}${sysconfdir}/init.d/acme-iio-init
    install -m 0644 ${WORKDIR}/acme-iio-init.service ${D}${systemd_unitdir}/system
    sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/acme-iio-init.service
    install -m 0644 ${WORKDIR}/acme-iio-modules.conf ${D}${sysconfdir}/modules-load.d/
}


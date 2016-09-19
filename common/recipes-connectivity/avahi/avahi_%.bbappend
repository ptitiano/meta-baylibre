FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " file://ssh.service"


FILES_avahi-daemon += "${sysconfdir}/avahi/services/*.service"


do_install_append() {
    install -d ${D}${sysconfdir}/avahi/services
    install -m 0755 ${WORKDIR}/ssh.service ${D}${sysconfdir}/avahi/services
}


LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://sigrok.org/download/source/sigrok-cli/sigrok-cli-${PV}.tar.gz"
SRC_URI[md5sum] = "f6786bc4d2455b8b7d225f4909946549"
SRC_URI[sha256sum] = "73a30501525b13c09624ae00d37041cdaa50238d89c6febf169fc784affe723c"

DEPENDS = "glib-2.0 libsigrok libsigrokdecode"

inherit pkgconfig autotools

EXTRA_OECONF = ""

LICENSE = "GPLv3"
SECTION = "libs"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://sigrok.org/download/source/libsigrokdecode/libsigrokdecode-${PV}.tar.gz"
SRC_URI[md5sum] = "39d96f51800c8602fad90996f638ea90"
SRC_URI[sha256sum] = "2c50efe16c2424b77ab0d9ae6b5d98d7c9894407ddb43dfb43846b3bdef5b5d1"

DEPENDS = "libcheck python3 glib-2.0"

inherit pkgconfig python3native autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = ""

LICENSE = "GPLv3"
SECTION = "libs"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/baylibre-acme/libsigrok/releases/download/libsigrok-${PV}-iio1/libsigrok-${PV}-iio1.tar.gz"
SRC_URI[md5sum] = "2833774ad596e07075463f7acc526a77"
SRC_URI[sha256sum] = "fbeb2726da5a82cc77f622cdf0d82f114f73d54574513745e000771bf4ce5957"

S = "${WORKDIR}/${BPN}-${PV}-iio1"

DEPENDS = "libiio libusb1 libcheck glib-2.0 libzip"

inherit pkgconfig autotools

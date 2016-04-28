FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
LINUX_VERSION="4.5.2"

# defconfig fragments
SRC_URI += "file://acme-baylibre.cfg \
	    file://iio.cfg" 

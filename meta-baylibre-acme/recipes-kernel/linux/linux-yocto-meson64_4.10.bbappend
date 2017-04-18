FILESEXTRAPATHS_append := "${THISDIR}/linux-yocto-meson64-4.10:"

SRC_URI += "file://acme.cfg	\
	    file://0001-acme-ina226-add-the-poweron-gpio-support.patch	\
	    file://0002-pca953x-defer-probe-on-1st-register-read-failure.patch	\
	    file://0003-iio-ina2xx-adc-return-probe-defer-when-gpio-returns-.patch	\
	    file://0004-ARM64-dts-meson-gxbb-odroidc2-Add-ACME-nodes.patch	\
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.7.10"

SRCREV = "c2cbd9c611256e7b957f75c23d9f76d58a4893c1"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.7.y 	\
	    file://acme.cfg								\
	    file://0001-eeprom-at24-improve-the-device_id-table-readability.patch	\
	    file://0002-eeprom-at24-move-at24_read-below-at24_eeprom_write.patch	\
	    file://0003-eeprom-at24-coding-style-fixes.patch				\
            file://0004-eeprom-at24-call-read-write-functions-via-function-p.patch	\
            file://0005-eeprom-at24-hide-the-read-write-loop-behind-a-macro.patch	\
            file://0006-eeprom-at24-split-at24_eeprom_read-into-specialized-.patch	\
            file://0007-eeprom-at24-split-at24_eeprom_write-into-specialized.patch	\
            file://0008-eeprom-at24-platform_data-use-BIT-macro.patch			\
            file://0009-eeprom-at24-support-reading-the-serial-number-for-24.patch	\
            file://0010-eeprom-at24-add-support-for-at24mac-series.patch		\
            file://0011-eeprom-at24-tweak-the-loop_until_timeout-macro.patch		\
            file://0012-eeprom-at24-check-if-the-chip-is-functional-in-probe.patch	\
            file://0013-gpio-pca953x-fix-a-lockdep-warning.patch			\
            file://0014-acme-ina226-add-the-poweron-gpio-support.patch			\
            file://0018-pca953x-defer-probe-on-1st-register-read-failure.patch		\
            file://0019-iio-ina2xx-adc-return-probe-defer-when-gpio-returns-.patch	\
	    file://0020-ARM-dts-Add-ACME-overlay.patch					\
	    file://0021-Update-RPI-defconfig-for-ACME.patch				\
"

require recipes-kernel/linux/linux-raspberrypi.inc

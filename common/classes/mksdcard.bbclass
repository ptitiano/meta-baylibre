DEPENDS += " \
	parted-native \
	dosfstools-native \
	mtools-native \
        mtd-utils-native"

do_wic() {
#    wic create sdimage-bootpart -s -e ${IMAGE_NAME} 
}


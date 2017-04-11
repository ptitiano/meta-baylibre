do_deploy_append() {
	echo "dtparam=i2c_arm=on" >>${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "dtoverlay=acme" >>${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "dtparam=i2c_arm_baudrate=400000" >>${DEPLOYDIR}/bcm2835-bootfiles/config.txt
}

# Fix missing packages def

SUMMARY_${PN}-enum ?="Python Dummy"
RDEPENDS_${PN}-enum ?="${PN}-core"
ALLOW_EMPTY_${PN}-enum ?= "1"

SUMMARY_${PN}-signal ?="Python Dummy"
RDEPENDS_${PN}-signal ?="${PN}-core"
ALLOW_EMPTY_${PN}-signal ?= "1"

SUMMARY_${PN}-selectors ?="Python Dummy"
RDEPENDS_${PN}-selectors ?="${PN}-core"
ALLOW_EMPTY_${PN}-selectors ?= "1"

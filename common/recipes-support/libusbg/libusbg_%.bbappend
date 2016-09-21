FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Personalize USB strings for ACME
SRC_URI += " file://gadget-acm-ecm-baylibre-acme.patch"

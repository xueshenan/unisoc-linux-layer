# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc ATRouter Configure Files"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

SRC_URI = ""

# Modify these as desired
PV = "1.0"

do_install () {
	# Specify install commands here

	install -d ${D}${sysconfdir}/atrouter/
	if [ ! -f "${THISDIR}/files/${MACHINE}/system.ini" ]; then
		install -m 0644 ${THISDIR}/files/default/system.ini ${D}${sysconfdir}/atrouter/system.ini
	else
		install -m 0644 ${THISDIR}/files/${MACHINE}/system.ini ${D}${sysconfdir}/atrouter/system.ini
	fi
}

FILES_${PN} = " \
	${sysconfdir}/atrouter/* \
"

deltask rm_work

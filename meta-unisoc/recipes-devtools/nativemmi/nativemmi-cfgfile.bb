# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Nativemmi Configure Files"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION_${PN} = "base"

SRC_URI = "file://COPYING \
           file://config \
"

# Modify these as desired
PV = "1.0"

S = "${WORKDIR}"

# Define default configure message
NTVM_CFG ?= "default"

do_install () {
	# Specify install commands here

	install -d ${D}${sysconfdir}/nativemmi/
	install -m 0644 ${WORKDIR}/config/${NTVM_CFG}/nativemmi.ini ${D}${sysconfdir}/nativemmi/nativemmi.ini
	install -m 0644 ${WORKDIR}/config/${NTVM_CFG}/PCBA.conf ${D}${sysconfdir}/nativemmi/PCBA.conf
}

FILES_${PN} = " \
	${sysconfdir}/nativemmi/* \
"

deltask rm_work

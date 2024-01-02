# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Nativemmi initscript for sysVinit"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION_${PN} = "base"

SRC_URI = "file://COPYING \
           file://script/nativemmi-init.sh \
"

S = "${WORKDIR}"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/script/nativemmi-init.sh ${D}/${sysconfdir}/init.d/nativemmi-init
}

FILES_${PN} = " \
	${sysconfdir}/init.d/nativemmi-init \
"

deltask rm_work

# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Nativemmi service for systemd"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION_${PN} = "base"

SRC_URI = "file://COPYING \
           file://script/unisoc-production-factory.service \
"

inherit systemd

S = "${WORKDIR}"

do_install () {
	install -d ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/script/unisoc-production-factory.service ${D}${systemd_unitdir}/system
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "unisoc-production-factory.service"
NATIVE_SYSTEMD_SUPPORT = "1"

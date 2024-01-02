# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Engpc service for systemd"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

SRC_URI = "file://COPYING \
           file://script/unisoc-production-engpc.service \
"

inherit systemd

S = "${WORKDIR}"

do_install () {
	install -d ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/script/unisoc-production-engpc.service ${D}${systemd_unitdir}/system
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "unisoc-production-engpc.service"
NATIVE_SYSTEMD_SUPPORT = "1"

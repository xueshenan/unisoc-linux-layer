# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc ATRouter Initscript for Sysvinit"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

SRC_URI = "file://COPYING \
           file://script/atrouter-init.sh \
"

inherit update-rc.d

S = "${WORKDIR}"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/script/atrouter-init.sh ${D}/${INIT_D_DIR}/atrouter-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "atrouter-init"
INITSCRIPT_PARAMS_${PN} = "start 50 3 4 5 . stop 50 0 6 ."
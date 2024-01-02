# SPDX-FileCopyrightText: 2021 Unisoc (beijing) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc sprdstorage initscript for sysVinit"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=2f042cf97852148d4513d2ce6ae8f03d"
SECTION_${PN} = "base"

SRC_URI = "file://COPYING \
           file://sprdstorage-init \
"

inherit update-rc.d

S = "${WORKDIR}"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/sprdstorage-init ${D}/${INIT_D_DIR}/sprdstorage-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "sprdstorage-init"
INITSCRIPT_PARAMS_${PN} = "start 61 3 4 5 . stop 61 0 6 ."

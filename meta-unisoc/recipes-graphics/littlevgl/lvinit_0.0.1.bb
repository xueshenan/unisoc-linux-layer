# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "lvgl init module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "lvinit"
LIC_FILES_CHKSUM =  "file://COPYING;md5=21016c0ddd8dc379baa0de0907a53603"
DEPENDS = "lvgl lvdrv"
RDEPENDS_${PN} = "lvgl lvdrv"


EXTERNALSRC = "${OEROOT}/source/unisoc/graphic/littlevgl/lvinit"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}
    install -m 0755 ${S}/liblvinit.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

deltask do_rm_work

FILES_${PN} += "${libdir}/liblvinit.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

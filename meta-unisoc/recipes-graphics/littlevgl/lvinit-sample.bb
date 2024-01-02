# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc lvgl call sample"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bins"
PROVIDES = "lvinit-sample"
LIC_FILES_CHKSUM =  "file://COPYING;md5=21016c0ddd8dc379baa0de0907a53603"
DEPENDS = "lvgl lvdrv lvinit"
RDEPENDS_${PN} = "lvgl lvdrv lvinit"

EXTERNALSRC = "${OEROOT}/source/unisoc/graphic/littlevgl/lvinit-sample"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

TARGET_CC_ARCH += "${LDFLAGS}"
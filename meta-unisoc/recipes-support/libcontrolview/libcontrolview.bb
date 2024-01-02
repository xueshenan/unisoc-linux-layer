# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc engmodeapp control-view Lib"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "libcontrolview"
PV = "0.1"
PR = "r0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"

DEPENDS = "libsessionqueue lvgl lvdrv lvinit"
RDEPENDS_${PN} = "libsessionqueue lvgl lvdrv lvinit"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/components/libcontrolview"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/modules/components/libcontrolview"

do_compile () {
  make clean
  make
}

do_install () {
  # Specify install commands here
  install -d ${D}${libdir}
  install -m 0644 ${S}/libcontrolview.so ${D}${libdir}/libcontrolview.so
  install -d ${D}${includedir}/${PN}
  install -m 0644 ${S}/inc/*.h ${D}${includedir}/${PN}
  install -m 0644 ${S}/UI/inc/*.h ${D}${includedir}/${PN}
}

FILES_${PN} += "${libdir}/libcontrolview.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

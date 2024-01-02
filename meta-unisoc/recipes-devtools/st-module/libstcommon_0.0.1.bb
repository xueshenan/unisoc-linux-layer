# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc libstcommon module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=634d3f35f9e470595888adc0dd03b526"
SECTION = "libs"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc dbus glib-2.0 libofono"
RDEPENDS_${PN} = "dbus glib-2.0 libofono"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0644 ${S}/libstcommon.so ${D}${libdir}/libstcommon.so
    install -d ${D}${includedir}/${PN}
    install -m 0644 ${S}/inc/*.h ${D}${includedir}/${PN}
}

FILES_${PN} += "${libdir}/libstcommon.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
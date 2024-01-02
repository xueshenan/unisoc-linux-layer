# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc libstmultimedia module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=634d3f35f9e470595888adc0dd03b526"
SECTION = "libs"
PV = "0.1"
PR = "r0"

DEPENDS = "libstcommon"
RDEPENDS_${PN} = "libstcommon"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${libdir}/stmodule/
    install -m 0644 ${S}/libstmultimedia.so ${D}${libdir}/stmodule/libstmultimedia.so
}

FILES_${PN} += "${libdir}/stmodule/libstmultimedia.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
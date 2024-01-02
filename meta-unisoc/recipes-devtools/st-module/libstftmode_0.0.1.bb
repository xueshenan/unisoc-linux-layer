# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc libstftmode module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=41f3f5ec746235af1ca119d0df2e3231"
SECTION = "libs"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc libsttest"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${libdir}/stmodule/
    install -m 0644 ${S}/libstftmode.so ${D}${libdir}/stmodule/libstftmode.so
}

FILES_${PN} += "${libdir}/stmodule/libstftmode.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

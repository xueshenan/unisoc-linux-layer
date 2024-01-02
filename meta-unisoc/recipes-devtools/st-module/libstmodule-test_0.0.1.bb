# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc libstmodule-test"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=634d3f35f9e470595888adc0dd03b526"
SECTION = "libs"
PV = "0.1"
PR = "r0"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    # Specify install commands here
    oe_runmake -C ${S} install DESTDIR=${D}
}
FILES_${PN} = " \
    ${bindir}/stmodule-test \
"
TARGET_CC_ARCH += "${LDFLAGS}"
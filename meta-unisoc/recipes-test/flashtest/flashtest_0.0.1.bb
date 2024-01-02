# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc Flash Test"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION = "bin"
PROVIDES = "flashtest"
PV = "0.1"
PR = "r0"

DEPENDS = ""
RDEPENDS_${PN} = ""

SRC_URI = " \
        file://flashtest-init.sh \
          "

EXTERNALSRC_BUILD = "${EXTERNALSRC}"


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/flashtest ${D}${bindir}/

}


FILES_${PN} += " \
    ${bindir}/flashtest \
"
TARGET_CC_ARCH += "${LDFLAGS}"


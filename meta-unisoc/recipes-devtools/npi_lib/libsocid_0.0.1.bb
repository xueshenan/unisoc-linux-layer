# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc libsocid module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION = "libs"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"
DEPENDS += "libkernelbootcp"
RDEPENDS_${PN} = "libkernelbootcp"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/npi_lib/libsocid"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice/
    install -m 0644 ${S}/libsocid.so ${D}${libdir}/npidevice/
}

FILES_${PN} += "${libdir}/npidevice/libsocid.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

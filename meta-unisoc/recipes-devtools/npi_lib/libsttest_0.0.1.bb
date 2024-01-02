# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc libsttest module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION = "libs"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${libdir}/npidevice/
    install -m 0644 ${S}/libsttest.so ${D}${libdir}/npidevice/
}

do_install_append () {
    install -d ${D}${includedir}/inc
    install -m 0644 ${S}/inc/*.h ${D}${includedir}/inc
}

FILES_${PN}-dev =" \
    ${includedir}/inc \
"

FILES_${PN} += "${libdir}/npidevice/libsttest.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

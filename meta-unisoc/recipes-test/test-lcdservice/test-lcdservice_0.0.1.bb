# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc lcdservice IT module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
PV = "0.1"
PR = "r0"

DEPENDS = "dbus dbushelper lcdservice gtest"
RDEPENDS_${PN} = "dbus dbushelper lcdservice"

inherit pkgconfig

EXTERNALSRC = "${OEROOT}/source/unisoc/powerfw/lcdservice/itest"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


FILES_${PN} += "${bindir}/utit/lcdservice_IT"

do_compile(){
    make clean
    make  WORKDIR=${WORKDIR}
}

do_install(){
    install -d ${D}${bindir}/utit/
    install -m 0755 ${S}/lcdservice_IT ${D}${bindir}/utit/
}

TARGET_CC_ARCH += "${LDFLAGS}"

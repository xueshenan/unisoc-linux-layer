# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc gnss-adapter IT module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
PV = "0.1"
PR = "r0"

DEPENDS = "dbus dbushelper gtest"
RDEPENDS_${PN} = "dbus dbushelper gnss-adapter"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/gnss-adapter/itest"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


FILES_${PN} += "${bindir}/utit/gnss-adapter_IT"

do_compile(){
    make clean
    make  WORKDIR=${WORKDIR}
}

do_install(){
    install -d ${D}${bindir}/utit/
    install -m 0755 ${S}/gnss-adapter_IT ${D}${bindir}/utit/
}

TARGET_CC_ARCH += "${LDFLAGS}"

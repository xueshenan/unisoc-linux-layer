# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc powermanager IT module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
PV = "0.1"
PR = "r0"

DEPENDS = "dbus dbushelper powermanager gtest"
RDEPENDS_${PN} = "dbus dbushelper powermanager"

EXTERNALSRC = "${OEROOT}/source/unisoc/powerfw/powermanager/itest"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


FILES_${PN} += "${bindir}/utit/powermanager_IT"

do_compile(){
    make clean
    make  WORKDIR=${WORKDIR}
}

do_install(){
    install -d ${D}${bindir}/utit/
    install -m 0755 ${S}/powermanager_IT ${D}${bindir}/utit/
}

TARGET_CC_ARCH += "${LDFLAGS}"

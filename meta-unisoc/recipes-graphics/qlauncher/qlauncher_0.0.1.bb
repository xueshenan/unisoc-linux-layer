# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc qlauncher module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
#SECTION = "bin"
SECTION_${PN} = "base"
PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM =  "file://COPYING;md5=70d08a37f759ae56b960daaf70243b40"
#EXTERNALSRC = "${OEROOT}/layers/meta-unisoc/recipes-graphics/qlauncher"
EXTERNALSRC = "${OEROOT}/source/unisoc/graphic/qlauncher"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

require recipes-qt/qt5/qt5.inc

PROVIDES = "qlauncher"

S = "${WORKDIR}"

# libconnman
DEPENDS = "qtbase "
RDEPENDS_${PN} = "qtbase"

# build CMakeLists.txt
#inherit cmake systemd
inherit qmake5 update-rc.d

# 当配置系统使用 sysvinit 方式管理开机启动时，用 update-c.d.bbclass 来管理服务的开机启动配置
# 当配置系统使用 systemd  方式管理开机启动时，用 systemd.bbclass 来管理服务的开机启动配置
# update-c.d/systemd 和 INITSCRIPT_NAME 必须成对出现
INITSCRIPT_NAME = "qlauncher-init"
INITSCRIPT_PARAMS = "start 99 5 ."

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_install () {
    install -d ${D}${datadir}

    install -m 0755 qlauncher ${D}${datadir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/qlauncher-init.sh ${D}${sysconfdir}/init.d/qlauncher-init
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} += "${datadir} ${sysconfdir}/init.d/"
#FILES ${PN}-staticdev =""   # for static libs
#FILES ${PN}-dev =""         # for dynamic libs
#FILES_${PN}-dbg =""         # for debug options

do_configure[postfuncs] =""

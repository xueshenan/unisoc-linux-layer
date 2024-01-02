# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc qfactorytest module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
#SECTION = "bin"
SECTION_${PN} = "base"
PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM =  "file://COPYING;md5=70d08a37f759ae56b960daaf70243b40"
#EXTERNALSRC = "${OEROOT}/layers/meta-unisoc/recipes-graphics/qfactorytest"
EXTERNALSRC = "${OEROOT}/source/unisoc/graphic/qfactorytest"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

require recipes-qt/qt5/qt5.inc

#FILESEXTRAPATHS_prepend := "${THISDIR}:"
#SRC_URI = " \
#        file://qfactorytest-init.sh \
#        "
PROVIDES = "qfactorytest"

S = "${WORKDIR}"

# libconnman
DEPENDS = "qtbase qtdeclarative qtmultimedia qtsensors qtserialport qtlocation qtsystems alsa-lib libofono gstreamer1.0 glib-2.0 libcamv4l2adapter libcamv4l2yuv2rgb libcamsensor libcamcommon libmemion"
RDEPENDS_${PN} = "qtbase qtdeclarative qtmultimedia qtsensors qtserialport qtlocation qtsystems libofono gstreamer1.0 glib-2.0 libcamv4l2adapter libcamv4l2yuv2rgb libcamsensor libcamcommon libmemion"

# build CMakeLists.txt
#inherit cmake systemd update-rc.d
inherit qmake5

# 当配置系统使用 sysvinit 方式管理开机启动时，用 update-c.d.bbclass 来管理服务的开机启动配置
# 当配置系统使用 systemd  方式管理开机启动时，用 systemd.bbclass 来管理服务的开机启动配置
# update-c.d/systemd 和 INITSCRIPT_NAME 必须成对出现
#INITSCRIPT_NAME = "qfactorytest-init"
#INITSCRIPT_PARAMS = "start 99 5 ."

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_install () {
    install -d ${D}${datadir}
    install -dv -m -0655 ${D}${datadir}/applications/
    install -dv -m -0655 ${D}${datadir}/icon/
    install -dv -m -0655 ${D}${datadir}/icons/
    install -dv -m -0655 ${D}${datadir}/xml/

    install -m 0755 qfactorytest ${D}${datadir}/
    install -m 0666 ${S}/qfactorytest.desktop ${D}${datadir}/applications/
    install -m 0666 ${S}/icon_qfactorytest.png ${D}${datadir}/icon/
    install -m 0666 ${S}/res/icon_qfactorytest.png ${D}${datadir}/icons/
    install -m 0666 ${S}/res/qfactory_config.xml ${D}${datadir}/xml/

    #install -dv -m -0655 ${D}${libdir}/fonts/
    #install -m 0666 ${S}/res/fonts/Font_Awesome_6_Free-Solid-900.otf ${D}${libdir}/fonts/
    
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/qfactorytest-init.sh ${D}${sysconfdir}/init.d/qfactorytest-init
    #install -Dm 0644 ${S}/qfactorytest.service ${D}${systemd_system_unitdir}/qfactorytest.service
}

#SYSTEMD_PACKAGES = "${PN}"
#SYSTEMD_SERVICE_${PN} = "qfactorytest.service"

TARGET_CC_ARCH += "${LDFLAGS}"

# ${libdir} ${systemd_system_unitdir}/qfactorytest.service
FILES_${PN} += "${datadir} ${sysconfdir}/init.d/"
#FILES ${PN}-staticdev =""   # for static libs
#FILES ${PN}-dev =""         # for dynamic libs
#FILES_${PN}-dbg =""         # for debug options

do_configure[postfuncs] =""

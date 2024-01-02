# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Engmodeapp Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

PR = "r0"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/engmodeapp;protocol=ssh;branch=unc_linux_trunk_2.0"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

#Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

PROVIDES = "engmodeapp"

DEPENDS = "libcontrolview glib-2.0 dbus iniparser libofono chm-at"
RDEPENDS_${PN} = "libcontrolview glib-2.0 dbus iniparser libofono chm-at"

do_compile () {
  make clean
  make WORKDIR=${WORKDIR}
}

do_install () {
  install -d ${D}${bindir}/
  install -m 0755 ${S}/engmodeapp ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"
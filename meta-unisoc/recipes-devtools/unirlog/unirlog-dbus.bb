# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc unirlog dbus module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION_${PN} = "base"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/unirlog;protocol=ssh;branch=unc_linux_trunk_2.0"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

inherit pkgconfig

DEPENDS = "dbushelper dbus"
RDEPENDS_${PN} = "dbushelper dbus"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S} install DESTDIR=${D}
}

FILES_${PN} = " \
	${bindir}/unirlogdbus \
"

TARGET_CC_ARCH += "${LDFLAGS}"

# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc engmodeapp control-view Lib"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
PROVIDES = "unirlog-manager"

PV = "0.1"
PR = "r0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"

DEPENDS = "libcontrolview libunirlogsocket"
RDEPENDS_${PN} = "libcontrolview libunirlogsocket"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/unirlog/unirlog-manager;protocol=ssh;branch=unc_linux_trunk_2.0"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S} install DESTDIR=${D}
}

FILES_${PN} = " \
	${bindir}/unirlog-manager \
"

TARGET_CC_ARCH += "${LDFLAGS}"
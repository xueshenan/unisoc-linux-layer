# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Nativemmi Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION_${PN} = "base"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/nativemmi;protocol=ssh;branch=unc_glp_nativemmi"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

DEPENDS = "iniparser lvgl lvdrv lvinit libsessionqueue engpc"
RDEPENDS_${PN} = "iniparser libsessionqueue \
                  lvgl lvdrv lvinit \
                  ${PN}-cfgfile \
                  ${@bb.utils.contains('INIT_MANAGER', 'systemd', '${PN}-systemd', '${PN}-initrc', d)} \
"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S} install DESTDIR=${D}
}

FILES_${PN} = " \
    ${bindir}/nativemmi \
"

TARGET_CC_ARCH += "${LDFLAGS}"

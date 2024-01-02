# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Engpc Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://server/COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/engpc;protocol=ssh;branch=unc_glp_engpc"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

DEPENDS = "engpc-adapt engpc-ctl iniparser"
RDEPENDS_${PN} = "engpc-adapt engpc-ctl iniparser \
                  ${@bb.utils.contains('USERDEBUG', 'user', '${PN}-userfile', '${PN}-cfgfile', d)} \
                  ${PN}-initrc\
"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}/server
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S}/server install DESTDIR=${D}
}

do_install_append () {
	# Specify install commands here
	install -d ${D}${includedir}/sprd_fts_inc
	install -m 0644 ${S}/sprd_fts_inc/*.h ${D}${includedir}/sprd_fts_inc
	install -d ${D}${includedir}/sprd_fts_inc/linux
	install -m 0644 ${S}/sprd_fts_inc/linux/*.h ${D}${includedir}/sprd_fts_inc/linux/
}

FILES_${PN} = " \
    ${bindir}/engpc \
"

FILES_${PN}-dev =" \
    ${includedir}/sprd_fts_inc \
"

TARGET_CC_ARCH += "${LDFLAGS}"


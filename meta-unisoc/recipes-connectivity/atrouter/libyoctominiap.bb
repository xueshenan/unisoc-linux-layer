# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc Libyoctominiap Lib"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "libs"

EXTERNALSRC = "${EXTERNALSRC}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

DEPENDS = "libofono iniparser libatcommon libcptransport libmodeminictl"
RDEPENDS_${PN} = "libofono iniparser libatcommon libcptransport libmodeminictl "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

LIBMISCDATA_SECSET ?= "${@bb.utils.contains('STORAGE_TYPE','mcp','-DCONFIG_NAND','',d)}"
export CONFIG_NAND_FLAG="${LIBMISCDATA_SECSET}"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S} install DESTDIR=${D}
}

do_install_append () {
	install -d ${D}${sysconfdir}/libyoctominiap/
	install -m 0644 ${THISDIR}/libyoctominiap/system.ini ${D}${sysconfdir}/libyoctominiap/system.ini
}

FILES_${PN} = " \
	${sysconfdir}/libyoctominiap/* \
	${libdir}/atlib/libyoctominiap.so \
"

TARGET_CC_ARCH += "${LDFLAGS}"
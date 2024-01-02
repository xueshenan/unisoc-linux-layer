# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc libmiscdata module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "libs"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/engpc;protocol=ssh;branch=unc_glp_engpc"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

DEPENDS = "engpc"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

EXTRA_OEMAKE = "\
  'CC=${CC}' \
  'AR=${AR}' \
  'LDFLAGS=${LDFLAGS}' \
"

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

FILES_${PN} += " \
    ${libdir}/npidevice/libmiscdata.so \
"

TARGET_CC_ARCH += "${LDFLAGS}"
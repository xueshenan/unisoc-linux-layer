# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc libapdeepsleep module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "libs"

DEPENDS = "engpc"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/npi_lib;protocol=ssh;branch=unc_glp_trunk"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

DEPENDS = "engpc"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export AP_DEVICE_SLEEP_CFLAGS="${AP_DEVICE_SEEP_SET}"
export AP_CALI_RM_WIFI_CFLAGS="${AP_CALI_RM_WIFI_SET}"

EXTRA_OEMAKE = "\
  'CC=${CC}' \
  'AR=${AR}' \
  'LDFLAGS=${LDFLAGS}' \
"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S} install DESTDIR=${D}
}

FILES_${PN} += " \
    ${libdir}/npidevice/libapdeepsleep.so \
"

TARGET_CC_ARCH += "${LDFLAGS}"

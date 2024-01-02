# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc sessionqueue Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "libs"

# Modify these as desired
PV = "1.0"
PR = "r0"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/components/libsessionqueue"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/modules/components/libsessionqueue"

do_compile () {
	# Specify compilation commands here
	make clean
	make
}

do_install () {
	# Specify install commands here
	install -d ${D}${libdir}
	install -m 0777 ${S}/libsessionqueue.so ${D}${libdir}/libsessionqueue.so
	install -d ${D}${includedir}/${PN}/
	install -m 0777 ${S}/*.h ${D}${includedir}/${PN}/
}

FILES_${PN} += "${libdir}/libsessionqueue.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

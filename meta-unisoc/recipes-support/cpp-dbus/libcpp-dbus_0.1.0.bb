# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc cpp dbus Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "libs"

# Modify these as desired
PV = "1.0"
PR = "r0"

DEPENDS = "dbus pkgconfig-native"
RDEPENDS_${PN} = "dbus"

EXTERNALSRC = "${OEROOT}/source/unisoc/powermanager/powerd/libcpp-dbus"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/powermanager/powerd/libcpp-dbus"

do_compile () {
	# Specify compilation commands here
	make clean
	make
}

do_install () {
	# Specify install commands here
	install -d ${D}${libdir}
	install -m 0777 ${S}/libcpp-dbus.so ${D}${libdir}/libcpp-dbus.so
}

do_install_append () {
	install -d ${D}${includedir}
	install -m 0644 ${S}/*.h ${D}${includedir}
}

FILES_${PN} += "${libdir}/libcpp-dbus.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

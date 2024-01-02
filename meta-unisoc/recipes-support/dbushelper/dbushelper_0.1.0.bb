# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc dbus helper Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=41f3f5ec746235af1ca119d0df2e3231"
SECTION_${PN} = "libs"

# Modify these as desired
PV = "1.0"
PR = "r0"

DEPENDS = "dbus"
RDEPENDS_${PN} = "dbus"

inherit pkgconfig

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/components/libdbushelper"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/modules/components/libdbushelper"

do_compile () {
	# Specify compilation commands here
	make clean
	make WORKDIR=${WORKDIR}
}

do_install () {
	# Specify install commands here
	install -d ${D}${libdir}
	install -m 0777 ${S}/libdbushelper.so ${D}${libdir}/libdbushelper.so
}

do_install_append () {
	install -d ${D}${includedir}/${PN}
	install -m 0644 ${S}/${PN}/*.h ${D}${includedir}/${PN}
}

FILES_${PN} += "${libdir}/libdbushelper.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

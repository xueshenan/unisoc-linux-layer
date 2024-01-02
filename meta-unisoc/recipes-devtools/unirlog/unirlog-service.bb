# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Nativemmi Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
SECTION_${PN} = "base"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/unirlog;protocol=ssh;branch=unc_linux_trunk_2.0"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

inherit update-rc.d pkgconfig

DEPENDS = "libuevent dbushelper dbus iniparser"
RDEPENDS_${PN} = "libuevent dbushelper dbus iniparser"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S} install DESTDIR=${D}
}

do_install_append () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${THISDIR}/files/script/unirlog-service-init.sh ${D}/${sysconfdir}/init.d/unirlog-service-init
	mv ${D}${bindir}/unirlog-service ${D}${bindir}/unirlogservice

	install -d ${D}${sysconfdir}/dbus-1/system.d
	install -m 0644 ${THISDIR}/files/config/unirlogservice-dbus.conf ${D}${sysconfdir}/dbus-1/system.d/unirlogservice-dbus.conf
	
	install -d ${D}${sysconfdir}/unirlogservice/
	install -m 0644 ${THISDIR}/files/config/system.ini ${D}${sysconfdir}/unirlogservice/system.ini
}

FILES_${PN} += " \
	${bindir}/unirlogservice \
	${sysconfdir}/dbus-1/system.d/unirlogservice-dbus.conf \
	${sysconfdir}/unirlogservice/system.ini \
"

TARGET_CC_ARCH += "${LDFLAGS}"

INITSCRIPT_NAME = "unirlog-service-init"
INITSCRIPT_PARAMS = "start 50 3 4 5 . stop 50 0 6 ."

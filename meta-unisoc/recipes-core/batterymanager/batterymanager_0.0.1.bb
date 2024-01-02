# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc BatteryManager Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

# Modify these as desired
PV = "1.0"
PR = "r0"

SRC_URI = " \
           file://script/batterymanager-init.sh \
           file://config/system.ini \
"

DEPENDS = "libsessionqueue libuevent libcpp-dbus dbus iniparser"
RDEPENDS_${PN} = "libsessionqueue libuevent libcpp-dbus dbus iniparser"

EXTRA_OEMAKE = " \
  'CC=${CC}' \
  'AR=${AR}' \
  'LDFLAGS=${LDFLAGS}' \
  'CFLAGS=${CFLAGS}' \
"

inherit update-rc.d

export SYS_ROOT = "${WORKDIR}"

do_compile () {
	# Specify compilation commands here
	oe_runmake -C ${S}
}

do_install () {
	# Specify install commands here
	oe_runmake -C ${S} install DESTDIR=${D}
	install -d ${D}${includedir}
	install -m 0644 ${S}/inc/*.h ${D}${includedir}
}

do_install_append () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/script/batterymanager-init.sh ${D}/${INIT_D_DIR}/batterymanager-init

	install -d ${D}${sysconfdir}/batterymanager/
	install -m 0644 ${WORKDIR}/config/system.ini ${D}${sysconfdir}/batterymanager/system.ini
}

FILES_${PN} = " \
	${bindir}/batterymanager \
	${INIT_D_DIR}/batterymanager-init \
	${sysconfdir}/batterymanager/system.ini \
"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "batterymanager-init"
INITSCRIPT_PARAMS_${PN} = "start 50 3 4 5 . stop 50 0 6 ."

TARGET_CC_ARCH += "${LDFLAGS}"

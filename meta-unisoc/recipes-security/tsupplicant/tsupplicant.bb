# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc tsupplicant module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"

PACKAGES =+ "${PN}-tsupplicant"
DEPENDS = "libtrusty"
RDEPENDS_${PN} = "libtrusty ${PN}-tsupplicant"
SECTION = "bins"
LIC_FILES_CHKSUM = "file://COPYING;md5=9f2c5a42d436ca84d1af26f5b1908793"
PROVIDES = "tsupplicant"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"


inherit systemd update-rc.d

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "tsupplicant.service"


INITSCRIPT_PACKAGES = "${PN}-tsupplicant"
INITSCRIPT_NAME_${PN}-tsupplicant = "tsupplicant-init"
INITSCRIPT_PARAMS_${PN}-tsupplicant = "start 59 3 4 5 . stop 59 0 6 ."

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/tsupplicant ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${THISDIR}/files/tsupplicant-init ${D}/${sysconfdir}/init.d/tsupplicant-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${THISDIR}/files/tsupplicant.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = "${bindir}/tsupplicant \
                ${systemd_unitdir}/system/tsupplicant.service"

FILES_${PN}-tsupplicant = "${sysconfdir}/init.d/tsupplicant-init"


TARGET_CC_ARCH += "${LDFLAGS}"

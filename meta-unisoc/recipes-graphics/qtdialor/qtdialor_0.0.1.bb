# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc qt dialor demo module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION_${PN} = "base"
PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM =  "file://COPYING;md5=3ca06ce07025ae20c1099892b2593dd9"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

require recipes-qt/qt5/qt5.inc

DEPENDS = "qtbase libofono glib-2.0"
RDEPENDS_${PN} = "qtbase libofono glib-2.0"

do_install () {
    install -d ${D}${datadir}
    install -m 0755 ${S}/qtdialor ${D}${datadir}
}

FILES_${PN} += "${datadir}"

do_configure[postfuncs] =""

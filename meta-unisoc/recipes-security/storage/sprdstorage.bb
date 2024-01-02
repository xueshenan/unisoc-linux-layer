# SPDX-FileCopyrightText: 2021 Unisoc (beijing) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc sprdstorage module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"

SECTION = "bins"
LIC_FILES_CHKSUM = "file://COPYING;md5=ceee7455aff17e59432062f3cc1a6835"
PROVIDES = "sprdstorage"


EXTERNALSRC_BUILD = "${EXTERNALSRC}"


DEPENDS = "libtrusty iniparser"
RDEPENDS_${PN} = "libtrusty iniparser \
                  ${PN}-initrc\
"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/sprdstorage ${D}${bindir}/
}

FILES_${PN} = "${bindir}/sprdstorage"

TARGET_CC_ARCH += "${LDFLAGS}"

# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-UNISOC

DESCRIPTION = "a ini file parser module from the C level"
HOMEPAGE = "https://github.com/ndevilla/iniparser"
SECTION = "libs"
LICENSE = "MIT & Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e02baf71c76e0650e667d7da133379ac \
                    file://test/CuTest_license.txt;md5=5816069afcffd7c7015d5129c5a6647c"

SRC_URI = "git://github.com/ndevilla/${BPN}"
SRCREV = "0a38e85c9cde1e099ca3bf70083bd00f89c3e5b6"

S="${WORKDIR}/git"

inherit ptest

EXTRA_OEMAKE = "\
	'CC=${CC}' \
	'AR=${AR}' \
	'LDFLAGS=${LDFLAGS}' \
"

do_install () {
	install -d ${D}${libdir}
	install -m 0755 ${S}/libiniparser.so.1 ${D}${libdir}
	install -d ${D}${includedir}
	install -m 0644 ${S}/src/*.h ${D}${includedir}
	ln -s libiniparser.so.1 ${D}${libdir}/libiniparser.so
}

do_compile_ptest() {
	oe_runmake -C test testrun
}

do_install_ptest() {
	cp -r   ${B}/test/ressources ${D}${PTEST_PATH}
	install ${B}/test/testrun    ${D}${PTEST_PATH}
}

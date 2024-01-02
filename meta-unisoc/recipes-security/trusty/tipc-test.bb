DESCRIPTION = "Unisoc libtrusty module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
DEPENDS = "libtrusty"
RDEPENDS_${PN} = "libtrusty"
SECTION = "bins"
LIC_FILES_CHKSUM = "file://NOTICE;md5=9645f39e9db895a4aa6e02cb57294595"
PROVIDES = "tipc-test"

EXTERNALSRC = "${OEROOT}/source/unisoc/trusty/libtrusty/tipc-test/"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/tipc-test ${D}${bindir}/
}

FILES_${PN} += "${bindir}/tipc-test"
TARGET_CC_ARCH += "${LDFLAGS}"

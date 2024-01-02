DESCRIPTION = "Unisoc memtester"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=36dba8813a86f10cc3ecfbcf89dc6eab"
PV = "0.1"
PR = "r0"
PROVIDES = "unisoc-memtester"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/memtester ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"
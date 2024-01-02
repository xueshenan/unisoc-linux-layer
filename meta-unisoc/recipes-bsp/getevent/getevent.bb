DESCRIPTION = "getevent"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
# SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"

PROVIDES = "getevent"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/getevent"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/getevent ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"


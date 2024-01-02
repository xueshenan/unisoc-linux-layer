DESCRIPTION = "Unisoc inireader test"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=512d5423d038dc0f51c908700126ccfa"

PV = "0.1"
PR = "r0"
PROVIDES = "inireader-test"
DEPENDS = "inireader"
RDEPENDS_${PN} = "inireader"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/iniconfig;protocol=ssh;branch=unc_linux_trunk"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/inireader-test ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"
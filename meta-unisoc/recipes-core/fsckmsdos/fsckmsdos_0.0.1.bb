DESCRIPTION = "Unisoc fsck_msdos module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "BSD"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=da7ed0c87d1f20812e1c6454ae227040"
PV = "0.1"
PR = "r0"
PROVIDES = "fsck_msdos"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
	make clean
	make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/fsck_msdos ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

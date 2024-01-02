DESCRIPTION = "Unisoc power manager demo"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
PV = "0.1"
PR = "r0"
PROVIDES = "pm-demo"
DEPENDS = "pm"
RDEPENDS_${PN} = "pm"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/pm;protocol=ssh;branch=unc_linux_trunk"

do_compile () {
    make demo -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/pm-demo ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc libapopt module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "libapopt"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/libapopt;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libapopt.so ${D}${libdir}/npidevice
    install -d ${D}${sysconfdir}/engpc
    install -m 0770 ${S}/prodnv/BBAT.conf ${D}${sysconfdir}/engpc/
    install -m 0770 ${S}/prodnv/PCBA.conf ${D}${sysconfdir}/engpc/
}

FILES_${PN} += "${libdir}/npidevice/libapopt.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

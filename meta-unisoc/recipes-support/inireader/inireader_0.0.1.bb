DESCRIPTION = "Unisoc inireader lib"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "lib"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"

PV = "0.1"
PR = "r0"
PROVIDES = "inireader"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/iniconfig;protocol=ssh;branch=unc_linux_trunk"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libinireader.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libinireader.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
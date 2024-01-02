DESCRIPTION = "Unisoc RGB LED deamon"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
PROVIDES = "liborcaled"

PV = "0.1"
PR = "r0"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/led-lib;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0755 ${S}/liborcaled.so ${D}${libdir}/

    install -d ${D}${includedir}/
    install -m 0755 ${S}/ledset.h ${D}${includedir}/

}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

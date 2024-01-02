DECRIPTION = "Unisoc libdynamicproduction module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
DEPENDS = "libteeproduction"
RDEPENDS_${PN} = "libteeproduction"
LIC_FILES_CHKSUM = "file://COPYING;md5=9f2c5a42d436ca84d1af26f5b1908793"
PROVIDES = "libdynamicproduction"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice/
    install -m 0777 ${S}/libdynamicproduction.so ${D}${libdir}/npidevice/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/npidevice/libdynamicproduction.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc xml parser module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
PV = "0.1"
PR = "r0"
PROVIDES = "libcamxmlparser"
DEPENDS = "libxml2"
RDEPENDS_${PN} = "libxml2"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/*.so ${D}${libdir}/
    install -d ${D}${includedir}/libcamera/
    install -m 0777 ${S}/inc/*.h ${D}${includedir}/libcamera/
    install -m 0777 ${S}/inc/libxml2/libxml/*.h ${D}${includedir}/libcamera/
}


FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

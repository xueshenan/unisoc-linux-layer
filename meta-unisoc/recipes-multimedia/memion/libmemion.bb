DESCRIPTION = "Unisoc memion module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "lib"
DEPENDS = ""
RDEPENDS_${PN} = ""
PV = "0.1"
PR = "r0"
PROVIDES = "libmemion"

do_compile () {
    make -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libmemion.so ${D}${libdir}/
    install -d ${D}${includedir}/memion/
    install -m 0777 ${S}/include/*.h ${D}${includedir}/memion/
}

FILES_${PN} += "${libdir}/libmemion.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

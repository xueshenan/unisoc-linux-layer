DESCRIPTION = "Unisoc bqbbt module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "lib"
DEPENDS = ""
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
PV = "0.1"
PR = "r0"
PROVIDES = "bqbbt"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/wcn/marlin2/bttest/libbqbbt"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/connectivity/wcn/marlin2/bttest/libbqbbt"

export USERDEBUG

do_compile () {
    make clean -C ${S} OBJ_DIR=${B}
    make libbqbbt -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libbqbbt.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

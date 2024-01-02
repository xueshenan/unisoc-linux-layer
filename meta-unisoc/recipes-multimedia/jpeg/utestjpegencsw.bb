DESCRIPTION = "Unisoc jpeg demo"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "bin"
DEPENDS = ""
RDEPENDS_${PN} = ""
PV = "0.1"
PR = "r0"
PROVIDES = "utestjpegencsw"

export SYS_ROOT = "${WORKDIR}/recipe-sysroot"

do_compile () {
    make -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/utest_jpeg_sw_enc ${D}${bindir}/
}

FILES_${PN} += "${bindir}/utest_jpeg_sw_enc"
TARGET_CC_ARCH += "${LDFLAGS}"

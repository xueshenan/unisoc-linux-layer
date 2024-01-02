DESCRIPTION = "Unisoc jpeg module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "lib"
DEPENDS = ""
RDEPENDS_${PN} = ""
PV = "0.1"
PR = "r0"
PROVIDES = "libjpegencswsprd"

inherit get_release_mode

JPEG_SRC_DIR = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/jpeg/sw/enc/"
JPEG_INSTALL_DIR = "${DEPLOY_OUT_DIR}/sprd_codecs/${PN}"
JPEG_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/sprd_codecs/${PN}"
export JPEG_SRC_MODE="${@get_release_mode(d, "${JPEG_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("JPEG_SRC_MODE", "customer", "${JPEG_RELEASE_DIR}", "${JPEG_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


do_compile () {
    if [ ${JPEG_SRC_MODE} != "customer" ]; then
        make -C ${S} OBJ_DIR=${B}
    fi
}

do_install_prepend() {
    if [ ${JPEG_SRC_MODE} != "customer" ]; then
        rm -rf ${JPEG_INSTALL_DIR}/libjpegenc_sw_sprd.so
        install -d ${JPEG_INSTALL_DIR}/
        install -m 0777 ${B}/libjpegenc_sw_sprd.so ${JPEG_INSTALL_DIR}/
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libjpegenc_sw_sprd.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/libjpegenc_sw_sprd.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

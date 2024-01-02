DESCRIPTION = "Unisoc codecs module - vp9 hw encoder"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "lib"
DEPENDS = ""
RDEPENDS_${PN} = ""
PV = "0.1"
PR = "r0"
PROVIDES = "libomxvp9enchwsprd"

inherit get_release_mode

VCODEC_SRC_DIR = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/vp9_mix/enc/hw/"
VCODEC_INSTALL_DIR = "${DEPLOY_OUT_DIR}/sprd_codecs/${PN}"
VCODEC_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/sprd_codecs/${PN}"
export VCODEC_SRC_MODE="${@get_release_mode(d, "${VCODEC_SRC_DIR}")}"
EXTERNALSRC = "${@bb.utils.contains("VCODEC_SRC_MODE", "customer", "${VCODEC_RELEASE_DIR}", "${VCODEC_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export SYS_ROOT = "${WORKDIR}/recipe-sysroot"

export TARGET_VSP_PLATFORM = "${VSP_PLATFORM}"

export TARGET_VPP_PLATFORM = "${VPP_PLATFORM}"

export TARGET_BOARD_PLATFORM = "${BOARD_PLATFORM}"

export TARGET_BIA_SUPPORT = "${BIA_SUPPORT}"

export SUPPORT_AFBC = "${AFBC}"

do_compile () {
    if [ ${VCODEC_SRC_MODE} != "customer" ]; then
        make -C ${S} OBJ_DIR=${B}
    fi
}

do_install_prepend() {
    if [ ${VCODEC_SRC_MODE} != "customer" ]; then
        rm -rf ${VCODEC_INSTALL_DIR}/libomx_vp9enc_hw_sprd.so
        install -d ${VCODEC_INSTALL_DIR}/
        install -m 0777 ${B}/libomx_vp9enc_hw_sprd.so ${VCODEC_INSTALL_DIR}/
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libomx_vp9enc_hw_sprd.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/libomx_vp9enc_hw_sprd.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

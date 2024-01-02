inherit get_release_mode

GNSS_SRC_DIR = "${OEROOT}/source/unisoc/modules/gnss/gnssmgt"
GNSS_INSTALL_DIR = "${DEPLOY_OUT_DIR}/gnss"
GNSS_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/gnss/gnssmgt"

export GNSS_SRC_MODE="${@get_release_mode(d, "${GNSS_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("GNSS_SRC_MODE", "customer", "${GNSS_RELEASE_DIR}", "${GNSS_SRC_DIR}", d)}"

GNSS_CHPID_GE2    = "${@bb.utils.contains_any('MACHINE', 'sl8521e-base sl8521e-smartcoreboard sl8521e-3in1 sl8541e-3in1 sl8541e-smartpen64 sl8541e-smartpen64-wifionly sl8541e-smartpen32 sl8541e-smartpen32-wifionly sl8581e-3in1 sc9863a-1h10', 'ge2', '', d)}"
export GNSS_CHPID = "${@bb.utils.contains_any('MACHINE', 't710-smartcoreboard', 'marlin3lite', '${GNSS_CHPID_GE2}' ,d)}"

export LIB_PATH = "${@bb.utils.contains('TARGET_ARCH', 'aarch64', '64bit', '32bit', d)}"

do_compile () {
    if [ ${GNSS_SRC_MODE} != "customer" ]; then
        make  clean
        make
    fi
}

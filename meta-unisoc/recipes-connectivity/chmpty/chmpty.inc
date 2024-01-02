inherit get_release_mode

CHM_SRC_DIR = "${OEROOT}/source/unisoc/connectivity/radio/chmpty"
CHM_INSTALL_DIR = "${DEPLOY_OUT_DIR}/connectivity/radio/chmpty"
CHM_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/connectivity/radio/chmpty"

export CHM_SRC_MODE = "${@get_release_mode(d, "${CHM_SRC_DIR}")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("CHM_SRC_MODE", "customer", "${CHM_RELEASE_DIR}", "${CHM_SRC_DIR}", d)}"

do_compile () {
    if [ ${CHM_SRC_MODE} != "customer" ]; then
        make clean
        make
    fi
}

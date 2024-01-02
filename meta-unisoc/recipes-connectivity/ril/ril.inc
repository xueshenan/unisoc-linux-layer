inherit get_release_mode copy-symbols

RIL_SRC_DIR = "${OEROOT}/source/unisoc/proprietories/radio/ril"
RIL_INSTALL_DIR = "${DEPLOY_OUT_DIR}/radio/ril"
RIL_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/radio/ril"

export RIL_SRC_MODE = "${@get_release_mode(d, "${RIL_SRC_DIR}")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("RIL_SRC_MODE", "customer", "${RIL_RELEASE_DIR}", "${RIL_SRC_DIR}", d)}"

do_compile () {
    if [ ${RIL_SRC_MODE} != "customer" ]; then
        make clean
        make
    fi
}

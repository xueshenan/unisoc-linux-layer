inherit get_release_mode

RADIO_SRC_DIR = "${OEROOT}/source/unisoc/proprietories/radio"
RADIO_INSTALL_DIR = "${DEPLOY_OUT_DIR}/radio"
RADIO_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/radio"

export RADIO_SRC_MODE = "${@get_release_mode(d, "${RADIO_SRC_DIR}/libatci")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("RADIO_SRC_MODE", "customer", "${RADIO_RELEASE_DIR}", "${RADIO_SRC_DIR}", d)}"

do_compile () {
    if [ ${RADIO_SRC_MODE} != "customer" ]; then
        make clean
        make
    fi
}

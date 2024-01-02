inherit get_release_mode

TELE_SRC_DIR = "${OEROOT}/source/unisoc/proprietories/telephony"
TELE_INSTALL_DIR = "${DEPLOY_OUT_DIR}/telephony"
TELE_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/telephony"

export TELE_SRC_MODE = "${@get_release_mode(d, "${TELE_SRC_DIR}/atRouter")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("TELE_SRC_MODE", "customer", "${TELE_RELEASE_DIR}", "${TELE_SRC_DIR}", d)}"

do_compile () {
    if [ ${TELE_SRC_MODE} != "customer" ]; then
        make clean
        make
    fi
}

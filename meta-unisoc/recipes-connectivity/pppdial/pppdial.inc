inherit get_release_mode

PPPDIAL_SRC_DIR = "${OEROOT}/source/unisoc/proprietories/telephony"
PPPDIAL_INSTALL_DIR = "${DEPLOY_OUT_DIR}/telephony"
PPPDIAL_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/telephony"

export PPPDIAL_SRC_MODE = "${@get_release_mode(d, "${PPPDIAL_SRC_DIR}/pppdial")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("PPPDIAL_SRC_MODE", "customer", "${PPPDIAL_RELEASE_DIR}", "${PPPDIAL_SRC_DIR}", d)}"

do_compile () {
    if [ ${PPPDIAL_SRC_MODE} != "customer" ]; then
        make clean
        make WORKDIR=${WORKDIR}
    fi
}

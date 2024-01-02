inherit get_release_mode

APN_SRC_DIR = "${OEROOT}/source/unisoc/connectivity/telephony/apn"
APN_INSTALL_DIR = "${DEPLOY_OUT_DIR}/telephony/apn"
APN_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/telephony/apn"

export APN_SRC_MODE = "${@get_release_mode(d, "${APN_SRC_DIR}/xmlParser")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("APN_SRC_MODE", "customer", "${APN_RELEASE_DIR}", "${APN_SRC_DIR}", d)}"

do_compile () {
    if [ ${APN_SRC_MODE} != "customer" ]; then
        make clean
        make WORKDIR=${WORKDIR}
    fi
}

inherit get_release_mode

CMCCDMSDK_SRC_DIR = "${OEROOT}/source/unisoc/operator_dm/operatordm_sdk/cmccdmsdk"
CMCCDMSDK_INSTALL_DIR = "${DEPLOY_OUT_DIR}/cmccdmsdk"
CMCCDMSDK_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/cmccdmsdk"

export CMCCDMSDK_SRC_MODE="${@get_release_mode(d, "${CMCCDMSDK_SRC_DIR}")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("CMCCDMSDK_SRC_MODE", "customer", "${CMCCDMSDK_RELEASE_DIR}", "${CMCCDMSDK_SRC_DIR}", d)}"

do_compile () {
     if [ ${CMCCDMSDK_SRC_MODE} != "customer" ]; then
         make clean
         make
     fi
}

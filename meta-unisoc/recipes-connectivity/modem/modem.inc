inherit get_release_mode

MODEM_SRC_DIR = "${OEROOT}/source/unisoc/connectivity/modem/modem_control"
MODEM_INSTALL_DIR = "${DEPLOY_OUT_DIR}/connectivity/modem/modem_control"
MODEM_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/connectivity/modem/modem_control"

export MODEM_SRC_MODE="${@get_release_mode(d, "${MODEM_SRC_DIR}")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("MODEM_SRC_MODE", "customer", "${MODEM_RELEASE_DIR}", "${MODEM_SRC_DIR}", d)}"

do_compile () {
     if [ ${MODEM_SRC_MODE} != "customer" ]; then
         make clean
         make
     fi
}

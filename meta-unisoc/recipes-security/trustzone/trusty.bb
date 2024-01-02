DESCRIPTION = "Unisoc Trusty TEE"
PV = "1.0"
PR = "r0"

### INCLUDE DEPENDENT CONFIGS ###
include configs/${MACHINE}.inc

### CODE PATH AND PATCHE ###
EXTERNALSRC = "${OEROOT}/source/unisoc/proprietories/security/trusty"

#### LICENSE ###
LICENSE = "CLOSED"

#### CUSTOMER MODE CONFIGS ###
inherit get_release_mode
TRUSTY_SRC_DIR = "${OEROOT}/source/unisoc/proprietories/security/trusty/lk/trusty"
TRUSTY_INSTALL_DIR = "${DEPLOY_OUT_DIR}/trusty"
TRUSTY_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/trusty"
export TRUSTY_SRC_MODE = "${@get_release_mode(d,"${TRUSTY_SRC_DIR}")}"
EXTERNALSRC_BUILD = "${@bb.utils.contains("TRUSTY_SRC_MODE", "customer", "${TRUSTY_RELEASE_DIR}", "", d)}"

### BUILD CONFIGS ###
BUILD_VARS = " \
    TOOL_CHAIN_ROOT=${OEROOT}/source/unisoc/proprietories/security \
    CFLAGS= \
    NOT_VERIFY_MODEM=${NOT_VERIFY_MODEM} \
    SML_MEM_ADDR=${SML_MEM_ADDR} \
    SML_MEM_SIZE=${SML_MEM_SIZE} \
    WITH_TEECFG_CUSTOM=${TEECFG_CUSTOM} \
    TEECFG_MEM_ADDR=${TEECFG_MEM_ADDR} \
    TEECFG_MEM_SIZE=${TEECFG_MEM_SIZE} \
    DEFAULT_PROJECT=${TRUSTY_DEFAULT_PROJECT} \
    BOARD_TEE_64BIT=${TEE_64BIT} \
    TOS_MEM_ADDR=${TOS_MEM_ADDR} \
    TOS_MEM_SIZE=${TOS_MEM_SIZE} \
    TRUSTY_PRODUCTION_VERSION=${TRUSTY_PRODUCTION_VERSION} \
    TOS_TARGET_DTS=${TOS_TARGET_DTS} \
    CONFIG_SPRD_FIREWALL=${CONFIG_SPRD_FIREWALL} \
    GLP_PROJECT=${GLP_PROJECT} \
    SDK=1 \
"

### BUILD COMMANDS ###
inherit deploy
inherit sign_unisoc_binary
inherit pythonnative
UNISOC_SIGN_ENABLE ?= "no"
DEPENDS += "unisoc-sign-native"
DEPENDS += "python-pycrypto-native"

do_compile () {
	if [ ${TRUSTY_SRC_MODE} != "customer" ]; then
		make -C ${S}/vendor/sprd/build ${BUILD_VARS} TOP=${S} BUILDROOT=${B}
	fi
}

do_install_prepend () {
	if [ ${TRUSTY_SRC_MODE} != "customer" ]; then
		rm -rf ${TRUSTY_INSTALL_DIR}
		install -d ${TRUSTY_INSTALL_DIR}
		install ${B}/lk.bin ${TRUSTY_INSTALL_DIR}
	fi
}

do_install () {
    if [ -f "${B}/lk.bin" ]; then
        install -d ${DEPLOY_DIR_IMAGE}/
        install ${B}/lk.bin ${DEPLOY_DIR_IMAGE}/tos.bin
        sign_dynamic_ta "${DEPLOY_DIR_IMAGE}"
        sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/tos.bin"
    fi
}

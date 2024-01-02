# Class for generating signed UNISOC binaries.
#
# Configuration variables used by this class:
# UNISOC_SIGN_CONFIG
#           The path(default value is config) contains keys&version configs.
# UNISOC_SIGN_PAYLOAD
#           1, Add payload hash when secure boot is disabled
#           0, Payload hash isn't required when secure boot is enabled
# UNISOC_SIGN_REMOVE_ORIGIN
#           1, remove the original signing binary
#           0, keep the original signing binary

inherit sanity

UNISOC_SIGN_CONFIG = 'config/'
CFGPATH = "${STAGING_BINDIR_NATIVE}/${UNISOC_SIGN_CONFIG}"
UNISOC_SIGN_PAYLOAD = "${@bb.utils.contains('SECBOOT_ENABLE','sec','0','1',d)}"
UNISOC_SIGN_REMOVE_ORIGIN = '0'
BSP_SPRD_SECUREBOOT_ALGORITHM ?= 'rsa2048'
SIGN_PADDING = "${@bb.utils.contains('UNISOC_SIGN_PSS','pss','6','1',d)}"
SECURE_WCN_GNSS = "${@bb.utils.contains('SECURE_WCN_GNSS_ENABLE','true','true','false',d)}"


sign_unisoc_bin() {
        if [ "${UNISOC_SIGN_ENABLE}" = "yes" ]; then
            if [ "${SECBOOT_ENABLE}" = "sec" ]; then
                echo "sign_image ${1} ${CFGPATH} ${BSP_SPRD_SECUREBOOT_ALGORITHM} ${SIGN_PADDING}"
                ${STAGING_BINDIR_NATIVE}/sprd_sign sign_image --image ${1} --config_dir ${CFGPATH} --algorithm ${BSP_SPRD_SECUREBOOT_ALGORITHM} --rsa_padding ${SIGN_PADDING} --wcn_gnss_flag ${SECURE_WCN_GNSS}
            else
                # add comment for test
                echo "insert_image_header ${1} ${CFGPATH}"
                ${STAGING_BINDIR_NATIVE}/sprd_sign insert_image_header --image ${1} --config_dir ${CFGPATH} --wcn_gnss_flag ${SECURE_WCN_GNSS}
            fi
        fi
}

sign_dynamic_ta() {
        DEPLOY_DIR_IMAGE=${1}
        UNISOC_TOOLS=${STAGING_BINDIR_NATIVE}
        if [ "${UNISOC_SIGN_ENABLE}" = "yes" ]; then
                echo "python ${UNISOC_TOOLS}/dynamicTA/genkey/main_process.py ${DEPLOY_DIR_IMAGE} ${UNISOC_TOOLS}"
                python ${UNISOC_TOOLS}/dynamicTA/genkey/main_process.py ${DEPLOY_DIR_IMAGE} ${UNISOC_TOOLS} ${BSP_SPRD_SECUREBOOT_ALGORITHM}
        fi
}

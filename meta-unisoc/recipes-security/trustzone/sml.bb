DESCRIPTION = "Unisoc SML(secure monitor loader)"

include configs/${MACHINE}.inc
include recipes-security/trustzone/sml/${SML_VERSION}/sml.inc

LICENSE ="CLOSED"
SECTION = "bin"
#PR = "r0"

inherit get_release_mode
SML_SRC_DIR = "${OEROOT}/source/unisoc/proprietories/security/arm-trusted-firmware/plat/sprd"
SML_INSTALL_DIR = "${DEPLOY_OUT_DIR}/sml"
SML_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/sml"
export SML_SRC_MODE = "${@get_release_mode(d,"${SML_SRC_DIR}")}"
EXTERNALSRC_BUILD = "${@bb.utils.contains("SML_SRC_MODE", "customer", "${SML_RELEASE_DIR}", "", d)}"

BUILD_VARS = " \
    TOOL_CHAIN_ROOT=${OEROOT}/source/unisoc/proprietories/security \
    SML_CONFIG=${SML_CONFIG} \
    SML_NO_CONSOLE=${SML_NO_CONSOLE} \
    TEE_CONFIG=${TEE_CONFIG} \
    SML_MEM_ADDR=${SML_MEM_ADDR} \
    SML_MEM_SIZE=${SML_MEM_SIZE} \
    TEECFG_CUSTOM=${TEECFG_CUSTOM} \
    TEECFG_MEM_ADDR=${TEECFG_MEM_ADDR} \
    TEECFG_MEM_SIZE=${TEECFG_MEM_SIZE} \
    TOS_MEM_ADDR=${TOS_MEM_ADDR} \
    TOS_MEM_SIZE=${TOS_MEM_SIZE} \
    USERDEBUG=${USERDEBUG} \
    UFS_INLINE_CRYPTO=${UFS_INLINE_CRYPTO} \
    SUPPORT_DVFS_LOGIC=${SUPPORT_DVFS_LOGIC} \
"

inherit sign_unisoc_binary
UNISOC_SIGN_ENABLE ?= "no"
DEPENDS += "unisoc-sign-native"

inherit deploy

do_compile () {
	if [ ${SML_SRC_MODE} != "customer" ]; then
		unset CFLAGS
		unset LDFLAGS
		make -C ${S} BUILD_BASE=${B} -f prebuild.mk ${BUILD_VARS}
	fi
}

do_install_prepend () {
	if [ ${SML_SRC_MODE} != "customer" ]; then
		rm -rf ${SML_INSTALL_DIR}
		install -d ${SML_INSTALL_DIR}
		if echo ${SML_CONFIG} |grep -q "arm32" ; then
			install -d ${DEPLOY_DIR_IMAGE}/
			install ${B}/bl32.bin ${SML_INSTALL_DIR}
		else
			install -d ${DEPLOY_DIR_IMAGE}/
			install ${B}/bl31.bin ${SML_INSTALL_DIR}
		fi
	fi

}

do_install () {
    if echo ${SML_CONFIG} | grep -q "arm32" ; then
	install -d  ${DEPLOY_DIR_IMAGE}/
        install ${B}/bl32.bin ${DEPLOY_DIR_IMAGE}/sml.bin
    else
	install -d  ${DEPLOY_DIR_IMAGE}/
        install ${B}/bl31.bin ${DEPLOY_DIR_IMAGE}/sml.bin
    fi

    sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/sml.bin"
}

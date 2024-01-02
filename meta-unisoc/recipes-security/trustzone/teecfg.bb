DESCRIPTION = "Unisoc TEECFG"
PV = "1.0"
PR = "r0"

### INCLUDE DEPENDENT CONFIGS ###
include configs/${MACHINE}.inc

#### LICENSE ###
LICENSE = "Unisoc-General-1.0"

#### CONFIGS ###
TEECFG_XML_DIR = "${OEROOT}/layers/meta-unisoc/recipes-security/trustzone/teecfg"
TEECFG_PLATFORM_XML_PATH = "${TEECFG_XML_DIR}/${UNISOC_SOC}/common"
TEECFG_BOARD_XML_PATH = "${TEECFG_XML_DIR}/${UNISOC_SOC}/${MACHINE}"
TEECFG_TEMP_DIR = "${WORKDIR}/teecfg-${PV}"
TEECFG_INTERMEDIATE_XML_PATH = "${TEECFG_TEMP_DIR}/xml"
TEECFG_XML_OVERLAY_PARAMS = "\
	-i ${TEECFG_PLATFORM_XML_PATH} \
	-b ${TEECFG_BOARD_XML_PATH} \
	--dtb ${SML_TARGET_DTB_PATH} \
	--dtbo ${SML_TARGET_DTBO_PATH} \
	-o ${TEECFG_INTERMEDIATE_XML_PATH} \
"

### BUILD COMMANDS ###
inherit deploy
inherit sign_unisoc_binary
inherit pythonnative
UNISOC_SIGN_ENABLE ?= "no"
DEPENDS += "unisoc-sign-native"

do_compile () {
	if [ -d "${TEECFG_INTERMEDIATE_XML_PATH}" ]; then
		rm -rf ${TEECFG_INTERMEDIATE_XML_PATH}
	fi

	install -d ${DEPLOY_DIR_IMAGE}
	install -d ${TEECFG_INTERMEDIATE_XML_PATH}
	install -m 0777 ${OEROOT}/prebuilts/scripts/teecfg/teecfg_xml_overlay.sh ${TEECFG_TEMP_DIR}
	install -m 0777 ${OEROOT}/prebuilts/scripts/teecfg/teecfg_tool ${TEECFG_TEMP_DIR}

	${TEECFG_TEMP_DIR}/teecfg_xml_overlay.sh ${TEECFG_XML_OVERLAY_PARAMS}
	${TEECFG_TEMP_DIR}/teecfg_tool -g -i ${TEECFG_INTERMEDIATE_XML_PATH} -o ${DEPLOY_DIR_IMAGE}/teecfg.bin
}

do_install () {
	if [ -f "${DEPLOY_DIR_IMAGE}/teecfg.bin" ]; then
		sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/teecfg.bin"
	fi
}

deltask rm_work

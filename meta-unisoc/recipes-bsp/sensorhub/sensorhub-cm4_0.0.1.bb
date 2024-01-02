DESCRIPTION = "Uniso sensorhub-cm4 module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
PV = "0.1"
PR = "r0"
PROVIDES = "sensorhub-cm4"
#DEPENDS = ""
#RDEPENDS_${PN} = ""

inherit get_release_mode

CM4_SRC_DIR = "${OEROOT}/source/bsp/sensorhub/public/yocto_compile"
CM4_INSTALL_DIR = "${DEPLOY_OUT_DIR}/sensorhub"
CM4_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/sensorhub"
export CM4_SRC_MODE="${@get_release_mode(d, "${CM4_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("CM4_SRC_MODE", "customer", "${CM4_RELEASE_DIR}", "${CM4_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BSP_SENSORHUB_PROJECT="${SENSOR_PROJECT}"
export BSP_SENSORHUB_BOARD="${SENSOR_BOARD}"
export BSP_SENSORHUB_BIN_BOARD="${UNISOC_SOC}"

inherit sign_unisoc_binary
UNISOC_SIGN_ENABLE ?= "no"
DEPENDS += "unisoc-sign-native"

do_compile () {
    if [ ${CM4_SRC_MODE} != "customer" ]; then
        make clean
        make 
   fi
}

do_install_prepend() {
    if [ ${CM4_SRC_MODE} != "customer" ]; then
        rm -rf ${CM4_INSTALL_DIR}/sensorhub-cm4
        install -d ${CM4_INSTALL_DIR}/
        install -m 0777 ${B}/sensorhub-cm4 ${CM4_INSTALL_DIR}/
    fi
}

do_install () {
    install -d ${DEPLOY_DIR_IMAGE}/ch
    install -m 0644 ${B}/../build/${SENSOR_PROJECT}/${SENSOR_PROJECT}.bin ${DEPLOY_DIR_IMAGE}/ch/
    sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/ch/${SENSOR_PROJECT}.bin"
    install -d ${D}${bindir}/
    install -m 0777 ${B}/sensorhub-cm4 ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

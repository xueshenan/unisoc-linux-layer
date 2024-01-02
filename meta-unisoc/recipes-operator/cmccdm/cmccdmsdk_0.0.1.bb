DESCRIPTION = "CMCC DM SDK"
LICENSE = "CLOSED"
PV = "0.1"
PR = "r0"

require cmccdmsdk.inc

SECTION = "libs"

PROVIDES = "cmccdmsdk"

EXTERNALSRC = "${EXTERNALSRC_DIR}"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}"

do_install_prepend () {
    if [ ${CMCCDMSDK_SRC_MODE} != "customer" ]; then
        rm -rf ${CMCCDMSDK_INSTALL_DIR}
        install -d ${CMCCDMSDK_INSTALL_DIR}/sdksrc/sdk
        install -m 0777 ${B}/liblwm2msdkL.so ${CMCCDMSDK_INSTALL_DIR}
        install -m 0777 ${S}/sdksrc/sdk/lwm2msdk.h ${CMCCDMSDK_INSTALL_DIR}/sdksrc/sdk
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/liblwm2msdkL.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/sdksrc/sdk/lwm2msdk.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

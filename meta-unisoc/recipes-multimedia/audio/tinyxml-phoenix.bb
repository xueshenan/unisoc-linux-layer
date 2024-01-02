MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

SECTION = "libs"
#PV = "0.1"
#PR = "r0"

PROVIDES = "tinyxml-phoenix"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/external/tinyxml"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, tinyxml!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

do_compile () {
    make clean
    make
}

do_install_prepend () {
    if [ ${RADIO_SRC_MODE} != "customer" ]; then
        rm -rf ${RADIO_INSTALL_DIR}/libtinyxml
        install -d ${RADIO_INSTALL_DIR}/libtinyxml
        install -m 0777 ${S}/libtinyxml.so ${RADIO_INSTALL_DIR}/libtinyxml
        install -m 0777 ${S}/*.h ${RADIO_INSTALL_DIR}/libtinyxml
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libtinyxml.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libtinyxml.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
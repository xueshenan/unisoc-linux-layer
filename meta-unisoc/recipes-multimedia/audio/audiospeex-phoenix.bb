MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

SECTION = "libs"
#DEPENDS = "utils"
#PV = "0.1"
#PR = "r0"

PROVIDES = "audiospeex-phoenix"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/external/speex"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audiospeex!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

do_compile () {
    make clean
    make
}

do_install_prepend () {
    if [ ${RADIO_SRC_MODE} != "customer" ]; then
        rm -rf ${RADIO_INSTALL_DIR}/libaudiospeex
        install -d ${RADIO_INSTALL_DIR}/libaudiospeex
        install -m 0777 ${S}/libaudiospeex.so ${RADIO_INSTALL_DIR}/libaudiospeex
        install -m 0777 ${S}/include/speex/*.h ${RADIO_INSTALL_DIR}/libaudiospeex

    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libaudiospeex.so ${D}${libdir}/
    #install -d ${D}${includedir}/
    #mkdir -p ${D}${includedir}/speex
    #install -m 0777 ${S}/include/speex/*.h ${D}${includedir}/speex/
}

FILES_${PN} += "${libdir}/libaudiospeex.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

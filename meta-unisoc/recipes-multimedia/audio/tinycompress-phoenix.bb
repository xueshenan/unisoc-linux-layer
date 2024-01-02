MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

SECTION = "libs"
#PV = "0.1"
#PR = "r0"

PROVIDES = "tinycompress-phoenix"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/external/tinycompress"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("**************************");
   bb.plain("*                  	  *");
   bb.plain("*  Hello, tinycompress!  *");
   bb.plain("*                        *");
   bb.plain("**************************");
}

do_compile () {
    make clean
    make
}

do_install_prepend () {
    if [ ${RADIO_SRC_MODE} != "customer" ]; then
        rm -rf ${RADIO_INSTALL_DIR}/libtinycompress
        install -d ${RADIO_INSTALL_DIR}/libtinycompress
        install -m 0755 ${S}/libtinycompress.so ${RADIO_INSTALL_DIR}/libtinycompress
        install -m 0755 ${S}/include/tinycompress/*.h ${RADIO_INSTALL_DIR}/libtinycompress
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libtinycompress.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/include/tinycompress/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libtinycompress.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
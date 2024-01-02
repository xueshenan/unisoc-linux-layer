MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

SECTION = "libs"

PROVIDES = "tinyalsa"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal/external/tinyalsa"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, tinyalsa!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

do_compile () {
    make clean
    make
}

do_install_prepend () {
    if [ ${RADIO_SRC_MODE} != "customer" ]; then
        rm -rf ${RADIO_INSTALL_DIR}/libtinyalsa
        install -d ${RADIO_INSTALL_DIR}/libtinyalsa
        install -m 0777 ${S}/libtinyalsa.so ${RADIO_INSTALL_DIR}/libtinyalsa
		install -m 0777 ${S}/tinycap ${RADIO_INSTALL_DIR}/libtinyalsa
		install -m 0777 ${S}/tinyplay ${RADIO_INSTALL_DIR}/libtinyalsa
		install -m 0777 ${S}/tinymix ${RADIO_INSTALL_DIR}/libtinyalsa
		install -m 0777 ${S}/tinypcminfo ${RADIO_INSTALL_DIR}/libtinyalsa
        install -m 0777 ${S}/include/tinyalsa/*.h ${RADIO_INSTALL_DIR}/libtinyalsa
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libtinyalsa.so ${D}${libdir}/
    install -d ${D}${bindir}/
    install -m 0777 ${S}/tinycap ${D}${bindir}/
    install -m 0777 ${S}/tinyplay ${D}${bindir}/
    install -m 0777 ${S}/tinymix ${D}${bindir}/
    install -m 0777 ${S}/tinypcminfo ${D}${bindir}/
    install -d ${D}${includedir}/
    mkdir -p ${D}${includedir}/tinyalsa
    install -m 0777 ${S}/include/tinyalsa/*.h ${D}${includedir}/tinyalsa/
}

FILES_${PN} += "${libdir}/libtinyalsa.so ${bindir}/tinycap ${bindir}/tinyplay ${bindir}/tinymix ${bindir}/tinypcminfo"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
SUMMARY = "audio_atci"
MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

DEPENDS = "chm-at"
RDEPENDS_${PN} = "chm-at"

PROVIDES = "audioatci"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/voicecall/libaudioatci"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("***********************");
   bb.plain("*                     *");
   bb.plain("*  Hello, voicecall!  *");
   bb.plain("*                     *");
   bb.plain("***********************");
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libaudioatci.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

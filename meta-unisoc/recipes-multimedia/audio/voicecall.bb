SUMMARY = "voicecall"
MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

DEPENDS = "audioatci"
RDEPENDS_${PN} = "audioatci"

PROVIDES = "voicecall"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/voicecall"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

export AUDUO_PLATFORM = "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'whale', 'normal', d)}"

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
    install -m 0777 ${B}/libvoicecall.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/voicecall.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

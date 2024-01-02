SUMMARY = "voicecall test"
MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

DEPENDS = "voicecall"
RDEPENDS_${PN} = "voicecall"

PROVIDES = "voicecalltest"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/voicecall/unitest"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("***************************");
   bb.plain("*                         *");
   bb.plain("*  Hello, voicecalltest!  *");
   bb.plain("*                         *");
   bb.plain("***************************");
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/voicecalltest ${D}${bindir}/
}


FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

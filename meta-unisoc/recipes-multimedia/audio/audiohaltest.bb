SUMMARY = "audiohal test"
MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

DEPENDS = "audiohal-phoenix"
RDEPENDS_${PN} = "audiohal-phoenix"

PROVIDES = "audiohaltest"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/hal_init"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audiohaltest!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/audiohaltest ${D}${bindir}/
}

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

deltask do_rm_work

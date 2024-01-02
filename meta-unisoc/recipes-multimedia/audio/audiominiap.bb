MEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "libs"

DEPENDS = "libatcommon voice"
RDEPENDS_${PN} = "libatcommon voice"

PROVIDES = "audiominiap"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiominiap"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiominiap"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, yoctoaudiominiap!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/atlib
    install -m 0777 ${S}/audiominiap.so ${D}${libdir}/atlib
}

FILES_${PN} += "${libdir}/atlib/audiominiap.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


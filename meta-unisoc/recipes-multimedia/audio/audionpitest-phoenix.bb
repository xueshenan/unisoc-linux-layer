MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

DEPENDS = "audionpi-phoenix tinyalsa-phoenix expat chm-at audioutil-phoenix"
RDEPENDS_${PN} = "audionpi-phoenix tinyalsa-phoenix expat chm-at audioutil-phoenix"

PROVIDES = "audionpitest-phoenix"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/npi"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audionpitest!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/audionpitest ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

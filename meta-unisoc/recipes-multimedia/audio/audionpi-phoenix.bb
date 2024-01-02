MEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "libs"

DEPENDS = "tinyalsa-phoenix expat chm-at audioutil-phoenix"
RDEPENDS_${PN} = "tinyalsa-phoenix expat chm-at audioutil-phoenix"

PROVIDES = "audionpi"

export AUDIO_PLATFORM = "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'whale', 'normal', d)}"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/npi/lib"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("***********************");
   bb.plain("*                     *");
   bb.plain("*  Hello, audionpi!  *");
   bb.plain("*                     *");
   bb.plain("***********************");
}

export AUDUO_PLATFORM_FLAG="${AUDIO_PLATFORM}"
export AUDIO_APLOOP_FLAG = "${AUDIO_APLOOP_SET}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    mkdir ${D}${libdir}/npidevice
    install -m 0777 ${S}/libaudionpi.so ${D}${libdir}/
    install -m 0777 ${S}/libaudionpi.so ${D}${libdir}/npidevice/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

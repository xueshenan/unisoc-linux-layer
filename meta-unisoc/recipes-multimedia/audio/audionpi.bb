MEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "libs"

DEPENDS = "tinyalsa expat atci audioutil"
RDEPENDS_${PN} = "tinyalsa expat atci audioutil"

PROVIDES = "audionpi"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/audionpi;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export AUDIO_PLATFORM_CFLAGS = "${AUDIO_PLATFORM}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audionpi!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

export AUDUO_PLATFORM_FLAG="${AUDIO_PLATFORM}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    mkdir ${D}${libdir}/npidevice
    install -m 0777 ${S}/libaudionpi.so ${D}${libdir}/
    install -m 0777 ${S}/libaudionpi.so ${D}${libdir}/npidevice/
    install -d ${D}${sysconfdir}/audio/
    install -d ${D}${sysconfdir}/audio/native_mmi
    install -m 0777 ${THISDIR}/files/native_mmi/audio_sample.pcm ${D}${sysconfdir}/audio/native_mmi/audio_sample.pcm
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


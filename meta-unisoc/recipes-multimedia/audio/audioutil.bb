MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "libs"

DEPENDS = "audiospeex expat"
RDEPENDS_${PN} = "audiospeex expat"

PROVIDES = "audioutil"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/audioutil;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audioutil!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libaudioutil.so ${D}${libdir}/
    install -d ${D}${includedir}/
    mkdir -p ${D}${includedir}/audio_utils
    install -m 0777 ${S}/include/audio_utils/resampler.h ${D}${includedir}/audio_utils/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


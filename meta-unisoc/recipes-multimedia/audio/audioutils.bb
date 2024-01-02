MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "libs"

DEPENDS = ""
RDEPENDS_${PN} = ""

PROVIDES = "audioutils"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/audioutils;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audioutils!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libaudioutils.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/audio_utils.h ${D}${includedir}
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


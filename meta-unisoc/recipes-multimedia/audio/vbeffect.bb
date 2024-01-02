MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "libs"

DEPENDS = "tinyalsa-phoenix expat audioutils"
RDEPENDS_${PN} = "tinyalsa-phoenix expat audioutils"

PROVIDES = "vbeffect"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/vbeffect;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, vbeffect!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libvbeffect.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


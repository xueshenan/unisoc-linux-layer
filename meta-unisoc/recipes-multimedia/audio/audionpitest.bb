MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

DEPENDS = "audionpi"
RDEPENDS_${PN} = "audionpi"

PROVIDES = "audionpitest"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/audionpitest;protocol=ssh;branch=unc_linux_trunk"
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


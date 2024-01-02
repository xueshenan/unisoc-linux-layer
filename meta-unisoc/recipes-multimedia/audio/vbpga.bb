MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "libs"

DEPENDS = "expat tinyalsa-phoenix audioutils"
RDEPENDS_${PN} = "expat tinyalsa-phoenix audioutils"

PROVIDES = "vbpga"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/vbpga;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, vbpga!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libvbpga.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


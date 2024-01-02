MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "libs"

DEPENDS = "audioutils expat nvexchange vbeffect vbpga"
RDEPENDS_${PN} = "audioutils expat nvexchange vbeffect vbpga"

PROVIDES = "audioparamteser"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/audioparamteser;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audioparamteser!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libaudioparamteser.so ${D}${libdir}/
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libaudioparamteser.so ${D}${libdir}/npidevice/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


DESCRIPTION = "Unisoc vdsp library"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "lib"
PV = "0.1"
PR = "r0"
PROVIDES = "dspclient"
DEPENDS = "libvdspservice"
RDEPENDS_${PN} = "libvdspservice"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/dspclient ${D}${bindir}/
}

FILES_${PN} += "${bindir}/dspclient"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

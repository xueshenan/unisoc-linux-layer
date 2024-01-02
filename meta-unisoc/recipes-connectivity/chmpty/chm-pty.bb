DESCRIPTION = "Unisoc chmpty module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require chmpty.inc

SECTION = "libs"
#DEPENDS = "utils"
#PV = "0.1"
#PR = "r0"
#RDEPENDS_${PN} = "utils"
DEPENDS = "iniparser"
RDEPENDS_${PN} = "iniparser"
PROVIDES = "chm-pty"


EXTERNALSRC = "${EXTERNALSRC_DIR}/chm-pty"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/chm-pty"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, chmpty!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}


do_install_prepend () {
    if [ ${CHM_SRC_MODE} != "customer" ]; then
        rm -rf ${CHM_INSTALL_DIR}/chm-pty
        install -d ${CHM_INSTALL_DIR}/chm-pty
        install -m 0777 ${S}/libchm-pty.so ${CHM_INSTALL_DIR}/chm-pty
        install -m 0777 ${S}/chm-pty.h ${CHM_INSTALL_DIR}/chm-pty
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libchm-pty.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libchm-pty.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc chmpty module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require chmpty.inc

SECTION = "libs"
#PV = "0.1"
#PR = "r0"
DEPENDS = "chm-pty engpc"
RDEPENDS_${PN} = "chm-pty engpc"
PROVIDES = "chm-at"


EXTERNALSRC = "${EXTERNALSRC_DIR}/chm-at"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/chm-at"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, chmpty!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}


do_install_prepend () {
    if [ ${CHM_SRC_MODE} != "customer" ]; then
        rm -rf ${CHM_INSTALL_DIR}/chm-at
        install -d ${CHM_INSTALL_DIR}/chm-at
        install -m 0777 ${S}/libchm-at.so ${CHM_INSTALL_DIR}/chm-at
        install -m 0777 ${S}/*.h ${CHM_INSTALL_DIR}/chm-at
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${libdir}/npidevice/
    install -m 0777 ${S}/libchm-at.so ${D}${libdir}/
    install -m 0777 ${S}/libchm-at.so ${D}${libdir}/npidevice/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libchm-at.so"
FILES_${PN} += "${libdir}/npidevice/libchm-at.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

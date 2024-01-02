DESCRIPTION = "libconnman and client based on this lib"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Closed"
SECTION = "libs"
DEPENDS = "utils"
LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} = "utils"
PROVIDES = "smartlink-client"
PV = "0.1"
PR = "r0"

EXTERNALSRC = "${OEROOT}/source/unisoc/connman/smartlink_client"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/connman/smartlink_client"

NATIVE_SYSTEMD_SUPPORT = "1"

python do_pre_compile() {
    bb.plain("********************");
    bb.plain("*                  *");
    bb.plain("*  Hello, smartlink-client!  *");
    bb.plain("*                  *");
    bb.plain("********************");
}

do_compile () {
         make clean
         make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libsmartlink-client.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libsmartlink-client.so"
FILES_${PN} += "${includedir}/*.h"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


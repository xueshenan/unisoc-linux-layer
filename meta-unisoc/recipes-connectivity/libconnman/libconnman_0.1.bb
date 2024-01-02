DESCRIPTION = "libconnman and client based on this lib"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "ApacheLicenseVersion2.0"
SECTION = "libs"
DEPENDS = "dbus glib-2.0"
LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} = "dbus glib-2.0"
PROVIDES = "libconnman"
PV = "0.1"
PR = "r0"

inherit pkgconfig cmake

EXTERNALSRC = "${OEROOT}/source/unisoc/connman/libconnman"
#EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/connman/libconnman"
EXTERNALSRC_BUILD = "${WORKDIR}/build/libconnman"
export SUPPORT_BIND_PROCESS_TO_PDP_FLAG= "${BIND_PROCESS_TO_PDP}"
NATIVE_SYSTEMD_SUPPORT = "1"


do_install () {
    install -d ${D}${includedir}/
    install -m 0644 ${S}/include/*.h ${D}${includedir}/
    install -d ${D}${libdir}/
    install -m 0777 ${B}/*.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${includedir}/*.h"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
TARGET_CC_ARCH += "${SUPPORT_BIND_PROCESS_TO_PDP_FLAG}"

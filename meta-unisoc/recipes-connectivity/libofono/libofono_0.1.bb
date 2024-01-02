DESCRIPTION = "libofono and client based on this lib"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "bins"
DEPENDS = "dbus glib-2.0"
LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} = "dbus glib-2.0"
PROVIDES = "libofono"
PV = "0.1"
PR = "r0"

inherit pkgconfig cmake

export N6P_5G_SET_CFLAGS = "${N6P_5G_SET}"
LIBMISCDATA_SECSET ?= "${@bb.utils.contains('STORAGE_TYPE','nand','-DCONFIG_NAND','',d)}"
export CONFIG_NAND_FLAG="${LIBMISCDATA_SECSET}"
EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/telephony/libapi/libofono"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/connectivity/telephony/libapi/libofono"

NATIVE_SYSTEMD_SUPPORT = "1"


do_install () {
    install -d ${D}${includedir}/
    install -m 0644 ${S}/include/*.h ${D}${includedir}/
    install -d ${D}${libdir}/
    install -m 0777 ${S}/*.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${includedir}/*.h"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
TARGET_CC_ARCH += "${N6P_5G_SET_CFLAGS}"
TARGET_CC_ARCH += "${CONFIG_NAND_FLAG}"

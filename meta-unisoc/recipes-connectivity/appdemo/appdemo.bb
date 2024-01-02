DESCRIPTION = "app demo"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "bins"

LIC_FILES_CHKSUM = ""
PROVIDES = "appdemo"
PV = "0.1"
PR = "r0"

DEPENDS = "dbus glib-2.0 libofono iniparser"
RDEPENDS_${PN} = "dbus glib-2.0 libofono iniparser"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/telephony/libapi/app-demo"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/connectivity/telephony/libapi/app-demo"


NATIVE_SYSTEMD_SUPPORT = "1"


do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
	install -d ${D}${bindir}/
	install -m 0755 ${S}/app_demo ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"
export N6P_5G_SET_CFLAGS = "${N6P_5G_SET}"

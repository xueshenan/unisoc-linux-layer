DESCRIPTION = "gnss-adapter"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
DEPENDS = ""
LIC_FILES_CHKSUM = "file://COPYING;md5=3ca06ce07025ae20c1099892b2593dd9"
PV = "0.1"
PR = "r0"
PROVIDES = "gnss-adapter"

DEPENDS = "gnss dbus dbushelper"
RDEPENDS_${PN} = "gnss dbus dbushelper"

inherit update-rc.d update-alternatives

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/gnss-adapter"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# make clean
do_compile () {

    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}/${sysconfdir}/dbus-1/system.d
    install -m 0755 ${THISDIR}/files/config/gps-dbus.conf ${D}/${sysconfdir}/dbus-1/system.d
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${THISDIR}/files/default/init_gnss-adapter ${D}${sysconfdir}/init.d/gnss-adapter
    install -d ${D}${sysconfdir}/default
    install -m 0755 ${THISDIR}/files/default/default_gnss-adapter ${D}${sysconfdir}/default/gnss-adapter.default
    install -d ${D}${sbindir}
    install -m 0755 ${S}/gnss-adapter ${D}${sbindir}
}

do_install_append () {
     install -d ${D}${includedir}/${PN}
     install -m 0644 ${S}/gnss_adapter.h ${D}${includedir}/${PN}
}

TARGET_CC_ARCH += "${LDFLAGS}"

INITSCRIPT_NAME = "gnss-adapter"
INITSCRIPT_PARAMS = "start 35 3 4 5 . stop 35 0 6 ."

ALTERNATIVE_${PN} = "gnss-adapter-defaults"
ALTERNATIVE_LINK_NAME[gnss-adapter-defaults] = "${sysconfdir}/default/gnss-adapter"
ALTERNATIVE_TARGET[gnss-adapter-defaults] = "${sysconfdir}/default/gnss-adapter.default"

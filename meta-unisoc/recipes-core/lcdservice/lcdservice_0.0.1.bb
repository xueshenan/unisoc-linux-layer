DESCRIPTION = "lcd control service"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "UNISOC"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a709ac98a9d8606af72d15cafa6a147"
PV = "0.1"
PR = "r0"
PROVIDES = "lcdservice"

DEPENDS = "dbus dbushelper powermanager"
RDEPENDS_${PN} = "dbus dbushelper"

inherit systemd update-rc.d pkgconfig

SRC_URI = "\
            file://lcdservice.service \
            file://init_lcdservice \
            file://default_lcdservice \
            file://lcdservice.conf \
"

EXTERNALSRC = "${OEROOT}/source/unisoc/powerfw/lcdservice"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "lcdservice"
INITSCRIPT_PARAMS_${PN} = "start 110 3 4 5 . stop 110 0 6 ."

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "lcdservice.service"

export SYS_ROOT = "${WORKDIR}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/lcdservice ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/lcdservice.conf ${D}/etc/dbus-1/system.d/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init_lcdservice ${D}/${sysconfdir}/init.d/lcdservice

    install -d ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/default_lcdservice ${D}/${sysconfdir}/default/lcdservice.default

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/lcdservice.service ${D}${systemd_unitdir}/system
}

do_install_append () {
     install -d ${D}${includedir}/${PN}
     install -m 0644 ${S}/${PN}.h ${D}${includedir}/${PN}
}

ALTERNATIVE_${PN} = "lcdservice-defaults"
ALTERNATIVE_LINK_NAME[lcdservice-defaults] = "${sysconfdir}/default/lcdservice"
ALTERNATIVE_TARGET[lcdservice-defaults] = "${sysconfdir}/default/lcdservice.default"

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"
deltask do_rm_work

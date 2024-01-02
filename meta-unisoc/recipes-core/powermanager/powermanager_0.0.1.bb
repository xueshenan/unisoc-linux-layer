DESCRIPTION = "power manager framework component"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=41f3f5ec746235af1ca119d0df2e3231"
PV = "0.1"
PR = "r0"
PROVIDES = "powermanager"

DEPENDS = "iniparser dbus dbushelper"
RDEPENDS_${PN} = "iniparser dbus dbushelper pm-utils"

inherit systemd update-rc.d pkgconfig

SRC_URI = "\
            file://powermanager.service \
            file://init_powermanager \
            file://default_powermanager \
            file://powermanager.ini \
            file://powermanager.conf \
"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "powermanager"
INITSCRIPT_PARAMS_${PN} = "defaults 108"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "powermanager.service"

export SYS_ROOT = "${WORKDIR}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/powermanager ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/powermanager.conf ${D}/etc/dbus-1/system.d/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init_powermanager ${D}/${sysconfdir}/init.d/powermanager

    install -d ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/default_powermanager ${D}/${sysconfdir}/default/powermanager

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/powermanager.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/powermanager/
    install -m 0755 ${WORKDIR}/powermanager.ini ${D}${sysconfdir}/powermanager/powermanager.ini
}

do_install_append () {
     install -d ${D}${includedir}/${PN}
     install -m 0644 ${S}/${PN}.h ${D}${includedir}/${PN}
}

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"
deltask do_rm_work

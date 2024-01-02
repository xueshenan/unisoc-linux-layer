DESCRIPTION = "suspendblocker-service"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
PV = "0.1"
PR = "r0"
PROVIDES = "suspendblocker"
DEPENDS = "iniparser pm dbus"
RDEPENDS_${PN} = "iniparser pm dbus"
inherit systemd update-rc.d

SRC_URI = "\
            file://suspendblocker.service \
            file://suspendblocker-init \
            file://autosuspend.ini \
            file://suspendblocker.conf \
"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "suspendblocker-init"
INITSCRIPT_PARAMS_${PN} = "start 108 3 4 5 . stop 108 0 6 ."

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "suspendblocker.service"
export SYS_ROOT = "${WORKDIR}"
export MODEM_CTRL_REMOVE_MODEM_FLAG = "${MODEM_CTRL_REMOVE_MODEM}"
do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/suspendblocker ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/suspendblocker.conf ${D}/etc/dbus-1/system.d/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/suspendblocker-init ${D}/${sysconfdir}/init.d/suspendblocker-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/suspendblocker.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/suspendblocker/
    install -m 0755 ${WORKDIR}/autosuspend.ini ${D}${sysconfdir}/suspendblocker/autosuspend.ini
}

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"


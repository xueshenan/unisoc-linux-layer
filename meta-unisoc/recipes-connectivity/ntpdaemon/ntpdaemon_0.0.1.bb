DESCRIPTION = "ntp daemon"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=1b4811463151eac740e51e5a0cb6b487"
SECTION = "bins"
PROVIDES = "ntpdaemon"
DEPENDS = "dbus glib-2.0 libnetcon"
RDEPENDS_${PN} = "dbus glib-2.0 libnetcon"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/ntp_daemon"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/connectivity/ntp_daemon"

inherit update-rc.d systemd

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}
do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/NtpDaemon ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/ntpdaemon-init ${D}${sysconfdir}/init.d/ntpdaemon-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "ntpdaemon-init"
INITSCRIPT_PARAMS_${PN} = "start 60 3 4 5 . stop 60 0 6 ."

FILES_${PN} += " \
    ${bindir}/NtpDaemon \
    ${sysconfdir}/init.d/ntpdaemon-init \
"

TARGET_CC_ARCH += "${LDFLAGS}"

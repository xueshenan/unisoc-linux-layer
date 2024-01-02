DESCRIPTION = "reg daemon"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "close"
SECTION = "bins"
PROVIDES = "regdaemon"
DEPENDS = "dbus glib-2.0"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"

EXTERNALSRC = "${OEROOT}/source/unisoc/operator_dm/autoReg_daemon"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/operator_dm/autoReg_daemon"

inherit update-rc.d systemd

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}
do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/autoRegDaemon ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/regdaemon-init ${D}${sysconfdir}/init.d/regdaemon-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "regdaemon-init"
INITSCRIPT_PARAMS_${PN} = "defaults 60"

FILES_${PN} += " \
    ${bindir}/autoRegDaemon \
    ${sysconfdir}/init.d/regdaemon-init \
"

TARGET_CC_ARCH += "${LDFLAGS}"

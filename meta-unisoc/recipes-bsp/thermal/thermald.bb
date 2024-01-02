DESCRIPTION = "Unisoc thermal manager"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "bin"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"
PROVIDES = "thermald"
DEPENDS = "chm-at rgbled"
RDEPENDS_${PN} = "chm-at rgbled"

inherit systemd update-rc.d
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/thermald ${D}${bindir}/
    install -d ${D}/etc/
    install -m 0444 ${THISDIR}/files/${MACHINE}/thermalSensorsConfig.xml ${D}/etc

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/thermald.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/thermald-init.sh ${D}${sysconfdir}/init.d/thermald-init.sh

}

FILES_${PN} += "${bindir}/thermald"
TARGET_CC_ARCH += "${LDFLAGS}"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "thermald.service"

INITSCRIPT_NAME = "thermald-init.sh"
INITSCRIPT_PARAMS = "start 90 3 4 5 . stop 90 0 6 ."

DESCRIPTION = "Unisoc power off charge"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
PV = "0.1"
PR = "r0"
PROVIDES = "power off charge"
DEPENDS = "lvgl lvdrv libdrm iniparser rgbled"
RDEPENDS_${PN} = "lvgl lvdrv libdrm iniparser rgbled"

SRC_URI = "\
    file://autorest.ini \
"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit systemd

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/charge ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0777 ${THISDIR}/files/${MACHINE}/charge-init.sh ${D}/${sysconfdir}/init.d/charge-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0777 ${THISDIR}/files/${MACHINE}/charge.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/charge/
    install -m 0755 ${THISDIR}/files/autorest.ini ${D}${sysconfdir}/charge/autorest.ini
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} = " \
    ${bindir}/charge \
    ${sysconfdir}/init.d/charge-init \
    ${systemd_unitdir}/system/charge.service \
    ${sysconfdir}/charge/autorest.ini \
"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "charge.service"


NATIVE_SYSTEMD_SUPPORT = "1"

INITSCRIPT_NAME = "charge"
INITSCRIPT_PARAMS = "start 9 5 1 . stop 20 0 2 6 ."


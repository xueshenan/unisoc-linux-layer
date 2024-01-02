DESCRIPTION = "unisoc gnss-ota"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=14a9876a6ace8be776a952addc32eb4e"
PROVIDES = "gnss-ota"
PV = "0.1"
PR = "r1"

DEPENDS = "dbushelper"
RDEPENDS_${PN} = "gpsd gnss-adapter dbushelper"

inherit update-rc.d systemd

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "gnss-ota"
INITSCRIPT_PARAMS_${PN} = "defaults"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "gnss-ota.service"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/gnss-ota"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/modules/gnss-ota"

do_compile(){
    make clean
    make WORKDIR=${WORKDIR}
}

do_install(){
    install -d ${D}${bindir}/
    install -m 0755 ${S}/gnss-ota ${D}${bindir}/

    install -d ${D}/${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/init_gnss-ota ${D}/${sysconfdir}/init.d/gnss-ota

    install -d ${D}/${sysconfdir}/default/
    install -m 0755 ${THISDIR}/files/default_gnss-ota ${D}/${sysconfdir}/default/gnss-ota

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${THISDIR}/files/gnss-ota.service ${D}${systemd_unitdir}/system/
}

FILES_${PN} += " \
    ${bindir}/gnss-ota \
    ${sysconfdir}/init.d/gnss-ota \
    ${systemd_unitdir}/system/gnss-ota.service \
"

TARGET_CC_ARCH += "${LDFLAGS}"

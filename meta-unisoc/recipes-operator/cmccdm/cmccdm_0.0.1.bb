DESCRIPTION = "CMCC DM"
LICENSE = "CLOSED"
PV = "0.1"
PR = "r0"

PROVIDES = "cmccdm"

DEPENDS = "opdmutility"
RDEPENDS_${PN} = "opdmutility"

inherit systemd update-rc.d

EXTERNALSRC = "${OEROOT}/source/unisoc/operator_dm/operatordm_src/cmccdmapp"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/operator_dm/operatordm_src/cmccdmapp"

SRC_URI = "\
    file://cmccdm.service \
    file://cmccdm-init.sh \
    file://cmccdm.conf \
"

INITSCRIPT_NAME = "cmccdm-init.sh"
INITSCRIPT_PARAMS = "defaults 50"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "cmccdm.service"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/cmccdm ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/cmccdm.conf ${D}/etc/dbus-1/system.d/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/cmccdm-init.sh ${D}${sysconfdir}/init.d/

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/cmccdm.service ${D}${systemd_unitdir}/system/
}

FILES_${PN} += "${bindir}/cmccdm"
TARGET_CC_ARCH += "${LDFLAGS}"

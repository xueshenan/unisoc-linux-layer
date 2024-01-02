DESCRIPTION = "telephony serviceclient based"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bins"
DEPENDS = "dbus glib-2.0"
DEPENDS += "${@bb.utils.contains('SUPPORT_ORCA_LED','true','liborcaled','rgbled',d)}"

RDEPENDS_${PN} = "dbus glib-2.0"
RDEPENDS_${PN} += "${@bb.utils.contains('SUPPORT_ORCA_LED','true','liborcaled','rgbled',d)}"
PROVIDES = "teleservice"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"

inherit pkgconfig cmake
inherit systemd update-rc.d
inherit get_release_mode copy-symbols


TELESERVICE_SRC_DIR = "${OEROOT}/source/unisoc/connectivity/teleservice"
TELESERVICE_INSTALL_DIR = "${DEPLOY_OUT_DIR}"
TELESERVICE_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/teleservice"

TELESERVICE_SRC_MODE="${@get_release_mode(d, "${TELESERVICE_SRC_DIR}")}"
EXTERNALSRC_DIR = "${@bb.utils.contains("TELESERVICE_SRC_MODE", "customer", "${TELESERVICE_RELEASE_DIR}", "${TELESERVICE_SRC_DIR}", d)}"

export N6P_5G_SET_CFLAGS = "${N6P_5G_SET}"
export NETWORK_LED_SET_CFLAGS = "${NETWORK_LED_SET}"
export MULTI_SIM_CFLAGS = "${MULTI_SIM_SET}"

MODEMCONTROL_SECSET ?= "${@bb.utils.contains('SUPPORT_ORCA_LED','true','-DSYS_SUPPORT_LEDDAEMON_','',d)}"
export SUPPORT_LED_FLAG="${MODEMCONTROL_SECSET}"

EXTERNALSRC = "${EXTERNALSRC_DIR}"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}"


SRC_URI = "\
  file://teleservice.service \
  file://teleservice-init \
"

INITSCRIPT_NAME = "teleservice-init"
INITSCRIPT_PARAMS = "start 20 5 3 4 . stop 18 0 1 6 ."

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "teleservice.service"


do_configure () {
    if [ ${TELESERVICE_SRC_MODE} != "customer" ]; then
        cmake_do_configure
    fi
}


do_compile () {
    if [ ${TELESERVICE_SRC_MODE} != "customer" ]; then
        cmake_do_compile
    fi
}

do_install_prepend () {
    if [ ${TELESERVICE_SRC_MODE} != "customer" ]; then
        rm -rf ${TELESERVICE_INSTALL_DIR}/teleservice
        install -d ${TELESERVICE_INSTALL_DIR}/teleservice/
        install -m 0755 ${B}/tele-service ${TELESERVICE_INSTALL_DIR}/teleservice/
    fi
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${B}/tele-service ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/teleservice-init ${D}${sysconfdir}/init.d/teleservice-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/teleservice.service ${D}${systemd_unitdir}/system
}

#FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${includedir}/*.h"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
TARGET_CC_ARCH += "${N6P_5G_SET_CFLAGS} ${NETWORK_LED_SET_CFLAGS} ${MULTI_SIM_CFLAGS} ${SUPPORT_LED_FLAG}"

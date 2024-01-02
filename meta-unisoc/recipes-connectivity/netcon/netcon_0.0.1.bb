DESCRIPTION = "Unisoc netcon module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
PROVIDES = "netcond"
PV = "0.2"
PR = "r0"

DEPENDS = "dbus glib-2.0 libudbus gnutls"
RDEPENDS_${PN} = "dbus glib-2.0 libudbus python3-core gnutls"

inherit get_release_mode
NETCON_SRC_DIR = "${OEROOT}/source/unisoc/perflink/netcon/"
NETCON_INSTALL_DIR = "${DEPLOY_OUT_DIR}/netcon"
NETCON_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/netcon"
export NETCON_SRC_MODE="${@get_release_mode(d,"${NETCON_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("NETCON_SRC_MODE", "customer", "${NETCON_RELEASE_DIR}", "${NETCON_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

SRC_URI = " \
    file://netcon_dbus.conf \
"

ATROUTER_PPPSET ?= "${@bb.utils.contains('ATROUTER_PPP_CLOSED','yes','-DATROUTER_CLOSED_PPP_FUNCTION','-DPPP_USE_DBUS',d)}"
export ATROUTER_CONFIG_PPP_FLAG="${ATROUTER_PPPSET}"

DUALSIM_SECSET ?= "${@bb.utils.contains('DUALSIM_FLAG','true','-DDUALSIM_FLAG','',d)}"
export DUAL_SIM_FLAG="${DUALSIM_SECSET}"

NETCON_DEBUG ?="${@bb.utils.contains('USERDEBUG', 'userdebug', '-DNETCON_DEBUG', '', d)}"
export NETCON_DEBUG_FLAG="${NETCON_DEBUG}"

inherit systemd update-rc.d

do_compile () {
    if [ ${NETCON_SRC_MODE} != "customer" ]; then
        make clean
        echo "WORKDIR: " +${WORKDIR}
        make WORKDIR=${WORKDIR}
    fi
}

do_install_prepend() {
    if [ ${NETCON_SRC_MODE} != "customer" ]; then
        rm -rf ${NETCON_INSTALL_DIR}/out/bin
        rm -rf ${NETCON_INSTALL_DIR}/core/test
        install -d ${NETCON_INSTALL_DIR}/out/bin
        install -d ${NETCON_INSTALL_DIR}/core/test
        install -m 0777 ${S}/out/bin/netcond ${NETCON_INSTALL_DIR}/out/bin
        install -m 0777 ${S}/core/test/netcon_autotest ${NETCON_INSTALL_DIR}/core/test
    fi
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/out/bin/netcond ${D}${bindir}/
    install -m 0755 ${S}/core/test/netcon_autotest ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/netcon_dbus.conf ${D}/etc/dbus-1/system.d/


    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${THISDIR}/files/defaluts/script/netcon-init ${D}${sysconfdir}/init.d/netcon-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${THISDIR}/files/defaluts/script/netcon.service ${D}${systemd_unitdir}/system
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN}-netcon = " \
    ${systemd_unitdir}/system/netcon.service \
"
SYSTEMD_PACKAGES = "${PN}"

INITSCRIPT_NAME = "netcon-init "
INITSCRIPT_PARAMS = "start 90 3 4 5 . stop 90 0 6 ."

SYSTEMD_SERVICE_${PN} = "netcon.service"
NATIVE_SYSTEMD_SUPPORT = "1"

deltask do_rm_work

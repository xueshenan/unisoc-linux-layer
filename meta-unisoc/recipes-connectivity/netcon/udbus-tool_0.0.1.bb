DESCRIPTION = "Unisoc netcon module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
PROVIDES = "udbus-tool"
PV = "0.2"
PR = "r0"

DEPENDS = "dbus glib-2.0 readline libudbus"
RDEPENDS_${PN} = "dbus glib-2.0 readline libudbus"

inherit get_release_mode
UDBUSTOOL_SRC_DIR = "${OEROOT}/source/unisoc/perflink/netcon/udbus-tool"
UDBUSTOOL_INSTALL_DIR = "${DEPLOY_OUT_DIR}/${PN}"
UDBUSTOOL_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/${PN}"
export UDBUSTOOL_SRC_MODE="${@get_release_mode(d,"${UDBUSTOOL_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("UDBUSTOOL_SRC_MODE", "customer", "${UDBUSTOOL_RELEASE_DIR}", "${UDBUSTOOL_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

SRC_URI = " \
    file://netcon_dbus.conf \
"

ATROUTER_PPPSET ?= "${@bb.utils.contains('ATROUTER_PPP_CLOSED','yes','-DATROUTER_CLOSED_PPP_FUNCTION','-DPPP_USE_DBUS',d)}"
export ATROUTER_CONFIG_PPP_FLAG="${ATROUTER_PPPSET}"

DUALSIM_SECSET ?= "${@bb.utils.contains('DUALSIM_FLAG','true','-DDUALSIM_FLAG','',d)}"
export DUAL_SIM_FLAG="${DUALSIM_SECSET}"

inherit systemd

do_compile () {
    if [ ${UDBUSTOOL_SRC_MODE} != "customer" ]; then
        make clean
        echo "WORKDIR: " +${WORKDIR}
        make WORKDIR=${WORKDIR}
    fi
}

do_install_prepend() {
    if [ ${UDBUSTOOL_SRC_MODE} != "customer" ]; then
        rm -rf ${UDBUSTOOL_INSTALL_DIR}/../out/bin/udbus-tool
        install -d ${UDBUSTOOL_INSTALL_DIR}/../out/bin
        install -m 0755 ${S}/../out/bin/udbus-tool ${UDBUSTOOL_INSTALL_DIR}/../out/bin
    fi
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/../out/bin/udbus-tool ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/netcon_dbus.conf ${D}/etc/dbus-1/system.d/
}

TARGET_CC_ARCH += "${LDFLAGS}"

SYSTEMD_PACKAGES = "${PN}"

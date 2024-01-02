DESCRIPTION = "Unisoc netcon module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = " "
PROVIDES = "echoserver"
PV = "0.2"
PR = "r0"

DEPENDS = "dbus glib-2.0 readline libudbus libnetcon"
RDEPENDS_${PN} = "dbus glib-2.0 readline libudbus libnetcon"

EXTERNALSRC = "${OEROOT}/source/unisoc/perflink/netcon/echoserver"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/perflink/netcon/echoserver"

SRC_URI = " \
    ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/netcon/echoserver;protocol=ssh;branch=unc_linux_trunk \
    file://netcon_dbus.conf \
"

ATROUTER_PPPSET ?= "${@bb.utils.contains('ATROUTER_PPP_CLOSED','yes','-DATROUTER_CLOSED_PPP_FUNCTION','-DPPP_USE_DBUS',d)}"
export ATROUTER_CONFIG_PPP_FLAG="${ATROUTER_PPPSET}"

DUALSIM_SECSET ?= "${@bb.utils.contains('DUALSIM_FLAG','true','-DDUALSIM_FLAG','',d)}"
export DUAL_SIM_FLAG="${DUALSIM_SECSET}"

inherit systemd

do_compile () {
    make clean
	echo "WORKDIR: " +${WORKDIR}
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/../out/bin/echoserver ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/netcon_dbus.conf ${D}/etc/dbus-1/system.d/
}

TARGET_CC_ARCH += "${LDFLAGS}"

SYSTEMD_PACKAGES = "${PN}"

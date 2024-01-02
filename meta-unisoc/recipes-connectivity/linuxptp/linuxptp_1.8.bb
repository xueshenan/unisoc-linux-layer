DESCRIPTION = "Precision Time Protocol (PTP) according to IEEE standard 1588 for Linux"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bins"
LIC_FILES_CHKSUM = ""
PROVIDES = "linuxptp"
PV = "1.8"
PR = "r0"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
do_package_qa[noexec] = "1"
PACKAGES = "${PN}"

NATIVE_SYSTEMD_SUPPORT = "1"
EXTRA_OEMAKE = "ARCH=${TARGET_ARCH} \
    EXTRA_CFLAGS='-D_GNU_SOURCE -DHAVE_CLOCK_ADJTIME -DHAVE_POSIX_SPAWN -DHAVE_ONESTEP_SYNC ${CFLAGS}'"

inherit get_release_mode
LINUXPTP_SRC_DIR = "${OEROOT}/source/unisoc/perflink/linuxptp"
LINUXPTP_INSTALL_DIR = "${DEPLOY_OUT_DIR}/${PN}"
LINUXPTP_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/${PN}"
export LINUXPTP_SRC_MODE="${@get_release_mode(d,"${LINUXPTP_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("LINUXPTP_SRC_MODE", "customer", "${LINUXPTP_RELEASE_DIR}", "${LINUXPTP_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    if [ ${LINUXPTP_SRC_MODE} != "customer" ]; then
        if [ -e Makefile -o -e makefile -o -e GNUmakefile ]; then
            oe_runmake || die "make failed"
        else
            bbnote "nothing to compile"
        fi
    fi
}

do_install_prepend() {
    if [ ${LINUXPTP_SRC_MODE} != "customer" ]; then
        rm -rf ${LINUXPTP_INSTALL_DIR}/
        install -d ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/ptp4l ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/pmc ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/phc2sys ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/hwstamp_ctl ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/nsm ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/phc_ctl ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/timemaster ${LINUXPTP_INSTALL_DIR}/
        install -m 0755 ${S}/ts2phc ${LINUXPTP_INSTALL_DIR}/
    fi
}

do_install () {
    install -d          ${D}/${sbindir}/
    install -m 0755     ${S}/ptp4l          ${D}/${sbindir}
    install -m 0755     ${S}/pmc            ${D}/${sbindir}
    install -m 0755     ${S}/phc2sys        ${D}/${sbindir}
    install -m 0755     ${S}/hwstamp_ctl    ${D}/${sbindir}
    install -m 0755     ${S}/nsm            ${D}/${sbindir}
    install -m 0755     ${S}/phc_ctl        ${D}/${sbindir}
    install -m 0755     ${S}/timemaster    	${D}/${sbindir}
    install -m 0755     ${S}/ts2phc         ${D}/${sbindir}
}
TARGET_CC_ARCH += "${LDFLAGS}"



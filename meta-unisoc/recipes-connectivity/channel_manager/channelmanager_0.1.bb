DESCRIPTION = "Unisoc channel_manager module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require channelmanager.inc

SECTION = "bins"
LIC_FILES_CHKSUM = ""
DEPENDS = "iniparser"
RDEPENDS_${PN} = "iniparser"
PROVIDES = "channelmanager"
PV = "0.1"
PR = "r0"
inherit systemd update-rc.d

EXTERNALSRC = "${EXTERNALSRC_DIR}"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}"

SRC_URI = "\
  file://channelmanager.service \
  file://channelmanager-init \
"

INITSCRIPT_NAME = "channelmanager-init"
INITSCRIPT_PARAMS = "start 18 3 4 5 . stop 20 0 1 6 ."


NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "channelmanager.service"

do_install_prepend () {
    if [ ${CHM_SRC_MODE} != "customer" ]; then
        rm -rf ${CHM_INSTALL_DIR}
        install -d ${CHM_INSTALL_DIR}
        install -m 0777 ${S}/channelmanager ${CHM_INSTALL_DIR}
    fi
}

do_install () {
    install -d ${D}/dev/socket/

    install -d ${D}${bindir}/
    install -m 0755 ${S}/channelmanager ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/channelmanager-init ${D}${sysconfdir}/init.d/channelmanager-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/channelmanager.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/channelmanager/
    install -m 0777 ${THISDIR}/files/${MACHINE}/system.ini ${D}${sysconfdir}/channelmanager/system.ini
    install -m 0777 ${THISDIR}/files/${MACHINE}/ptyconf.ini ${D}${sysconfdir}/channelmanager/ptyconf.ini
}

FILES_${PN} += "${systemd_unitdir}"
FILES_${PN} += "/dev/socket/"
TARGET_CC_ARCH += "${LDFLAGS}"

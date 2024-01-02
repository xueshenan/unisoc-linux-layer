DESCRIPTION = "Unisoc rild module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require ril.inc

SECTION = "bins"
DEPENDS = "rilutils sqlite3 rgbled utils rilmbim"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} = "rilutils sqlite3 rgbled utils rilmbim"
PROVIDES = "mbim-daemon"
PV = "0.1"
PR = "r0"
inherit systemd update-rc.d

EXTERNALSRC = "${EXTERNALSRC_DIR}/mbim-device/mbim_device_daemon"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/mbim-device/mbim_device_daemon"

SRC_URI = "\
  file://sprdrild.service \
  file://sprdzmbim-init \
"

INITSCRIPT_NAME = "sprdzmbim-init"
INITSCRIPT_PARAMS = "start 18 5 2 3 . stop 20 0 1 6 ."


NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "sprdrild.service"

do_install_prepend () {
    if [ ${RIL_SRC_MODE} != "customer" ]; then
        rm -rf ${RIL_INSTALL_DIR}/mbim-device/mbim_device_daemon
        install -d ${RIL_INSTALL_DIR}/mbim-device/mbim_device_daemon
        install -m 0777 ${S}/mbim-daemon ${RIL_INSTALL_DIR}/mbim-device/mbim_device_daemon
    fi
}

do_install () {
    install -d ${D}/dev/socket/

    install -d ${D}${bindir}/
    install -m 0755 ${S}/mbim-daemon ${D}${bindir}/
    install -m 0711 ${THISDIR}/files/changemode.sh ${D}${bindir}/changemode.sh

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/sprdzmbim-init ${D}${sysconfdir}/init.d/sprdzmbim-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sprdrild.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${systemd_unitdir}"
FILES_${PN} += "/dev/socket/"
TARGET_CC_ARCH += "${LDFLAGS}"
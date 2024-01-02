DESCRIPTION = "Unisoc test chmpty demo"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "CLOSED"
require chmpty.inc

LIC_FILES_CHKSUM = ""
DEPENDS = "chm-pty"
RDEPENDS_${PN} = "chm-pty"
PROVIDES = "unitest_chmpty"
inherit systemd

EXTERNALSRC = "${EXTERNALSRC_DIR}/unitest"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/unitest"

SRC_URI = "file://unitest-chmpty.service"
NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "unitest-chmpty.service"

do_install_prepend () {
    if [ ${CHM_SRC_MODE} != "customer" ]; then
        rm -rf ${CHM_INSTALL_DIR}/unitest
        install -d ${CHM_INSTALL_DIR}/unitest
        install -m 0777 ${S}/unitest_chmpty ${CHM_INSTALL_DIR}/unitest
    fi
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/unitest_chmpty ${D}${bindir}/

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/unitest-chmpty.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "ppp dial module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require pppdial.inc

SECTION = "bins"
DEPENDS = "dbus glib-2.0"

LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} = "dbus glib-2.0"
PROVIDES = "pppdial"
PV = "0.2"
PR = "r0"
inherit systemd update-rc.d

EXTERNALSRC = "${EXTERNALSRC_DIR}/pppdial2.0"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/pppdial2.0"

SRC_URI = "\
  file://pppdial.service \
  file://pppdial-init.sh \
  file://pppdial.conf \
"

INITSCRIPT_NAME = "pppdial-init.sh"
INITSCRIPT_PARAMS = "defaults 50"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "pppdial.service"

do_install_prepend () {
    if [ ${PPPDIAL_SRC_MODE} != "customer" ]; then
        rm -rf ${PPPDIAL_INSTALL_DIR}/pppdial2.0

        install -d ${PPPDIAL_INSTALL_DIR}/pppdial2.0
        install -m 0777 ${S}/pppdial ${PPPDIAL_INSTALL_DIR}/pppdial2.0
    fi
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/pppdial ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/pppdial.conf ${D}/etc/dbus-1/system.d/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/pppdial-init.sh ${D}${sysconfdir}/init.d/

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/pppdial.service ${D}${systemd_unitdir}/system/
}

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"
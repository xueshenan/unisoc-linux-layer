SUMMARY = "ram init feature package / UNSIOC platform"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"

DEPENDS += ""

SRC_URI = " \
           file://ram-init \
           file://ram.service \
"
inherit systemd update-rc.d

S = "${WORKDIR}"

do_compile () {
}
RDEPENDS_${PN} = "formfactor"
do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/ram-init ${D}${bindir}/
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/ram.service ${D}${systemd_unitdir}/system/ram.service
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/ram-init ${D}${sysconfdir}/init.d/ram-init
}

FILES_${PN} += "${bindir}/ram-init"
FILES_${PN} += "${sysconfdir}/init.d/ram-init"
FILES_${PN} += "${systemd_unitdir}/system/${PN}.service"

INITSCRIPT_NAME = "ram-init"
INITSCRIPT_PARAMS = "start 30 3 4 5 ."

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${PN}.service"

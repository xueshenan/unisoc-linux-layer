PACKAGE_BEFORE_PN = "${PN}-adbd"

RDEPENDS_${BPN}_remove = "${BPN}-conf"
RDEPENDS_${BPN}-adbd = "${BPN}-conf"

FILES_${PN}-adbd = "\
    ${bindir}/adbd \
    ${systemd_unitdir}/system/android-tools-adbd.service \
"

SYSTEMD_PACKAGES = "${PN}-adbd"
SYSTEMD_SERVICE_${PN} = ""
SYSTEMD_SERVICE_${PN}-adbd = "android-tools-adbd.service"

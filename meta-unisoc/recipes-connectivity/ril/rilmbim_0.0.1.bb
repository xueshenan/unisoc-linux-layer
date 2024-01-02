DESCRIPTION = "Unisoc rild module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require ril.inc

SECTION = "libs"
DEPENDS = "rilutils sqlite3 rgbled utils iniparser"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} = "rilutils sqlite3 rgbled utils iniparser"
#LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"
PROVIDES = "rilmbim"

#inherit autotools

EXTERNALSRC = "${EXTERNALSRC_DIR}/mbim-device/mbim_device_vendor"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/mbim-device/mbim_device_vendor"


do_install_prepend () {
    if [ ${RIL_SRC_MODE} != "customer" ]; then
        rm -rf ${RIL_INSTALL_DIR}/mbim-device/mbim_device_vendor
        install -d ${RIL_INSTALL_DIR}/mbim-device/mbim_device_vendor
        install -m 0777 ${S}/librilmbim.so ${RIL_INSTALL_DIR}/mbim-device/mbim_device_vendor
    fi
}


do_install () {
    install -d ${D}${libdir}/
    install -m 0755 ${S}/librilmbim.so ${D}${libdir}/

    install -d ${D}${sysconfdir}/ril/
    install -m 0777 ${THISDIR}/files/mbimmode.ini ${D}${sysconfdir}/ril/mbimmode.ini
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
FILES_${PN}-dbg += "${libdir}/.debug"
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

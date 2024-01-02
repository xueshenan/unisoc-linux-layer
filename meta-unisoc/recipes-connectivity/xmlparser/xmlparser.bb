DESCRIPTION = "Unisoc xml parser module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require xmlparser.inc
SECTION = "lib"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"
PROVIDES = "xmlparser"
DEPENDS = "libxml2"
RDEPENDS_${PN} = "libxml2"

EXTERNALSRC = "${EXTERNALSRC_DIR}/xmlParser"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/xmlParser"

do_install_prepend () {
    if [ ${APN_SRC_MODE} != "customer" ]; then
        rm -rf ${APN_INSTALL_DIR}/xmlParser
        install -d ${APN_INSTALL_DIR}/xmlParser
        install -m 0777 ${S}/*.so ${APN_INSTALL_DIR}/xmlParser

        install -d ${APN_INSTALL_DIR}/xmlParser/inc
        install -m 0644 ${S}/inc/xmlParser.h ${APN_INSTALL_DIR}/xmlParser/inc
        install -m 0644 ${S}/inc/xmlData.h ${APN_INSTALL_DIR}/xmlParser/inc

        install -d ${APN_INSTALL_DIR}/xmlParser/res
        install -m 0644 ${S}/res/*.xml ${APN_INSTALL_DIR}/xmlParser/res
        install -d ${APN_INSTALL_DIR}/xmlParser/res/CarrierConfig/assets
        install -m 0644 ${S}/res/CarrierConfig/assets/*.xml ${APN_INSTALL_DIR}/xmlParser/res/CarrierConfig/assets
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/*.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0644 ${S}/inc/xmlParser.h ${D}${includedir}/
    install -m 0644 ${S}/inc/xmlData.h ${D}${includedir}/
    install -d ${D}${sysconfdir}/
    install -m 0644 ${S}/res/*.xml ${D}${sysconfdir}/
    install -d ${D}${sysconfdir}/CarrierConfig/assets/
    install -m 0644 ${S}/res/CarrierConfig/assets/*.xml ${D}${sysconfdir}/CarrierConfig/assets/
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

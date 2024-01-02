DESCRIPTION = "Unisoc nvsave for ota"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
PROVIDES = "nvsave"
DEPENDS = "modem-utils iniparser"
RDEPENDS_${PN} = "modem-utils iniparser"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"

EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/update/ota/nvsave"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/nvsave ${D}${bindir}/
}
TARGET_CC_ARCH += "${LDFLAGS}"
DESCRIPTION = "Unisoc modem-propctl module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=36dba8813a86f10cc3ecfbcf89dc6eab"
PROVIDES = "modem-propctl"
DEPENDS = "iniparser"
RDEPENDS_${PN} = "iniparser"
PV = "0.1"
PR = "r0"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/modem/modem-utils/modem_propctl"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
  make clean
  make
}

do_install () {
  install -d ${D}${bindir}/
  install -m 0755 ${S}/modem_propctl ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"


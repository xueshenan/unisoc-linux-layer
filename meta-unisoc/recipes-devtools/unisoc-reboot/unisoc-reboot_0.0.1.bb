DESCRIPTION = "Unisoc unisoc_reboot module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f7b40df666d41e6508d03e1c207d498f"
PROVIDES = "unisoc-reboot"
PV = "0.1"
PR = "r0"

EXTERNALSRC = "${OEROOT}/source/devtools/unisoc-reboot"
EXTERNALSRC_BUILD = "${OEROOT}/source/devtools/unisoc-reboot"

do_compile () {
  make clean
  make
}

do_install () {
  install -d ${D}${bindir}/
  install -m 0755 ${S}/unisoc-reboot ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

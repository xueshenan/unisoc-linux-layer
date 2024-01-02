DESCRIPTION = "Unisoc libmodeminictl module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "libmodeminictl"
DEPENDS = "iniparser"
RDEPENDS_${PN} = "iniparser"

PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/libmodeminictl;protocol=ssh;branch=unc_linux_trunk"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/modem/modem-utils/modem_propctl/lib/libmodeminictl"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
  make clean
  make
}

do_install () {
  install -d ${D}${libdir}/
  install -m 0755 ${S}/libmodeminictl.so ${D}${libdir}/

  install -d ${D}${includedir}/
  install -m 0644 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libmodeminictl.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

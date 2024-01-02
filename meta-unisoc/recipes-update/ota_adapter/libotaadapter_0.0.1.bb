DESCRIPTION = "Unisoc libotaadapter module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "libs"
PROVIDES = "libotaadapter"
DEPENDS = "libatcommon iniparser"
RDEPENDS_${PN} = "libatcommon iniparser"

PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=36dba8813a86f10cc3ecfbcf89dc6eab"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/atrouter/lib/otaadapterlib;protocol=ssh;branch=unc_linux_trunk"

EXTERNALSRC = "${OEROOT}/source/unisoc/atrouter/lib/otaadapterlib"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/atrouter/lib/otaadapterlib"

do_compile () {
  make clean
  make
}

do_install () {
  install -d ${D}${libdir}/atlib
  install -m 0777 ${S}/libotaadapter.so ${D}${libdir}/atlib
}

FILES_${PN} += "${libdir}/atlib/libotaadapter.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc libotaadaptersocket module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "libs"
PROVIDES = "libotaadaptersocket"
DEPENDS = ""
RDEPENDS_${PN} = ""

PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=36dba8813a86f10cc3ecfbcf89dc6eab"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/ota_adapter/lib/libotaadaptersocket;protocol=ssh;branch=unc_linux_trunk"

EXTERNALSRC = "${OEROOT}/source/unisoc/ota_adapter/lib/OtaAdapterSocketLib"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/ota_adapter/lib/OtaAdapterSocketLib"

do_compile () {
  make clean
  make WORKDIR=${WORKDIR}
}

do_install () {
  install -d ${D}${libdir}/
  install -m 0777 ${S}/libotaadaptersocket.so ${D}${libdir}/libotaadaptersocket.so
}

FILES_${PN} += "${libdir}/libotaadaptersocket.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

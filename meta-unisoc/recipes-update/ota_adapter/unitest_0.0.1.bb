DESCRIPTION = "Unisoc unitest module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "closed"
PROVIDES = "unitest"
PV = "0.1"
PR = "r0"

DEPENDS = "libotaadaptersocket"
RDEPENDS_${PN} = "libotaadaptersocket"

EXTERNALSRC = "${OEROOT}/source/unisoc/ota_adapter/lib/OtaAdapterSocketLib/unitest"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/ota_adapter/lib/OtaAdapterSocketLib/unitest"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/source/unisoc/ota_adapter/lib/OtaAdapterSocketLib/unitest;protocol=ssh;branch=unc_linux_trunk"


do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/unitest ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

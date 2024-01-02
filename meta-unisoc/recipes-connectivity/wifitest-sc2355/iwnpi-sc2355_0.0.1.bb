DESCRIPTION = "Unisoc iwnpi program"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=f3f99872d65078978ecb8cd71c70c0a5"
PV = "0.1"
PR = "r0"
PROVIDES = "iwnpi"
DEPENDS = "libnl"
RDEPENDS_${PN} = "libnl"
SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/wcn;protocol=ssh;branch=unc_linux_trunk_2.0"

inherit pkgconfig

do_compile () {
    make clean -C ${S} OBJ_DIR=${B}
    make iwnpi -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/iwnpi ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

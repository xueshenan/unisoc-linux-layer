DESCRIPTION = "rgbled demo"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
PV = "0.1"
PR = "r0"
PROVIDES = "rgbled-demo"
DEPENDS = "rgbled"
RDEPENDS_${PN} = "rgbled"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/reg-led;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}/demo"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/demo/rgbled-demo ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "cpe rgbled api module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "lib"
DEPENDS = " "
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
PV = "0.1"
PR = "r0"
PROVIDES = "rgbled"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/rgbled/rgbled;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}/src"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/src/librgbled.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/src/inc/rgbled.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/librgbled.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

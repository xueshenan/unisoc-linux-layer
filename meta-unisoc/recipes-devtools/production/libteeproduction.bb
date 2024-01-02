DECRIPTION = "Unisoc libteeproduction module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
DEPENDS = "libtrusty"
RDEPENDS_${PN} = "libtrusty"
LIC_FILES_CHKSUM = "file://COPYING;md5=9f2c5a42d436ca84d1af26f5b1908793"
PROVIDES = "libteeproduction"

export PRODUCTION_ORCA_FLAG="${PRODUCTION_SECSET}"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${libdir}/npidevice/
    install -m 0777 ${S}/libteeproduction.so ${D}${libdir}/
    install -m 0777 ${S}/libteeproduction.so ${D}${libdir}/npidevice/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libteeproduction.so"
FILES_${PN} += "${libdir}/npidevice/libteeproduction.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

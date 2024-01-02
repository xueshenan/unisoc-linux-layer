DESCRIPTION = "Unisoc libteec module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "libs"
DEPENDS = "libtrusty"
RDEPENDS_${PN} = "libtrusty"
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
PROVIDES = "libteec"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libteec.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/libteec.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

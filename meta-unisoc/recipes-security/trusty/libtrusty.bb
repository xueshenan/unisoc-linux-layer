DESCRIPTION = "Unisoc libtrusty module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"
SECTION = "libs"
LIC_FILES_CHKSUM = "file://NOTICE;md5=9645f39e9db895a4aa6e02cb57294595"
PROVIDES = "libtrusty"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0755 ${S}/libtrusty.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

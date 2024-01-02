DECRIPTION = "Unisoc kernelbootcp module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
DEPENDS = "libtrusty"
RDEPENDS_${PN} = "libtrusty"
LIC_FILES_CHKSUM = "file://COPYING;md5=9f2c5a42d436ca84d1af26f5b1908793"
PROVIDES = "libkernelbootcp"

export NOT_VERIFY_MODEM_FLAG="${KBC_SECSET}"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libkernelbootcp.so ${D}${libdir}/
#    install -m 0777 ${S}/libkernelbootcp.so ${D}${libdir}/modem-control/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} =+ "${libdir}/libkernelbootcp.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

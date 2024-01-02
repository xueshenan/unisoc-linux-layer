DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libxml2 libcaminiparser"
RDEPENDS_${PN} = "libcamcommon libxml2 libcaminiparser"
PV = "0.1"
PR = "r0"
PROVIDES = "libcam-otp-parser"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcam_otp_parser.so ${D}${libdir}/
    install -d ${D}${includedir}/${PN}/
    install -m 0777 ${S}/*.h ${D}${includedir}/${PN}/
}


FILES_${PN} += "${libdir}/libcam_otp_parser.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

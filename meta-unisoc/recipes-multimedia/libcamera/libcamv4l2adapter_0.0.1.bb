DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamoem libcamcommon libmemion libxml2 libcamv4l2yuv2rgb"
RDEPENDS_${PN} = "libcamoem libcamcommon libmemion libxml2 libcamv4l2yuv2rgb"
PV = "0.1"
PR = "r0"
PROVIDES = "libcamv4l2adapter"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcamv4l2adapter.so ${D}${libdir}/
    install -d ${D}${includedir}/libcamera/
    install -m 0777 ${S}/*.h ${D}${includedir}/libcamera/
}

FILES_${PN} += "${libdir}/libcamv4l2adapter.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

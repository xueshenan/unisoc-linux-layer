DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon"
RDEPENDS_${PN} = "libcamcommon"
PV = "0.1"
PR = "r0"
PROVIDES = "libcamv4l2yuv2rgb"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"
export ARM_VERSION="${CAMERA_ARM_VERSION}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcamv4l2yuv2rgb.so ${D}${libdir}/
    install -d ${D}${includedir}/libcamera/
    install -m 0777 ${S}/*.h ${D}${includedir}/libcamera/
}

FILES_${PN} += "${libdir}/libcamv4l2yuv2rgb.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

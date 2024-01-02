DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon"
RDEPENDS_${PN} = "libcamcommon"
PV = "0.1"
PR = "r0"
PROVIDES = "libcambr-isp2.7"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"
export ISP_VER="${TARGET_BOARD_CAMERA_ISP_VERSION}"
export DRV_DIR="${CAMERA_DRIVER_DIR}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcambr_isp2.7.so ${D}${libdir}/
    install -d ${D}${includedir}/libcamera/
    install -m 0777 ${S}/*.h ${D}${includedir}/libcamera/
}

FILES_${PN} += "${libdir}/libcambr_isp2.7.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
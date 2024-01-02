DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcaminiparser libcamcommon"
RDEPENDS_${PN} = "libcaminiparser libcamcommon"
PV = "0.1"
PR = "r0"
PROVIDES = "libcampmloader"

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
    install -m 0777 ${S}/libcampmloader.so ${D}${libdir}/
    install -d ${D}${includedir}/libcamera/
    install -m 0777 ${S}/its_drv/*.h ${D}${includedir}/libcamera/
}

FILES_${PN} += "${libdir}/libcampmloader.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
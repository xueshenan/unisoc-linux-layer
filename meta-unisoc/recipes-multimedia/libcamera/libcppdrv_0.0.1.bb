DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libcaminiparser"
RDEPENDS_${PN} = "libcamcommon libcaminiparser"
PV = "0.1"
PR = "r0"
PROVIDES = "libcppdrv"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export TARGET_BOARD_CAMERA_CPP_MODULAR_KERNEL="${CAMERA_TARGET_BOARD_CAMERA_CPP_MODULAR_KERNEL}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcppdrv.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/libcppdrv.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

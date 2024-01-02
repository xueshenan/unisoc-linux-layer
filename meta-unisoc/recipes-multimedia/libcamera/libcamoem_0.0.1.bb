DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libcaminiparser libcppdrv libxml2 libpss-isp2.7 libcamsensor libcamps"
RDEPENDS_${PN} = "libcamcommon libcaminiparser libcppdrv libxml2 libpss-isp2.7 libcamsensor libcamps"
PV = "0.1"
PR = "r0"
PROVIDES = "libcamoem"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"
export TARGET_BOARD_CAMERA_CPP_MODULAR_KERNEL="${CAMERA_TARGET_BOARD_CAMERA_CPP_MODULAR_KERNEL}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcamoem.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/libcamoem.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

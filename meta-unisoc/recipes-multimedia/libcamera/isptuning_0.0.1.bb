DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamoem libcamcommon libmemion libcamsensor libxml2 libpss-isp2.7 libcaminiparser"
RDEPENDS_${PN} = "libcamoem libcamcommon libmemion libcamsensor libxml2 libpss-isp2.7 libcaminiparser"
PV = "0.1"
PR = "r0"
PROVIDES = "isptuning"


EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"
export ISP_VER="${TARGET_BOARD_CAMERA_ISP_VERSION}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/isp_tuning ${D}${bindir}/
}

FILES_${PN} += "${bindir}/isp_tuning"
TARGET_CC_ARCH += "${LDFLAGS}"


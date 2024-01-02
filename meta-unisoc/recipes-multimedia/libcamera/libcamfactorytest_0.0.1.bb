DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libmemion libcamcommon libcaminiparser libcamoem libxml2 libcamsensor"
RDEPENDS_${PN} = "libmemion libcamcommon libcaminiparser libcamoem libxml2 libcamsensor"
PV = "0.1"
PR = "r0"
PROVIDES = "libcamfactorytest"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/*.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

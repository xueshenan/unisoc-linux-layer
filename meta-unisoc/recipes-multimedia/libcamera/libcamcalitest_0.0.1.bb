DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libmemion libcamsensor libcaminiparser libcamoem libxml2"
RDEPENDS_${PN} = "libcamcommon libmemion libcamsensor libcaminiparser libcamoem libxml2"
PV = "0.1"
PR = "r0"
PROVIDES = "libcamcalitest"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/*.so ${D}${libdir}/
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/*.so ${D}${libdir}/npidevice
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${THISDIR}/files/camera.sh ${D}${sysconfdir}/init.d
}

FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${libdir}/npidevice/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

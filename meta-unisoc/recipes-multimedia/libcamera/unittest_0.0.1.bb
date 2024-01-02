DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libcamv4l2adapter libcamv4l2yuv2rgb  libmemion  libcamoem libcamsensor   libxml2"
RDEPENDS_${PN} = "libcamcommon libcamv4l2adapter libcamv4l2yuv2rgb   libmemion  libcamoem libcamsensor   libxml2"
PV = "0.1"
PR = "r0"
PROVIDES = "unittest"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/V4l2AdapterUnitTest/V4l2UnitTest ${D}${bindir}/

    install -m 0777 ${S}/OemUnitTest/OemUnitTest ${D}${bindir}/

}

FILES_${PN} += "${bindir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

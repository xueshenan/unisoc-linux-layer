DESCRIPTION = "Unisoc libcamsensor module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libcamxmlparser libcam-otp-parser libcampmloader libxml2"
RDEPENDS_${PN} = "libcamcommon libcamxmlparser libcam-otp-parser libcampmloader libxml2"
PV = "0.1"
PR = "r0"
PROVIDES = "libcamsensor"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcamsensor.so ${D}${libdir}/
    install -d ${D}/etc/
    install -m 0444 ${THISDIR}/files/${CAMERA_BOARD}/sensor_config.xml ${D}/etc
    install -d ${D}${includedir}/${PN}/
    install -m 0777 ${S}/inc/*.h ${D}${includedir}/${PN}/
}

FILES_${PN} += "${libdir}/libcamsensor.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

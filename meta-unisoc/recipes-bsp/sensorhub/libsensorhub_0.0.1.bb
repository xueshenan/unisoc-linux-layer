DESCRIPTION = "Uniso sensorhub module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = ""
LIC_FILES_CHKSUM = "file://COPYING;md5=4dc42172d478f260ea964546a37164e0"

PV = "0.1"
PR = "r0"
PROVIDES = "libsensorhub"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${SENSOR_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libsensorhub.so ${D}${libdir}/
    install -m 0777 ${B}/ConfigSensor/libsensorsdrvcfg.so ${D}${libdir}/
    install -m 0777 ${B}/ConfigFeature/libsensorlistcfg.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


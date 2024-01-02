DESCRIPTION = "libcaminiparser module"
HOMEPAGE = "https://github.com/ndevilla/iniparser"
LICENSE = "MIT"
SECTION = "libs"
PROVIDES = "libcaminiparser"
PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=b2eac6cdb9efc91e48d647e00abdac48"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit update-rc.d systemd

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcaminiparser.so ${D}${libdir}/
    install -d ${D}${includedir}/libcamera/
    install -m 0777 ${S}/*.h ${D}${includedir}/libcamera/
    install -d ${D}${sysconfdir}/cam_ini/
    install -m 0777 ${THISDIR}/files/${CAMERA_BOARD}/camera_property.ini ${D}${sysconfdir}/cam_ini/
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/ini-init.sh ${D}${sysconfdir}/init.d/ini-init.sh
}

INITSCRIPT_NAME = "ini-init.sh"
INITSCRIPT_PARAMS = "start 95 S ."

FILES_${PN} += "${libdir}/libcaminiparser.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

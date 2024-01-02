DESCRIPTION = "Unisoc camera gstream plugin"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamv4l2adapter gstreamer1.0 gstreamer1.0-plugins-base libcamcommon"
RDEPENDS_${PN} = "libcamv4l2adapter libcamcommon"
PV = "0.1"
PR = "r0"
PROVIDES = "libgstuniscamera"

export SYS_ROOT = "${WORKDIR}/recipe-sysroot"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

#export board="${MACHINE}"

do_compile () {
    make clean
    make -C ${S} OBJ_DIR=${B}
    make camctrl
}

do_install () {
    install -d ${D}${libdir}/gstreamer-1.0/
    install -m 0777 ${B}/libgstuniscamera.so ${D}${libdir}/gstreamer-1.0/
}

FILES_${PN} += "${libdir}/gstreamer-1.0/libgstuniscamera.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

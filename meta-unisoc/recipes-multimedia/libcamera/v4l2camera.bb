DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "bin"
DEPENDS = "libcamv4l2adapter libcamcommon"
RDEPENDS_${PN} = "libcamv4l2adapter libcamcommon"
PV = "0.1"
PR = "r0"
PROVIDES = "v4l2camera"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make v4l2
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/v4l2camera ${D}${bindir}/
}

FILES_${PN} += "${sbindir}/v4l2camera"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

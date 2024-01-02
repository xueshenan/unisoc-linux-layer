DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libcamsensor libcam-otp-parser libcamxmlparser libxml2"
RDEPENDS_${PN} = "libcamcommon libcamsensor libcam-otp-parser libcamxmlparser libxml2"
PV = "0.1"
PR = "r0"
PROVIDES = "libcam-otp-drv"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"
export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/driver/general/*.so ${D}${libdir}/

}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

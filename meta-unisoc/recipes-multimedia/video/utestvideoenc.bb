DESCRIPTION = "Unisoc encoder test"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "bin"
DEPENDS = "libmemion glib-2.0 gtest gstreamer1.0"
RDEPENDS_${PN} = "libmemion glib-2.0 gtest gstreamer1.0"
PV = "0.1"
PR = "r0"
PROVIDES = "utestvideoenc"

export SYS_ROOT = "${WORKDIR}/recipe-sysroot"

export TARGET_VSP_PLATFORM = "${VSP_PLATFORM}"

export TARGET_VPP_PLATFORM = "${VPP_PLATFORM}"

export TARGET_BOARD_PLATFORM = "${BOARD_PLATFORM}"

export TARGET_BIA_SUPPORT = "${BIA_SUPPORT}"

export SUPPORT_AFBC = "${AFBC}"

do_compile () {
    make -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/utest_video_enc ${D}${bindir}/
}

FILES_${PN} += "${bindir}/utest_video_enc"
TARGET_CC_ARCH += "${LDFLAGS}"

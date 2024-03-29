DESCRIPTION = "Unisoc omx components h265 hw encoder"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "lib"
DEPENDS = "libmemion gstreamer1.0-omx"
RDEPENDS_${PN} = "libmemion gstreamer1.0-omx"
PV = "0.1"
PR = "r0"
PROVIDES = "libstagefrightsprdh265enc"

export SYS_ROOT = "${WORKDIR}/recipe-sysroot"

export TARGET_VSP_PLATFORM = "${VSP_PLATFORM}"

export TARGET_VPP_PLATFORM = "${VPP_PLATFORM}"

export TARGET_BOARD_PLATFORM = "${BOARD_PLATFORM}"

export TARGET_BIA_SUPPORT = "${BIA_SUPPORT}"

export SUPPORT_AFBC = "${AFBC}"

export SUPPORT_RGB_ENC = "${RGB_ENC_SUPPORT}"

export TARGET_PLATFORM_ARCH = "${TARGET_ARCH}"

do_compile () {
    make -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libstagefright_sprd_h265enc.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/libstagefright_sprd_h265enc.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc codecs demo hevc encoder"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "bin"
DEPENDS = "libmemion"
RDEPENDS_${PN} = "libmemion"
PV = "0.1"
PR = "r0"
PROVIDES = "utestvsphevch265enc"

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
    install -m 0777 ${B}/utest_vsp_hevch265enc ${D}${bindir}/
}

FILES_${PN} += "${bindir}/utest_vsp_hevch265enc"
TARGET_CC_ARCH += "${LDFLAGS}"

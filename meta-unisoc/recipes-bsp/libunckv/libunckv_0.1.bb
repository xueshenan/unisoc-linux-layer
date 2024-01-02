DESCRIPTION = "libunckv provides C API for key-value store"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM =  "file://COPYING;md5=3ca06ce07025ae20c1099892b2593dd9"
SECTION = "libs"
DEPENDS = "sqlite3"
RDEPENDS_${PN} = "sqlite3"
PROVIDES = "libunckv"
PV = "0.1"
PR = "r0"

inherit pkgconfig cmake
inherit get_release_mode

LIBUNCKV_SRC_DIR = "${OEROOT}/source/unisoc/modules/utils/libunckv"
LIBUNCKV_INSTALL_DIR = "${DEPLOY_OUT_DIR}/libunckv"
LIBUNCKV_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/libunckv"
export LIBUNCKV_SRC_MODE="${@get_release_mode(d,"${LIBUNCKV_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("LIBUNCKV_SRC_MODE", "customer", "${LIBUNCKV_RELEASE_DIR}", "${LIBUNCKV_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_configure () {
    if [ ${LIBUNCKV_SRC_MODE} != "customer" ]; then
        cmake_do_configure
    fi
}

do_compile() {
    if [ ${LIBUNCKV_SRC_MODE} != "customer" ]; then
        cmake_do_compile
    fi
}

do_install_prepend() {
    if [ ${LIBUNCKV_SRC_MODE} != "customer" ]; then
        rm -rf ${LIBUNCKV_INSTALL_DIR}
        install -d ${LIBUNCKV_INSTALL_DIR}
        install -m 0777 ${S}/libunckv.so ${LIBUNCKV_INSTALL_DIR}
        install -m 0777 ${S}/inc/unckv.h ${LIBUNCKV_INSTALL_DIR}/inc
    fi
}

do_install () {
    install -d ${D}${includedir}/
    install -m 0777 ${S}/inc/unckv.h ${D}${includedir}/
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libunckv.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${includedir}/unckv.h"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

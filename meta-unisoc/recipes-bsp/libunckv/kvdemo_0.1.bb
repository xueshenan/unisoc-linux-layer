DESCRIPTION = "brechmark for libunckv"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM =  "file://COPYING;md5=3ca06ce07025ae20c1099892b2593dd9"
SECTION = "bins"
DEPENDS = "libunckv"
RDEPENDS_${PN} = "libunckv"
PROVIDES = "kvdemo"
PV = "0.1"
PR = "r0"

inherit pkgconfig cmake
inherit get_release_mode

KVDEMO_SRC_DIR = "${OEROOT}/source/unisoc/modules/utils/libunckv/demo"
KVDEMO_INSTALL_DIR = "${DEPLOY_OUT_DIR}/kvdemo"
KVDEMO_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/kvdemo"
export KVDEMO_SRC_MODE="${@get_release_mode(d,"${KVDEMO_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("KVDEMO_SRC_MODE", "customer", "${KVDEMO_RELEASE_DIR}", "${KVDEMO_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_configure () {
    if [ ${KVDEMO_SRC_MODE} != "customer" ]; then
        cmake_do_configure
    fi
}

do_compile() {
    if [ ${KVDEMO_SRC_MODE} != "customer" ]; then
        cmake_do_compile
    fi
}

do_install_prepend() {
    if [ ${KVDEMO_SRC_MODE} != "customer" ]; then
        rm -rf ${KVDEMO_INSTALL_DIR}
        install -d ${KVDEMO_INSTALL_DIR}
        install -m 0777 ${B}/kvdemo ${KVDEMO_INSTALL_DIR}
    fi
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/kvdemo ${D}${bindir}/
}

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"
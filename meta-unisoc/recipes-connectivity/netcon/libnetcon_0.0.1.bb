DESCRIPTION = "Unisoc libnetcon module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "libs"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
PROVIDES = "libnetcon"
PV = "0.1"
PR = "r0"

DEPENDS = "dbus glib-2.0 libudbus"
RDEPENDS_${PN} = "dbus glib-2.0 libudbus"

inherit get_release_mode
LIBNETCON_SRC_DIR = "${OEROOT}/source/unisoc/perflink/netcon/libnetcon"
LIBNETCON_INSTALL_DIR = "${DEPLOY_OUT_DIR}/libnetcon"
LIBNETCON_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/libnetcon"
export LIBNETCON_SRC_MODE="${@get_release_mode(d,"${LIBNETCON_SRC_DIR}")}"


EXTERNALSRC = "${@bb.utils.contains("LIBNETCON_SRC_MODE", "customer", "${LIBNETCON_RELEASE_DIR}", "${LIBNETCON_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"



export SUPPORT_BIND_PROCESS_TO_PDP_FLAG= "${BIND_PROCESS_TO_PDP}"

SRC_URI = " \
    file://netcon_dbus.conf \
"

do_compile () {
    if [ ${LIBNETCON_SRC_MODE} != "customer" ]; then
        make clean
        echo "WORKDIR: " +${WORKDIR}
        make WORKDIR=${WORKDIR}
    fi
}

do_install_prepend() {
    if [ ${LIBNETCON_SRC_MODE} != "customer" ]; then
        rm -rf ${LIBNETCON_INSTALL_DIR}/
        install -d ${LIBNETCON_INSTALL_DIR}/out/lib
        install -d ${LIBNETCON_INSTALL_DIR}/
        install -m 0644 ${S}/*.h ${LIBNETCON_INSTALL_DIR}/
        install -m 0777 ${S}/out/lib/libnetcon.so ${LIBNETCON_INSTALL_DIR}/out/lib
    fi
}

do_install () {
    install -d ${D}${includedir}/libnetcon
    install -m 0644 ${S}/*.h ${D}${includedir}/libnetcon
    install -d ${D}${libdir}/
    install -m 0777 ${S}/out/lib/libnetcon.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${includedir}/*.h"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
TARGET_CC_ARCH += "${SUPPORT_BIND_PROCESS_TO_PDP_FLAG}"

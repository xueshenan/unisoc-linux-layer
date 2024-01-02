DESCRIPTION = "Unisoc libudbus module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "libs"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
PROVIDES = "libudbus"
PV = "0.1"
PR = "r0"

DEPENDS = "dbus glib-2.0"
RDEPENDS_${PN} = "dbus glib-2.0"

inherit get_release_mode
LIBUDBUS_SRC_DIR = "${OEROOT}/source/unisoc/perflink/netcon/udbus"
LIBUDBUS_INSTALL_DIR = "${DEPLOY_OUT_DIR}/${PN}"
LIBUDBUS_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/${PN}"
export LIBUDBUS_SRC_MODE="${@get_release_mode(d,"${LIBUDBUS_SRC_DIR}")}"


EXTERNALSRC = "${@bb.utils.contains("LIBUDBUS_SRC_MODE", "customer", "${LIBUDBUS_RELEASE_DIR}", "${LIBUDBUS_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

SRC_URI = " \
    file://netcon_dbus.conf \
"

do_compile () {
    if [ ${LIBUDBUS_SRC_MODE} != "customer" ]; then
        make clean
        make WORKDIR=${WORKDIR}
    fi
}

do_install_prepend() {
    if [ ${LIBUDBUS_SRC_MODE} != "customer" ]; then
        rm -rf ${LIBUDBUS_INSTALL_DIR}/*.so
        rm -rf ${LIBUDBUS_INSTALL_DIR}/*.h
        install -d ${LIBUDBUS_INSTALL_DIR}/include
        install -d ${LIBUDBUS_INSTALL_DIR}/out/lib
        install -m 0644 ${S}/include/*.h ${LIBUDBUS_INSTALL_DIR}/include
        install -m 0777 ${S}/out/lib/*.so ${LIBUDBUS_INSTALL_DIR}/out/lib
    fi
}

do_install () {
    install -d ${D}${includedir}/
    install -m 0644 ${S}/include/*.h ${D}${includedir}/
    install -d ${D}${libdir}/
    install -m 0777 ${S}/out/lib/*.so ${D}${libdir}/
}

FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${includedir}/*.h"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

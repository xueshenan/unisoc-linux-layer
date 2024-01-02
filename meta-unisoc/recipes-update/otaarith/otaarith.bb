inherit cmake

DESCRIPTION = "arith for ota"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "lib"
DEPENDS += "bsdiff"
RDEPENDS_{PN} += "bsdiff"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"
PROVIDES = "otaarith"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"

do_install_append () {
    install -d ${DEPLOY_DIR_IMAGE}/
    install -m 755 ${WORKDIR}/image/usr/lib/libotaarith.so ${DEPLOY_DIR_IMAGE}
}


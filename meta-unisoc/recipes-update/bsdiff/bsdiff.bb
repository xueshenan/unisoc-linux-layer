inherit cmake

DESCRIPTION = "Unisoc bsdiff for ota"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
DEPENDS += ""
RDEPENDS_{PN} += ""
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"
PROVIDES = "bsdiff"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
BBCLASSEXTEND = "native"

do_install_append_class-target () {
    install -d ${DEPLOY_DIR_IMAGE}/
    install -m 755 ${WORKDIR}/image/usr/lib/liblibbspatch.so ${DEPLOY_DIR_IMAGE}
}

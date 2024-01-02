DESCRIPTION = "Unisoc aiactiver library"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "lib"
PV = "0.1"
PR = "r1"
RPROVIDES_${PN} += "aiactiver-lib"
DEPENDS += "wayland"

do_install() {
    install -d ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/libaiactiver_core.so ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/libaiactiver.so ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/Imagination_Nna_backend.so ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/bin/NnaBackendIT ${D}${libdir}/
}

FILES_${PN} += " \
    ${libdir}/libaiactiver_core.so \
    ${libdir}/libaiactiver.so \
    ${libdir}/Imagination_Nna_backend.so \
    ${libdir}/NnaBackendIT \
"

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT="1"
INHIBIT_PACKAGE_STRIP="1"
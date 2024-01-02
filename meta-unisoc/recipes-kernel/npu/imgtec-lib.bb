DESCRIPTION = "Unisoc imgtec library"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "lib"
PV = "0.1"
PR = "r1"
RPROVIDES_${PN} += "imgtec-lib"

do_install() {
    install -d ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/libimgdnn.so ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/libimgcustom.so ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/libnnacompiler.so ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/libnnaruntime.so ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/npu_img_hwconfig.json ${D}${libdir}/
    install -m 0777 ${THISDIR}/files/lib/npu_img_mapconfig.json ${D}${libdir}/
}

FILES_${PN} += " \
    ${libdir}/libimgdnn.so \
    ${libdir}/libimgcustom.so \
    ${libdir}/libnnacompiler.so \
    ${libdir}/libnnaruntime.so \
    ${libdir}/npu_img_hwconfig.json \
    ${libdir}/npu_img_mapconfig.json \
"

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

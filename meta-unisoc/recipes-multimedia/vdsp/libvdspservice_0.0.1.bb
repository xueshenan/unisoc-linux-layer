DESCRIPTION = "Unisoc vdsp library"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "lib"
PV = "0.1"
PR = "r0"
PROVIDES = "libvdspservice"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libvdspservice.so ${D}${libdir}/
    install -d ${D}/lib/firmware/
    install -m 0644 ${S}/../../../firmware/qogirn6pro/vdsp_firmware.bin ${D}/lib/firmware/
}

FILES_${PN} += "${libdir}"
FILES_${PN} += "/lib/firmware/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN} += "arch"
TARGET_CC_ARCH += "${LDFLAGS}"

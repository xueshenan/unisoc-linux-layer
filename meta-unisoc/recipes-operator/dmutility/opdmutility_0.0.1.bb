DESCRIPTION = "Unisoc operator dm utility so"
LICENSE = "CLOSED"
SECTION = "lib"
DEPENDS = ""
PV = "0.1"
PR = "r0"
PROVIDES = "opdmutility"

DEPENDS = "atci"
RDEPENDS_${PN} = "atci"

EXTERNALSRC = "${OEROOT}/source/unisoc/operator_dm/operatordm_src/dmutility"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/operator_dm/operatordm_src/dmutility"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0644 ${S}/libopdmutility.so ${D}${libdir}/

    install -d ${D}${includedir}/
    install -m 0644 ${S}/*.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/libopdmutility.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

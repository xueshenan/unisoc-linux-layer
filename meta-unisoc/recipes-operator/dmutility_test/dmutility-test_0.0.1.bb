DESCRIPTION = "Unisoc operator dm utility test"
LICENSE = "CLOSED"
SECTION = "bin"
DEPENDS = ""
PV = "0.1"
PR = "r0"
PROVIDES = "dmutility_test"

DEPENDS = "atci opdmutility"
RDEPENDS_${PN} = "atci opdmutility"

EXTERNALSRC = "${OEROOT}/source/unisoc/operator_dm/operatordm_src/dmutility_test"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/operator_dm/operatordm_src/dmutility_test"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/dmutility_test.bin ${D}${bindir}/
}

FILES_${PN} += "${bindir}/dmutility_test.bin"
TARGET_CC_ARCH += "${LDFLAGS}"

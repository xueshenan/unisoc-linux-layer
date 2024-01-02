CRIPTION = "Unisoc aiactiver library testbench"
OMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
DEPENDS = "aiactiver-lib"
RDEPENDS_${PN} = "aiactiver-lib"
PV = "0.1"
PR = "r2"
RPROVIDES_${PN} += "aiactiver-testbench"

inherit systemd

do_install() {
    install -d ${D}${bindir}/
    install -m 0777 ${THISDIR}/files/bin/multiple_input_multiple_output ${D}${bindir}/
    install -m 0777 ${THISDIR}/files/bin/aiactiver_testbench ${D}${bindir}/
}

FILES_${PN} += "${bindir}/multiple_input_multiple_output"
FILES_${PN} += "${bindir}/aiactiver_testbench"

TARGET_CC_ARCH += "${LDFLAGS}"


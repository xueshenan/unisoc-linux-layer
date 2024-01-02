CRIPTION = "Unisoc imgtec library cnn_testbench"
OMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
DEPENDS = "imgtec-lib"
RDEPENDS_${PN} = "imgtec-lib"
PV = "0.1"
PR = "r2"
RPROVIDES_${PN} += "imgtec-cnntest"

inherit systemd

do_install() {
    install -d ${D}${bindir}/
    install -m 0777 ${THISDIR}/files/bin/cnn_testbench ${D}${bindir}/
    install -m 0777 ${THISDIR}/files/bin/input0.f32 ${D}${bindir}/
    install -m 0777 ${THISDIR}/files/bin/model.mbs.bin ${D}${bindir}/
}

FILES_${PN} += "${bindir}/cnn_testbench"
FILES_${PN} += "${bindir}/input0.f32"
FILES_${PN} += "${bindir}/model.mbs.bin"

TARGET_CC_ARCH += "${LDFLAGS}"

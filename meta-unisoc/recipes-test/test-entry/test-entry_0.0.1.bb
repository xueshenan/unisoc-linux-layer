CRIPTION = "test_entry"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/COPYING;md5=f7b40df666d41e6508d03e1c207d498f"
SECTION = "bin"
PV = "0.1"
PR = "r0"
PROVIDES = "test_entry"

SRC_URI = "\
"
inherit update-rc.d systemd

do_compile() {
}

do_install () {
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/test-init.sh ${D}${sysconfdir}/init.d/test-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "test-init"
INITSCRIPT_PARAMS_${PN} = "defaults 95"

FILES_${PN} = " \
    ${sysconfdir}/init.d/test-init \
"

TARGET_CC_ARCH += "${LDFLAGS}"


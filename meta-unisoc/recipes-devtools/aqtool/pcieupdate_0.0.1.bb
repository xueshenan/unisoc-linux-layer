DESCRIPTION = "pcie firmware tools"
SECTION = "pcieupdate"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=36dba8813a86f10cc3ecfbcf89dc6eab"
SECTION = "bin"
PV = "0.1"
PR = "r0"
PROVIDES = "pcieupdate"

EXTERNALSRC = "${OEROOT}/source/unisoc/aq_tools"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/aq_tools;protocol=ssh;branch=unc_linux_trunk"

inherit update-rc.d systemd

do_compile() {
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${THISDIR}/files/pcie_firmware_update.sh ${D}${bindir}/
    install -m 0755 ${OEROOT}/source/unisoc/aq_tools/AQC111-Bermuda-B0-3.1.90_bdp.clx ${D}${bindir}/
    install -m 0755 ${OEROOT}/source/unisoc/aq_tools/flashBurn ${D}${bindir}/
    install -m 0755 ${OEROOT}/source/unisoc/aq_tools/readstat ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/pcieupdate-init.sh ${D}${sysconfdir}/init.d/pcieupdate-init

    install -d ${D}${libdir}
    install -m 0755 ${OEROOT}/source/unisoc/aq_tools/libatlpci.so ${D}${libdir}/
    install -m 0755 ${OEROOT}/source/unisoc/aq_tools/hwInterfaceAtltool.so ${D}${libdir}/
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "pcieupdate-init"
INITSCRIPT_PARAMS_${PN} = "defaults 95"

FILES_${PN} = " \
    ${bindir}/pcie_firmware_update.sh \
    ${bindir}/AQC111-Bermuda-B0-3.1.90_bdp.clx \
    ${bindir}/flashBurn \
    ${bindir}/readstat \
    ${libdir}/* \
    ${sysconfdir}/init.d/pcieupdate-init \
"

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"
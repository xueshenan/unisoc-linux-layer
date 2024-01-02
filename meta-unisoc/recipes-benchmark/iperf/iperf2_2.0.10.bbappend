FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
            file://iperf-init \
"

inherit systemd update-rc.d

do_install_append() {
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/iperf-init ${D}/${sysconfdir}/init.d/iperf-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "iperf-init"
INITSCRIPT_PARAMS_${PN} = "defaults 10"

FILES_${PN} += " \
    ${sysconfdir}/init.d/iperf-init \
"

TARGET_CC_ARCH += "${LDFLAGS}"

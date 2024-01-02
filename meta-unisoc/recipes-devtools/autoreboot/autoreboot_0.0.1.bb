DESCRIPTION = "autoreboot tools"
SECTION = "autoreboot"
LICENSE = "CLOSED"
SECTION = "bin"

PV = "0.1"
PR = "r0"
inherit autotools systemd update-rc.d
PACKAGES =+ "${PN}-autoreboot \
"
FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI = " \
        file://reboot_test-init.sh \
        file://reboot-init.sh \
        file://reboot_test.sh \
          "
PROVIDES = "autoreboot"
RDEPENDS_${PN} = "${PN}-autoreboot \
                 "
INITSCRIPT_PACKAGES = "${PN}-autoreboot"
INITSCRIPT_NAME_${PN}-autoreboot = "reboot-init"
INITSCRIPT_PARAMS_${PN}-autoreboot = "start 95 5 ."

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}
do_install () {

    install -d ${D}${sbindir}
    install -m 0744 ${WORKDIR}/reboot_test-init.sh ${D}/${sbindir}
    install -m 0744 ${WORKDIR}/reboot_test.sh ${D}/${sbindir}

    install -d ${D}${sysconfdir}/init.d
    install -m 0744 ${WORKDIR}/reboot-init.sh ${D}${sysconfdir}/init.d/reboot-init

}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN}-autoreboot = " \
    ${sbindir}/* \
"

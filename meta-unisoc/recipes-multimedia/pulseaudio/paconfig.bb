DESCRIPTION = "pulseaudio config and init script"
LICENSE = "CLOSED"

PV = "0.1"
PR = "r0"

inherit update-rc.d

SRC_URI = " \
    file://pa-init \
    file://pulse_env.sh \
"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/pa-init ${D}${sysconfdir}/init.d/pa-init
    install -d ${D}${sysconfdir}/profile.d
    install -m 755 ${WORKDIR}/pulse_env.sh ${D}${sysconfdir}/profile.d/pulse_env.sh
}

pkg_postinst_ontarget_${PN}() {
}

FILES_${PN} += "${sysconfdir}/init.d/pa-init"
FILES_${PN} += "${sysconfdir}/profile.d/pulse_env.sh"

INITSCRIPT_NAME = "pa-init"
INITSCRIPT_PARAMS = "start 90 3 4 5 . stop 90 0 6 ."

FILESEXTRAPATHS_prepend := "${THISDIR}/alsa-state:"

INITSCRIPT_PARAMS = "stop 31 0 6 ."

SRC_URI_append += " \
           file://asound.conf \
           "

do_install_append () {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/asound.conf ${D}${sysconfdir}
}

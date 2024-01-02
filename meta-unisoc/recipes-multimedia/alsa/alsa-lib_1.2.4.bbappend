
FILESEXTRAPATHS_prepend := "${THISDIR}/alsa-lib:"

SRC_URI_append += " \
        file://${MACHINE}/sprdphone.conf \
        file://${MACHINE}/HiFi.conf \
	 file://${MACHINE}/VoiceCall.conf \
	 file://${MACHINE}/sprdphone-sc2730.conf \
        "

do_install_append() {
        install -d ${D}${datadir}/alsa/ucm2/sprdphone
        install -d ${D}${datadir}/alsa/ucm2/sprdphone-sc273
        install -m 0644 ${WORKDIR}/${MACHINE}/sprdphone.conf  ${D}${datadir}/alsa/ucm2/sprdphone/sprdphone.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/HiFi.conf ${D}${datadir}/alsa/ucm2/sprdphone/HiFi.conf
	 install -m 0644 ${WORKDIR}/${MACHINE}/VoiceCall.conf ${D}${datadir}/alsa/ucm2/sprdphone/VoiceCall.conf
	 install -m 0644 ${WORKDIR}/${MACHINE}/sprdphone-sc2730.conf  ${D}${datadir}/alsa/ucm2/sprdphone-sc273/sprdphone-sc2730.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/HiFi.conf ${D}${datadir}/alsa/ucm2/sprdphone-sc273/HiFi.conf
	 install -m 0644 ${WORKDIR}/${MACHINE}/VoiceCall.conf ${D}${datadir}/alsa/ucm2/sprdphone-sc273/VoiceCall.conf
}

FILES_${PN} += "${datadir}/alsa/ucm2/sprdphone/sprdphone.conf"
FILES_${PN} += "${datadir}/alsa/ucm2/sprdphone/HiFi.conf"
FILES_${PN} += "${datadir}/alsa/ucm2/sprdphone/VoiceCall.conf"
FILES_${PN} += "${datadir}/alsa/ucm2/sprdphone-sc273/sprdphone-sc2730.conf"
FILES_${PN} += "${datadir}/alsa/ucm2/sprdphone-sc273/HiFi.conf"
FILES_${PN} += "${datadir}/alsa/ucm2/sprdphone-sc273/VoiceCall.conf"

#deltask do_rm_work

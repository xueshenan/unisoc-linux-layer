FILESEXTRAPATHS_prepend := "${THISDIR}/pulseaudio:"

export SMART_PA_SUPPORT = "${SMART_PA_SUPPORT_FLAG}"

#check audio hal is use whale or normal.
export AUDUO_PLATFORM = "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'whale', 'normal', d)}"

PACKAGECONFIG_append = "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'whalehal', '', d)} \
                        ${@bb.utils.contains('MACHINE_FEATURES', 'radio', 'ofono', '', d)} "
PACKAGECONFIG[whalehal] = "--enable-whalehal,--disable-whalehal,"

#add pulse into input group 
USERADD_PARAM_pulseaudio-server = "--system --home /var/run/pulse \
                              --no-create-home --shell /bin/false \
                              --groups audio,pulse,input --gid pulse pulse"

SRC_URI_append += " \
           file://${MACHINE}/sprd_default.conf \
           file://${MACHINE}/headphone_output.conf \
           file://${MACHINE}/speaker_output.conf \
           file://${MACHINE}/earpiece_output.conf \
           file://${MACHINE}/music-analog-output.conf \
           file://${MACHINE}/sprd-input-headphone-mic.conf \
           file://${MACHINE}/sprd-input-internal-mic.conf \
           file://${MACHINE}/sprd-input-internal-signal-mic.conf \
           file://${MACHINE}/pulseaudio-bluetooth.conf \
           file://${MACHINE}/pulseaudio-ofono.conf \
           file://0001-Bug-1948009-porting-pulseaudio-patch-from-GLP1.0.patch \
           file://audio_policy.conf \
           file://0002-bug-1618888-ucm-setport.patch \
           file://0003-Bug-1618250-support-stream-role-control-device.patch \
           file://0004-Bug-1964611-add-a-macro-for-using-whale-audiohal.patch \
           file://0005-Bug-1618889-support-ucm-call.patch \
           file://0006-Bug-2079136-modify-suspend-interface-of-bluetooth-a2dp.patch \
           file://0007-Bug-2057500-support-unisoc-platform-bluetooth-HFP.patch \
           file://0008-Bug-1963587-support_N6P_ucm.patch \
           file://0009-Bug-2084649-handle-sample-rate-in-normal-platform-and-whale-platform.patch \
           file://0010-Bug-2045388-change-module-loading-order.patch \
           file://0011-Bug-2126317-support-analog-typeC-headphone.patch \
           file://0012-Bug-2130909-ucm-conf-for-different-board.patch \
           "
do_compile_prepend() {
        mkdir -p ${S}/src/audiohal
        cp -r ${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/*  ${S}/src/audiohal/
        cp -r ${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audioutils ${S}/src/
}

do_install_append() {
        install -d ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets
        install -m 0644 ${WORKDIR}/${MACHINE}/sprd_default.conf  ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets/sprd_default.conf
        install -d ${D}${datadir}/pulseaudio/alsa-mixer/paths
        install -m 0644 ${WORKDIR}/${MACHINE}/music-analog-output.conf  ${D}${datadir}/pulseaudio/alsa-mixer/paths/music-analog-output.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/headphone_output.conf  ${D}${datadir}/pulseaudio/alsa-mixer/paths/headphone_output.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/speaker_output.conf  ${D}${datadir}/pulseaudio/alsa-mixer/paths/speaker_output.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/earpiece_output.conf  ${D}${datadir}/pulseaudio/alsa-mixer/paths/earpiece_output.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/sprd-input-headphone-mic.conf  ${D}${datadir}/pulseaudio/alsa-mixer/paths/sprd-input-headphone-mic.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/sprd-input-internal-mic.conf  ${D}${datadir}/pulseaudio/alsa-mixer/paths/sprd-input-internal-mic.conf
        install -m 0644 ${WORKDIR}/${MACHINE}/sprd-input-internal-signal-mic.conf  ${D}${datadir}/pulseaudio/alsa-mixer/paths/sprd-input-internal-signal-mic.conf
        install -d ${D}/etc/dbus-1/system.d/
        install -m 0644 ${WORKDIR}/${MACHINE}/pulseaudio-bluetooth.conf  ${D}/etc/dbus-1/system.d/
        install -m 0644 ${WORKDIR}/${MACHINE}/pulseaudio-ofono.conf  ${D}/etc/dbus-1/system.d/
        install -d ${D}${datadir}/pulseaudio/
        install -m 0644 ${WORKDIR}/audio_policy.conf ${D}${datadir}/pulseaudio/
}

FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/profile-sets/sprd_default.conf"
FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/paths/music-analog-output.conf"
FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/paths/headphone_output.conf"
FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/paths/speaker_output.conf"
FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/paths/earpiece_output.conf"
FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/paths/sprd-input-headphone-mic.conf"
FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/paths/sprd-input-internal-mic.conf"
FILES_${PN} += "${datadir}/pulseaudio/alsa-mixer/paths/sprd-input-internal-signal-mic.conf"
FILES_${PN} += "${datadir}/pulseaudio/audio_policy.conf"

FILES_${PN}-server += "${bindir}/pacat ${bindir}/paplay ${bindir}/parec ${bindir}/pacmd"

RDEPENDS_pulseaudio-server += " \
    pulseaudio-module-loopback \
    pulseaudio-module-unisoc-card \
    pulseaudio-module-device-manager \
"

RDEPENDS_pulseaudio-server += "\
        ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', '\
                pulseaudio-module-bluetooth-policy \
                pulseaudio-module-bluetooth-discover \
                pulseaudio-module-bluez5-device \
                pulseaudio-module-bluez5-discover \
        ', '', d)}"

#deltask do_rm_work

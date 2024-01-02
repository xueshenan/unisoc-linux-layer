inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/alsa-utils:"

SRC_URI_append += " \
           file://0001-aplay-snd_pcm_drop.patch \
           "
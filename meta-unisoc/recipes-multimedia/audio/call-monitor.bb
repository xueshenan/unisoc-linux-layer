SUMMARY = "voicecall monitor"
MEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

DEPENDS = "dbus dbus-glib glib-2.0 audioatci ofono pulseaudio"
RDEPENDS_${PN} = "dbus dbus-glib glib-2.0 audioatci ofono"

PROVIDES = "call-monitor"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/call_monitor"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export AUDUO_PLATFORM = "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'whale', 'normal', d)}"

python do_pre_compile() {
   bb.plain("***********************");
   bb.plain("*                     *");
   bb.plain("*  Hello, call_monitor!  *");
   bb.plain("*                     *");
   bb.plain("***********************");
}

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${B}/call_monitor ${D}${bindir}/
}

FILES_${PN} += "${bindir}/call_monitor"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

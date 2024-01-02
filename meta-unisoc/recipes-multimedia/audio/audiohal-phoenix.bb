SUMMARY = "audiohal to do some audio works as android HAL"
MEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"

PV = "0.1"
PR = "r0"

export AUDUO_PLATFORM = "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'whale', 'normal', d)}"


DEPENDS = "expat tinyxml-phoenix tinyalsa-phoenix chm-at audioutils"
RDEPENDS_${PN} = "expat tinyxml-phoenix tinyalsa-phoenix chm-at audioutils"

DEPENDS += "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'tinycompress-phoenix', 'nvexchange vbeffect vbpga', d)}"
RDEPENDS_${PN} += "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'tinycompress-phoenix', 'nvexchange vbeffect vbpga', d)}"


PROVIDES = "audiohal_phoenix"

EXTERNALSRC = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

S = "${WORKDIR}"

python do_pre_compile() {
   bb.plain("********************");
   bb.plain("*                  *");
   bb.plain("*  Hello, audiohal!  *");
   bb.plain("*                  *");
   bb.plain("********************");
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libaudiohal.so ${D}${libdir}/

    install -d ${D}${sysconfdir}/audio/
    install -d ${D}${sysconfdir}/audio/audio_param
    if [ ${AUDUO_PLATFORM} = "normal" ]; then
        install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/audio_para ${D}${sysconfdir}/audio/audio_param/audio_para
    fi
    if [ ${AUDUO_PLATFORM} = "whale" ]; then
         install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/audio_process.xml ${D}${sysconfdir}/audio/audio_param/audio_process.xml
         install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/audio_structure.xml ${D}${sysconfdir}/audio/audio_param/audio_structure.xml
         install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/audio_pga.xml ${D}${sysconfdir}/audio/audio_param/audio_pga.xml
         install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/codec.xml ${D}${sysconfdir}/audio/audio_param/codec.xml
         install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/cvs.xml ${D}${sysconfdir}/audio/audio_param/cvs.xml
         install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/dsp_vbc.xml ${D}${sysconfdir}/audio/audio_param/dsp_vbc.xml
    fi
    install -m 0777 ${THISDIR}/files/${MACHINE}/audio_param/audioparam_config.xml ${D}${sysconfdir}/audio/audio_param/audioparam_config.xml
    install -d ${D}${sysconfdir}/audio/config
    install -m 0777 ${THISDIR}/files/${MACHINE}/config/audio_config.xml ${D}${sysconfdir}/audio/config/audio_config.xml
    install -m 0777 ${THISDIR}/files/${MACHINE}/config/audio_pcm.xml ${D}${sysconfdir}/audio/config/audio_pcm.xml
    install -m 0777 ${THISDIR}/files/${MACHINE}/config/audio_route.xml ${D}${sysconfdir}/audio/config/audio_route.xml
    install -m 0777 ${THISDIR}/files/${MACHINE}/config/codec_pga.xml ${D}${sysconfdir}/audio/config/codec_pga.xml
    install -d ${D}${sysconfdir}/audio/native_mmi
    install -m 0777 ${THISDIR}/files/native_mmi/audio_sample.pcm ${D}${sysconfdir}/audio/native_mmi/audio_sample.pcm
    #install audio firmware
    install -d ${D}${nonarch_base_libdir}/firmware/
    ln -sf /var/audio/vbc_eq ${D}${nonarch_base_libdir}/firmware/vbc_eq
    ln -sf /var/audio/audio_params/dsp_vbc ${D}${nonarch_base_libdir}/firmware/dsp_vbc
    ln -sf /var/audio/audio_params/cvs ${D}${nonarch_base_libdir}/firmware/cvs
    ln -sf /var/audio/audio_params/audio_structure ${D}${nonarch_base_libdir}/firmware/audio_structure
}

FILES_${PN} += "/lib/firmware/*"
FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

#deltask do_rm_work

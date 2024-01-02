DESCRIPTION = "Unisoc factoryradiotest module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require chmpty.inc

SECTION = "libs"
#PV = "0.1"
#PR = "r0"
DEPENDS = "iniparser engpc"
RDEPENDS_${PN} = "iniparser engpc"
PROVIDES = "factoryradiotest"

EXTERNALSRC = "${EXTERNALSRC_DIR}/factory_radio_test"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}/factory_radio_test"

#check audio hal is use whale or normal
export AUDIO_PLATFORM = "${@bb.utils.contains('USE_AUDIO_WHALE_HAL', 'true', 'whale', 'normal', d)}"

python do_pre_compile() {
   bb.plain("******************************");
   bb.plain("*                            *");
   bb.plain("*  Hello, FactoryRadioTest!  *");
   bb.plain("*                            *");
   bb.plain("******************************");
}

do_install_prepend () {
    if [ ${CHM_SRC_MODE} != "customer" ]; then
        rm -rf ${CHM_INSTALL_DIR}/factory_radio_test
        install -d ${CHM_INSTALL_DIR}/factory_radio_test
        install -m 0777 ${S}/libfactoryradiotest.so ${CHM_INSTALL_DIR}/factory_radio_test
        install -m 0777 ${S}/*.h ${CHM_INSTALL_DIR}/factory_radio_test
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${libdir}/npidevice/
    install -m 0777 ${S}/libfactoryradiotest.so ${D}${libdir}/
    install -m 0777 ${S}/libfactoryradiotest.so ${D}${libdir}/npidevice/
    install -d ${D}${includedir}/factory_radio_test/
    install -m 0777 ${S}/*.h ${D}${includedir}/factory_radio_test/
}

FILES_${PN} += "${libdir}/libfactoryradiotest.so"
FILES_${PN} += "${libdir}/npidevice/libfactoryradiotest.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

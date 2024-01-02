DESCRIPTION = "Unisoc ota-adapter module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bin"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f7b40df666d41e6508d03e1c207d498f"
PROVIDES = "ota_adapter"
PV = "0.1"
PR = "r0"

DEPENDS = "iniparser"
RDEPENDS_${PN} = "iniparser"

EXTERNALSRC = "${OEROOT}/source/unisoc/ota_adapter/src/"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/ota_adapter/"

inherit systemd update-rc.d

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/ota_adapter ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${THISDIR}/files/script/otaadapter-init ${D}${sysconfdir}/init.d/otaadapter-init

    install -d ${D}${libdir}/
    install -m 0777 ${OEROOT}/source/unisoc/ota_adapter/lib/libdmgr.so ${D}${libdir}/

    install -d ${D}${sysconfdir}/
    install -m 0777 ${THISDIR}/files/certLog.conf ${D}${sysconfdir}/

    install -d ${D}${sysconfdir}/ota_adapter/
    install -m 0777 ${THISDIR}/files/config/4g_info.ini ${D}${sysconfdir}/ota_adapter/4g_info.ini
    install -m 0777 ${THISDIR}/files/config/5g_info.ini ${D}${sysconfdir}/ota_adapter/5g_info.ini
    install -m 0777 ${THISDIR}/files/config/mcum_info.ini ${D}${sysconfdir}/ota_adapter/mcum_info.ini
    install -m 0777 ${THISDIR}/files/config/mcus_info.ini ${D}${sysconfdir}/ota_adapter/mcus_info.ini
    install -m 0777 ${THISDIR}/files/config/4g_conf.ini ${D}${sysconfdir}/ota_adapter/4g_conf.ini
    install -m 0777 ${THISDIR}/files/config/5g_conf.ini ${D}${sysconfdir}/ota_adapter/5g_conf.ini
    install -m 0777 ${THISDIR}/files/config/mcum_conf.ini ${D}${sysconfdir}/ota_adapter/mcum_conf.ini
    install -m 0777 ${THISDIR}/files/config/mcus_conf.ini ${D}${sysconfdir}/ota_adapter/mcus_conf.ini
    
    install -m 0777 ${THISDIR}/files/config/ota_adapter.ini ${D}${sysconfdir}/ota_adapter/ota_adapter.ini

    install -d ${D}${includedir}/
    install -m 0777 ${OEROOT}/source/unisoc/ota_adapter/inc/dmgr_api.h ${D}${includedir}/
}

FILES_${PN} += " \
     ${libdir}/libdmgr.so \
     ${bindir}/ota_adapter \
     ${sysconfdir}/certLog.conf \
     ${includedir}/dmgr_api.h \
"

FILES_SOLIBSDEV = ""
TARGET_CC_ARCH += "${LDFLAGS}"

INITSCRIPT_NAME = "otaadapter-init "
INITSCRIPT_PARAMS = "defaults 30"

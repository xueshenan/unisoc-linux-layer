DESCRIPTION = "Unisoc gnss module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require gnss.inc

SECTION = "lib"
PV = "0.1"
PR = "r0"
PROVIDES = "gnss"
DEPENDS = "openssl wbxml2 libxml2 iniparser"
RDEPENDS_${PN} = "openssl wbxml2 libxml2 iniparser"
EXTERNALSRC_BUILD = "${EXTERNALSRC}/"

#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
#INHIBIT_PACKAGE_STRIP = "1"


do_install_prepend () {
    if [ ${GNSS_SRC_MODE} != "customer" ]; then
        rm -rf ${GNSS_INSTALL_DIR}/gnssmgt
        install -d ${GNSS_INSTALL_DIR}/gnssmgt/gnss
        install -d ${GNSS_INSTALL_DIR}/gnssmgt/agnss/mgt
        install -d ${GNSS_INSTALL_DIR}/gnssmgt/agnss/assit_gnss
        install -d ${GNSS_INSTALL_DIR}/gnssmgt/gnsspc
        install -d ${GNSS_INSTALL_DIR}/gnssmgt/gnss/common/inc/
        install -m 0777 ${S}/gnss/libgnssmgt.so ${GNSS_INSTALL_DIR}/gnssmgt/gnss
        install -m 0777 ${S}/agnss/mgt/liblcsmgt.so ${GNSS_INSTALL_DIR}/gnssmgt/agnss/mgt
        install -m 0777 ${S}/agnss/assit_gnss/libsupl.so ${GNSS_INSTALL_DIR}/gnssmgt/agnss/assit_gnss/
        install -m 0777 ${S}/gnsspc/libgpspc.so ${GNSS_INSTALL_DIR}/gnssmgt/gnsspc
        install -m 0666 ${S}/gnss/common/inc/gps.h ${GNSS_INSTALL_DIR}/gnssmgt/gnss/common/inc
        install -m 0666 ${S}/gnss/common/inc/gnssmgt_api.h  ${GNSS_INSTALL_DIR}/gnssmgt/gnss/common/inc

        rm -rf ${GNSS_INSTALL_DIR}/gnssmeta
        install -d ${GNSS_INSTALL_DIR}/gnssmeta/misc
        install -d ${GNSS_INSTALL_DIR}/gnssmeta/lib/32bit
        install -d ${GNSS_INSTALL_DIR}/gnssmeta/lib/64bit
        install -m 0444 ${S}/../gnssmeta/misc/config.xml ${GNSS_INSTALL_DIR}/gnssmeta/misc/config.xml
        install -m 0444 ${S}/../gnssmeta/misc/supl.xml ${GNSS_INSTALL_DIR}/gnssmeta/misc/supl.xml
        install -m 0444 ${S}/../gnssmeta/misc/spirentroot.cer  ${GNSS_INSTALL_DIR}/gnssmeta/misc/spirentroot.cer

        install -m 0777 ${S}/../gnssmeta/lib/32bit/liblte.so ${GNSS_INSTALL_DIR}/gnssmeta/lib/32bit
        install -m 0777 ${S}/../gnssmeta/lib/64bit/liblte.so ${GNSS_INSTALL_DIR}/gnssmeta/lib/64bit

        install -m 0777 ${S}/../gnssmeta/lib/32bit/libnavcore.so ${GNSS_INSTALL_DIR}/gnssmeta/lib/32bit
        install -m 0777 ${S}/../gnssmeta/lib/64bit/libnavcore.so ${GNSS_INSTALL_DIR}/gnssmeta/lib/64bit

        install -d ${GNSS_INSTALL_DIR}/gnssmgt/common
        echo "[GNSS]" > ${GNSS_INSTALL_DIR}/gnssmgt/common/prop.ini
        echo "ro.build.type=${@bb.utils.contains('USERDEBUG', 'userdebug', 'userdebug', 'user', d)}" >> ${S}/common/prop.ini
        echo "ro.vendor.gnsschip=${GNSS_CHPID}" >> ${S}/common/prop.ini
        echo "[AGNSS]" >> ${GNSS_INSTALL_DIR}/gnssmgt/common/prop.ini
        echo "persist.vendor.modem.l.enable=1" >> ${GNSS_INSTALL_DIR}/gnssmgt/common/prop.ini
        echo "ro.vendor.modem.tty=/dev/stty_lte" >> ${GNSS_INSTALL_DIR}/gnssmgt/common/prop.ini
    fi
}

do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${sysconfdir}/

    install -m 0444 ${S}/../gnssmeta/misc/config.xml ${D}${sysconfdir}/
    install -m 0444 ${S}/../gnssmeta/misc/supl.xml ${D}${sysconfdir}/
    install -m 0444 ${S}/../gnssmeta/misc/spirentroot.cer  ${D}${sysconfdir}/

    install -m 0777 ${S}/../gnssmeta/lib/${LIB_PATH}/liblte.so ${D}${libdir}/
    install -m 0777 ${S}/../gnssmeta/lib/${LIB_PATH}/libnavcore.so ${D}${libdir}/

    install -m 0777 ${S}/gnss/libgnssmgt.so ${D}${libdir}/
    install -m 0777 ${S}/agnss/mgt/liblcsmgt.so ${D}${libdir}/
    install -m 0777 ${S}/agnss/assit_gnss/libsupl.so ${D}${libdir}/
    install -d ${D}${libdir}/npidevice/
    install -m 0777 ${S}/gnsspc/libgpspc.so ${D}${libdir}/npidevice/
    install -d ${D}${includedir}/gnss/
    install -m 0666 ${S}/gnss/common/inc/gps.h ${D}${includedir}/gnss/
    install -m 0666 ${S}/gnss/common/inc/gnssmgt_api.h ${D}${includedir}/gnss/

    echo "[GNSS]" > ${S}/common/prop.ini
    echo "ro.build.type=${@bb.utils.contains('USERDEBUG', 'userdebug', 'userdebug', 'user', d)}" >> ${S}/common/prop.ini
    echo "ro.vendor.gnsschip=${GNSS_CHPID}" >> ${S}/common/prop.ini
    echo "[AGNSS]" >> ${S}/common/prop.ini
    echo "persist.vendor.modem.l.enable=1" >> ${S}/common/prop.ini
    echo "ro.vendor.modem.tty=/dev/stty_lte" >> ${S}/common/prop.ini
    install -d ${D}${sysconfdir}/gnss/
    install -m 0777 ${S}/common/prop.ini ${D}${sysconfdir}/gnss/prop.ini
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


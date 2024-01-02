DESCRIPTION = "Unisoc nvitem module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require nvitem.inc
SECTION = "bins"
PROVIDES = "nvitem"
DEPENDS = "modem-utils iniparser"
RDEPENDS_${PN} = "modem-utils iniparser"

inherit systemd update-rc.d

EXTERNALSRC = "${EXTERNALSRC_DIR}"
EXTERNALSRC_BUILD =  "${EXTERNALSRC_DIR}"

SRC_URI = " \
    file://cp_diskserver.service \
    file://cp_diskserver-init \
"
do_compile_prepend (){
    if [ ${STORAGE_TYPE} = "nand" ]; then
        STORAGE_TYPE_NAND_FLAGS="-DCONFIG_NAND_UBI_VOL"
        export STORAGE_TYPE_NAND_FLAGS
    elif [ ${STORAGE_TYPE} = "emmc" ]; then
        STORAGE_TYPE_EMMC_FLAGS="-DCONFIG_EMMC"
        export STORAGE_TYPE_EMMC_FLAGS
    fi
    SUPPORT_AB_PARTITIONS_FLAGS=""
    if [ ${AB_PARTITIONS} = "true" ]; then
        SUPPORT_AB_PARTITIONS_FLAGS="-DAB_PARTITIONS"
        export SUPPORT_AB_PARTITIONS_FLAGS
    fi
}

do_install_prepend () {
    if [ ${MODEM_SRC_MODE} != "customer" ]; then
        rm -rf ${MODEM_INSTALL_DIR}/cp_diskserver
        install -d ${MODEM_INSTALL_DIR}
        install -m 0755 ${S}/cp_diskserver ${MODEM_INSTALL_DIR}
    fi
}

do_install () {

    install -d ${D}${bindir}
    install -m 0755 ${S}/cp_diskserver ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/cp_diskserver-init ${D}/${sysconfdir}/init.d/cp_diskserver-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/cp_diskserver.service ${D}${systemd_unitdir}/system
}

FILES_${PN}-cp_diskserver = " \
    ${systemd_unitdir}/system/cp_diskserver.service \
"

TARGET_CC_ARCH += "${LDFLAGS}"

INITSCRIPT_NAME = "cp_diskserver-init"
INITSCRIPT_PARAMS = "start 01 2 3 4 5 . stop 86 0 6 . "

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "cp_diskserver.service"
NATIVE_SYSTEMD_SUPPORT = "1"

DESCRIPTION = "Unisoc libreadfixednv module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require libreadfixednv.inc
SECTION = "bins"
PROVIDES = "libreadfixednv"
DEPENDS = "modem-utils iniparser engpc"
RDEPENDS_${PN} = "modem-utils iniparser"

EXTERNALSRC = "${EXTERNALSRC_DIR}"
EXTERNALSRC_BUILD =  "${EXTERNALSRC_DIR}"

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
        rm -rf ${MODEM_INSTALL_DIR}
        install -d ${MODEM_INSTALL_DIR}
        install -m 0755 ${S}/libreadfixednv.so ${MODEM_INSTALL_DIR}
    fi
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0755 ${S}/libreadfixednv.so ${D}${libdir}/npidevice
}

FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${libdir}/npidevice/libreadfixednv.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"

TARGET_CC_ARCH += "${LDFLAGS}"


NATIVE_SYSTEMD_SUPPORT = "1"

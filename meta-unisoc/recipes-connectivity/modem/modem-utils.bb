DESCRIPTION = "Unisoc modem utils module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "libs"
LIC_FILES_CHKSUM = ""
PROVIDES = "modem-utils"

DEPENDS = "iniparser"
DEPENDS += "${@bb.utils.contains('BOARD_SECBOOT_CONFIG','true','libkernelbootcp','',d)}"
DEPENDS += "${@bb.utils.contains('SUPPORT_ORCA_LED','true','liborcaled','',d)}"
RDEPENDS_${PN} = "iniparser"
RDEPENDS_${PN} += "${@bb.utils.contains('BOARD_SECBOOT_CONFIG','true','libkernelbootcp','',d)}"
RDEPENDS_${PN} += "${@bb.utils.contains('SUPPORT_ORCA_LED','true','liborcaled','',d)}"

EXTERNALSRC = "${OEROOT}/source/unisoc/modem-utils"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

do_compile_prepend (){
    if [ ${STORAGE_TYPE} = "nand" ]; then
        STORAGE_TYPE_NAND_FLAGS="-DCONFIG_NAND_UBI_VOL"
        export STORAGE_TYPE_NAND_FLAGS
    fi
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0755 ${S}/libmodem_utils.so ${D}${libdir}/

    install -d ${D}${includedir}/
    install -m 0644 ${S}/*.h ${D}${includedir}/

if [ ${@bb.utils.contains('USERDEBUG', 'userdebug', 'yes', 'no', d)} = yes ]
then
    install -d ${D}${sysconfdir}/modem/
    install -m 0777 ${THISDIR}/files/${MACHINE}/system_userdebug.ini ${D}${sysconfdir}/modem/modem.ini
else
    install -d ${D}${sysconfdir}/modem/
    install -m 0777 ${THISDIR}/files/${MACHINE}/system_user.ini ${D}${sysconfdir}/modem/modem.ini
fi
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

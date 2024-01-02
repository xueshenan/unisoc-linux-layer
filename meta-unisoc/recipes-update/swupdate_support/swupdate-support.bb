DESCRIPTION = "unisoc ota patch based on swupdate"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2+"
SECTION = "lib"
DEPENDS += "bzip2 mtd-utils libubootenv openssl otaarith librsync"
RDEPENDS_{PN} += "bzip2"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"
PROVIDES = "swupdate-support"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${includedir}/
    make install INSTALL_LIB_DIR=${D}${libdir} INSTALL_INC_DIR=${D}${includedir}
}

FILES_${PN} += "${libdir}/libswupdate-support.a"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

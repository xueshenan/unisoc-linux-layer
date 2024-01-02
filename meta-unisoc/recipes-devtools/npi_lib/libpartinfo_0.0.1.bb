DESCRIPTION = "Unisoc libpartinfo module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "libpartinfo"
DEPENDS = "libmodeminictl modem-utils iniparser engpc"
RDEPENDS_${PN} = "libmodeminictl modem-utils iniparser"

PV = "0.1"
PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/libpartinfo;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

LIBMISCDATA_SECSET ?= "${@bb.utils.contains('STORAGE_TYPE','nand','-DCONFIG_NAND','',d)}"
export CONFIG_NAND_FLAG="${LIBMISCDATA_SECSET}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libpartinfo.so ${D}${libdir}/npidevice
}

do_compile_prepend (){
    if [ ${STORAGE_TYPE} = "nand" ]; then
        STORAGE_TYPE_NAND_FLAGS="-DCONFIG_NAND_UBI_VOL"
        export STORAGE_TYPE_NAND_FLAGS
    elif [ ${STORAGE_TYPE} = "emmc" ]; then
        STORAGE_TYPE_EMMC_FLAGS="-DCONFIG_EMMC"
        export STORAGE_TYPE_EMMC_FLAGS
    fi
}
FILES_${PN} += "${libdir}/npidevice/libpartinfo.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


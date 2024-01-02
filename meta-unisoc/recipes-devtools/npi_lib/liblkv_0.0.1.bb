DESCRIPTION = "Unisoc liblkv_other module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "liblkv"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/liblkv;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

LIBLKVCONTROL_SECSET ?= "${@bb.utils.contains('SUPPORT_ORCA_LED','true','-DSYS_SUPPORT_ORCA_LED_','',d)}"
export SUPPORT_LED_FLAG="${LIBLKVCONTROL_SECSET}"

LIBMISCDATA_SECSET ?= "${@bb.utils.contains('ENABLE_LIGHT','true','-DENABLE_LIGHT','',d)}"
export CONFIG_LIGHT_FLAG="${LIBMISCDATA_SECSET}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/liblkv.so ${D}${libdir}/npidevice
}

FILES_${PN} += "${libdir}/npidevice/liblkv.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

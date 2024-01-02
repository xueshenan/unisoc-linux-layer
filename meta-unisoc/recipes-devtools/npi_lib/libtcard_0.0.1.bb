CRIPTION = "Unisoc libtcard module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "libs"
PROVIDES = "libtcard"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/libtcard;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"
LIBSDCARD_SECSET ?= "${@bb.utils.contains('SDCARD_TYPE','sdcard','-DCONFIG_SDCARD','',d)}"
export CONFIG_SDCARD_FLAG = "${LIBSDCARD_SECSET}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libtcard.so ${D}${libdir}/npidevice
}

FILES_${PN} += "${libdir}/npidevice/libtcard.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

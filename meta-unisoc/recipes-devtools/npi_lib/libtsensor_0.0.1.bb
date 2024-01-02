DESCRIPTION = "Unisoc libcharge module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "libs"
PROVIDES = "libtsensor"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

LIC_FILES_CHKSUM = "file://COPYING;md5=36dba8813a86f10cc3ecfbcf89dc6eab"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/libtsensor;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libtsensor.so ${D}${libdir}/npidevice
}

FILES_${PN} += "${libdir}/npidevice/libtsensor.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

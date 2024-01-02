DESCRIPTION = "Unisoc libusbtypec module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "libusbtypec"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/libusbtypec;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libusbtypec.so ${D}${libdir}/npidevice
}

FILES_${PN} += "${libdir}/npidevice/libusbtypec.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

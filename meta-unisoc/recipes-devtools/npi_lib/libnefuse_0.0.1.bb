DESCRIPTION = "Unisoc libgpio module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "libnefuse"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/libnefuse;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libnefuse.so ${D}${libdir}/npidevice
}

FILES_${PN} += "${libdir}/npidevice/libnefuse.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

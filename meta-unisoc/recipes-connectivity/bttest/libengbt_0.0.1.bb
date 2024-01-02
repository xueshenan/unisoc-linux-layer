DESCRIPTION = "Unisoc vendor bluetooth module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS ="libcap"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
PV = "0.1"
PR = "r0"
PROVIDES = ""

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/wcn;protocol=ssh;branch=unc_linux_trunk_2.0"

do_compile () {
    make clean -C ${S} OBJ_DIR=${B}
    make libengbt -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${libdir}/
    install -d ${D}${libdir}/npidevice/
    install -m 0777 ${B}/libengbt.so ${D}${libdir}/npidevice/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc power manager module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "lib"
DEPENDS = ""
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
PV = "0.1"
PR = "r0"
PROVIDES = "pm"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/pm;protocol=ssh;branch=unc_linux_trunk"

do_compile () {
    make libpm -C ${S} OBJ_DIR=${B}
    make libinput-uevent -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${B}/libpm.so ${D}${libdir}/
    install -m 0777 ${B}/libinput-uevent.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/src/pm.h ${D}${includedir}/
    install -m 0777 ${S}/input_uevent/input_uevent.h ${D}${includedir}/
}

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

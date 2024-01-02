DESCRIPTION = "Unisoc liborca module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "libs"
PROVIDES = "liborca"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/liborca;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/liborca.so ${D}${libdir}/npidevice

    if [ ${MACHINE} = "udx710-module" ] || [ ${MACHINE} = "udx710-module-op" ] || [ ${MACHINE} = "udx710-ecell" ] || [ ${MACHINE} = "sc9863a-smartcoreboard" ] || \
       [ ${MACHINE} = "udx710-module-pi" ] || [ ${MACHINE} = "udx710-module-vddmodem-pi" ] || \
       [ ${MACHINE} = "udx710-module-op-pi" ] || [ ${MACHINE} = "udx710-mifi" ]; then
      install -d ${D}${sysconfdir}/engpc/gpiotest/
      install -m 0777 ${THISDIR}/files/${MACHINE}/config/gpiotest/conntest ${D}${sysconfdir}/engpc/gpiotest/conntest
      install -m 0777 ${THISDIR}/files/${MACHINE}/config/gpiotest/shorttest ${D}${sysconfdir}/engpc/gpiotest/shorttest
    fi
}

FILES_${PN} += "${libdir}/npidevice/liborca.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "module for witi auto test or bbat"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "BSD"
SECTION = "libs"
PROVIDES = "libautotestwifi"
PV = "0.1"
PR = "r0"

DEPENDS = "engpc"

LIC_FILES_CHKSUM = "file://COPYING;md5=dbef6e6ff4b7cc05121404cce25b332e"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/wcn/libautotestwifi"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make OEROOT=${OEROOT}
}

do_install () {
    install -d ${D}${libdir}/npidevice
    install -m 0777 ${S}/libautotestwifi.so ${D}${libdir}/npidevice
}

FILES_${PN} += " \
    ${libdir}/npidevice/libautotestwifi.so \
"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

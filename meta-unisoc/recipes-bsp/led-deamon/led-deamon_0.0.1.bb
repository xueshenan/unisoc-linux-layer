DESCRIPTION = "Unisoc RGB LED deamon"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
PROVIDES = "led-deamon"
PV = "0.1"
PR = "r0"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

SRC_URI = "\
"

inherit update-rc.d systemd

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/led-deamon ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/led-deamon-init.sh ${D}${sysconfdir}/init.d/orcaled-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "orcaled-init"
INITSCRIPT_PARAMS_${PN} = "defaults 10"

FILES_${PN} += " \
    ${bindir}/led-deamon \
    ${sysconfdir}/init.d/orcaled-init \
"
TARGET_CC_ARCH += "${LDFLAGS}"

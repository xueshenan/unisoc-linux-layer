DESCRIPTION = "Unisoc bt demo program"
HOMEPAGE = "http://www.unisoc.com/"

DEPENDS = "dbus glib-2.0 bluez5"
RDEPENDS_${PN} = "dbus glib-2.0 bluez5"

LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
PV = "0.1"
PR = "r0"

#inherit autotools
inherit autotools-brokensep pkgconfig

PROVIDES = "bt-tool"

EXTERNALSRC = "${OEROOT}/source/unisoc/demo/bt-tool"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/demo/bt-tool"



do_compile () {
    make clean
    make bt-tool -C ${S} OBJ_DIR=${B} WORKDIR=${WORKDIR}
    make bt-cmd -C ${S} OBJ_DIR=${B} WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/bt-tool ${D}${bindir}/
    install -m 0777 ${B}/bt-cmd ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

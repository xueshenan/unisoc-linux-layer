DESCRIPTION = "lvgl drivers module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "MIT"
SECTION = "libs"
PROVIDES = "lvdrv"
LIC_FILES_CHKSUM =  "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "lvgl"
RDEPENDS_${PN} = "lvgl"


EXTERNALSRC = "${OEROOT}/source/unisoc/graphic/littlevgl/lvdrv"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}
    install -m 0755 ${S}/liblvdrv.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
    install -d ${D}${includedir}/display
    install -m 0777 ${S}/display/*.h ${D}${includedir}/display
    install -d ${D}${includedir}/indev
    install -m 0777 ${S}/indev/*.h ${D}${includedir}/indev
    install -d ${D}${includedir}/gtkdev
    install -m 0777 ${S}/gtkdrv/*.h ${D}${includedir}/gtkdev
}

deltask do_rm_work


FILES_${PN} += "${libdir}/liblvdrv.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

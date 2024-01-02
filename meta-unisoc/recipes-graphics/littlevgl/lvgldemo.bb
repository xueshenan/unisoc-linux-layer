DESCRIPTION = "Unisoc lvgl demo module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "MIT"
SECTION = "bins"
PROVIDES = "lvgldemo"
LIC_FILES_CHKSUM =  "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "lvgl lvdrv"
RDEPENDS_${PN} = "lvgl lvdrv"

EXTERNALSRC = "${OEROOT}/source/unisoc/graphic/littlevgl/demo"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/lvgldemo ${D}${bindir}/
    install -d ${D}${datadir}
    install -m 0777 ${S}/test_240_320_nv21.yuv ${D}${datadir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"
FILES_${PN} += " \
    ${datadir}/test_240_320_nv21.yuv \
    "
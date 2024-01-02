DESCRIPTION = "Unisoc lvgl demo module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "MIT"
SECTION = "bins"
PROVIDES = "lvglota"
LIC_FILES_CHKSUM =  "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "lvgl lvdrv"
RDEPENDS_${PN} = "lvgl lvdrv"

EXTERNALSRC_pn-${PN} = "${OEROOT}/source/unisoc/graphic/littlevgl/lvglota"
EXTERNALSRC_BUILD_pn-${PN} = "${EXTERNALSRC}"

do_compile () {
    oe_runmake -C ${S} OBJ_DIR=${WORKDIR}
    oe_runmake -C ${S} clean
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/lvglota ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"
FILES_${PN} += " \
    "
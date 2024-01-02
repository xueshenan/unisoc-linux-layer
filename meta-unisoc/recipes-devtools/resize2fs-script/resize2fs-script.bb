DESCRIPTION = "resize2fs-service"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
PV = "0.1"
PR = "r0"
inherit update-rc.d

RDEPENDS_${PN} = "e2fsprogs-resize2fs"

PROVIDES = "resize2fs-script"
SRC_URI = " \
            file://resize2fs-script.sh \
          "

INITSCRIPT_NAME = "resize2fs-script"
INITSCRIPT_PARAMS = "start 30 1 2 3 4 5 ."


do_compile () {
}

do_install () {

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/resize2fs-script.sh ${D}/${sysconfdir}/init.d/resize2fs-script

    install -d ${D}/${sbindir}
    install -m 0755 ${WORKDIR}/resize2fs-script.sh ${D}/${sbindir}/resize2fs-script.sh

}

TARGET_CC_ARCH += "${LDFLAGS}"


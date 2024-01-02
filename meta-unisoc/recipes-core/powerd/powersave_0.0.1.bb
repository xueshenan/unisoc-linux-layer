DESCRIPTION = "Unisoc powersave manager"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
SECTION = "bin"
PV = "0.1"
PR = "r0"
PROVIDES = "powersave.sh"

do_install() {
    if [ -e ${THISDIR}/files/lowpower.rules ]; then
         install -d ${D}${sysconfdir}/udev/rules.d
         install -m 0644 ${THISDIR}/files/lowpower.rules ${D}${sysconfdir}/udev/rules.d/lowpower.rules

         install -d ${D}${sysconfdir}
         install -m 0755 ${THISDIR}/files/powersave.sh ${D}${sysconfdir}
    fi
}

FILES_${PN} += " \
    ${sysconfdir}/udev/rules.d/lowpower.rules \
    ${sysconfdir}/powersave.sh \
"

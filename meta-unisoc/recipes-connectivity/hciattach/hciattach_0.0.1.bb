DESCRIPTION = "Unisoc hciattach program"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=e87f92461fb10894c6d772e032c8d02c"
PV = "0.1"
PR = "r0"
PROVIDES = "hciattach"
DEPENDS = "libbt"
RDEPENDS_${PN} = "rfkill libbt"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/hciattach;protocol=ssh;branch=unc_linux_trunk_2.0"

inherit systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "unisoc_bt.service"

do_compile () {
    make hciattach_sprd -C ${S} OBJ_DIR=${B}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${B}/hciattach_sprd ${D}${bindir}/
	install -d ${D}${systemd_unitdir}/system/
	install -m 0644 ${THISDIR}/files/unisoc_bt.service ${D}${systemd_unitdir}/system
}

TARGET_CC_ARCH += "${LDFLAGS}"

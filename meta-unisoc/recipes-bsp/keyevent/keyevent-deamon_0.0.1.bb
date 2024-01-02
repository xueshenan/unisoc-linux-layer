DESCRIPTION = "Unisoc Orca-keyevent"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
PROVIDES = "keyevent-deamon"
PV = "0.1"
PR = "r0"

DEPENDS = "atci"
RDEPENDS_${PN} = "atci"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/keyevent/orca-keyevent;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit systemd update-rc.d

export AP_DEVICE_AUTOSLEEP_CFLAGS = "${AP_DEVICE_AUTOSLEEP_SET}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/keyevent-deamon ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${THISDIR}/files/orca-keyevent-init.sh ${D}/${sysconfdir}/init.d/orca-keyevent-init
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "orca-keyevent-init"
INITSCRIPT_PARAMS_${PN} = "defaults 95"

FILES_${PN} += " \
    ${bindir}/keyevent-deamon \
    ${sysconfdir}/init.d/orca-keyevent-init.sh \
"
TARGET_CC_ARCH += "${LDFLAGS}"


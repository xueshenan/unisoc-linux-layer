DESCRIPTION = "test tool - sleepwakeup"
LICENSE = "Unisoc-General-1.0"
PV = "0.1"
PR = "r0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"
PROVIDES = "sleepwakeup"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/powerd;protocol=ssh;branch=unc_linux_truck"

DEPENDS = "dbus"
RDEPENDS_${PN} = "dbus"

export AP_DEVICE_AUTOSLEEP_CFLAGS = "${AP_DEVICE_AUTOSLEEP_SET}"

do_compile () {
    make clean
    make SYS_ROOT=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/sleepwakeup ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"

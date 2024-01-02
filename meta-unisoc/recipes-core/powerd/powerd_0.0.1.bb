DESCRIPTION = "powerd-service"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=bcd3ab47632e78cf1a3581b0b9e52794"
PV = "0.1"
PR = "r0"
PROVIDES = "powerd"
DEPENDS = "rgbled pm"
RDEPENDS_${PN} = "rgbled pm"
inherit systemd update-rc.d

SRC_URI = "\
            file://powerd.service \
            file://powerd-init \
"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "powerd-init"
INITSCRIPT_PARAMS_${PN} = "defaults 100"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "powerd.service"

export AP_DEVICE_LED_CFLAGS = "${AP_DEVICE_LED_SET}"
export AP_DEVICE_BATTERY_CFLAGS = "${AP_DEVICE_BATTERY_SET}"
export AP_DEVICE_KEYEVENT_CFLAGS = "${AP_DEVICE_KEYEVENT_SET}"
export AP_DEVICE_AUTOSLEEP_CFLAGS = "${AP_DEVICE_AUTOSLEEP_SET}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/powerd ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/powerd-init ${D}/${sysconfdir}/init.d/powerd-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/powerd.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"


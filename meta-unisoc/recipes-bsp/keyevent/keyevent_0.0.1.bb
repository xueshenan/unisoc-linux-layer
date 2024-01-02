DESCRIPTION = "keyevent-service"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
PV = "0.1"
PR = "r0"
inherit systemd update-rc.d
PACKAGES =+ "${PN}-keyevent \
"

PROVIDES = "keyevent"
DEPENDS = "rgbled"
RDEPENDS_${PN} = "rgbled \
${PN}-keyevent \
"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/keyevent/keyevent;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${EXTERNALSRC}/src"

INITSCRIPT_PACKAGES = "${PN}-keyevent"
INITSCRIPT_NAME_${PN}-keyevent = "keyevent-init"
INITSCRIPT_PARAMS_${PN}-keyevent = "defaults 98"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "keyevent.service"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/src/keyevent ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/script/keyevent-init.sh ${D}/${sysconfdir}/init.d/keyevent-init

    install -d ${D}/${sbindir}
    install -m 0755 ${S}/script/keyevent-init.sh ${D}/${sbindir}/keyevent-init.sh

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/script/keyevent.service ${D}${systemd_unitdir}/system
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN}-keyevent = " \
    ${sysconfdir}/init.d/keyevent-init \
    ${sbindir}/keyevent-init.sh \
    ${systemd_unitdir}/system/keyevent.service \
"


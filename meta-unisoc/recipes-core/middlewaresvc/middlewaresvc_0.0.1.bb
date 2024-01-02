DESCRIPTION = "lcd control service"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "UNISOC"
SECTION = "bin"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ec6785348c6cef0f1ba4743a50ff8aa"
PV = "0.1"
PR = "r0"
PROVIDES = "middlewaresvc"

DEPENDS = "dbus dbushelper powermanager"
RDEPENDS_${PN} = "dbus dbushelper"

inherit systemd update-rc.d pkgconfig

SRC_URI = "\
            file://middlewaresvc.service \
            file://middlewaresvc-init.sh \
            file://middlewaresvc.conf \
            file://default_middlewaresvc \
"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/middlewaresvc"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "middlewaresvc-init"
INITSCRIPT_PARAMS_${PN} = "start 50 3 4 5 . stop 50 0 6 ."

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "middlewaresvc.service"

export SYS_ROOT = "${WORKDIR}"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 middlewaresvc ${D}${bindir}/

    install -d ${D}/etc/dbus-1/system.d/
    install -m 0644 ${WORKDIR}/middlewaresvc.conf ${D}/etc/dbus-1/system.d/

    install -d ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/default_middlewaresvc ${D}/${sysconfdir}/default/middlewaresvc

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/middlewaresvc.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/middlewaresvc-init.sh ${D}${sysconfdir}/init.d/middlewaresvc-init
}

#do_install_append () {
#     install -d ${D}${includedir}/${PN}
#     install -m 0644 ${S}/${PN}.h ${D}${includedir}/${PN}
#}

# ${systemd_unitdir}
FILES_${PN} += "${bindir} ${sysconfdir}/init.d/ ${sysconfdir}/default"
TARGET_CC_ARCH += "${LDFLAGS}"
deltask do_rm_work

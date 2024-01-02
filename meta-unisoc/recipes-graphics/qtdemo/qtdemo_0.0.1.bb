DESCRIPTION = "qtdemo tools"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=5873d9fa7e36ae5ae49299e85208ebc0 \
                   "

SECTION = "bin"

PV = "0.1"
PR = "r0"

# systemd update-rc.d
inherit autotools
PACKAGES = "qtdemo \
"
FILESEXTRAPATHS_prepend := "${THISDIR}:${THISDIR}/files"
SRC_URI = " \
        file://qtdemo-init.sh \
        file://qtdemo.service \
        file://res/qtdemo.desktop \
        file://res/icon_qtdemo.png \
          "
PROVIDES = "qtdemo"

RDEPENDS_${PN} = "qtdemo \
                  qtbase \
                  qtquickcontrols \
                  qtquickcontrols2 \
                  qtdeclarative-qmlplugins \
                  qtquickcontrols-qmlplugins \
                 "

#INITSCRIPT_NAME = "qtdemo-init"
#INITSCRIPT_PARAMS = "start 99 5 ."

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}
do_install () {

    install -d ${D}${bindir}
    case "${TARGET_ARCH}" in
        "aarch64")
            install -m 0755 ${S}/demotest64 ${D}${bindir}/demotest
            ;;
        "arm")
            install -m 0755 ${S}/demotest ${D}${bindir}/demotest
            ;;
        *)
            ;;
    esac

    install -d ${D}${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/qtdemo-init.sh ${D}${sysconfdir}/init.d/qtdemo-init
    install -Dm 0644 ${WORKDIR}/qtdemo.service ${D}${systemd_system_unitdir}/qtdemo.service

    #install -d ${D}${datadir}
    install -dv -m -0655 ${D}${datadir}/applications/
    install -m 0666 ${WORKDIR}/res/qtdemo.desktop ${D}${datadir}/applications/
    install -dv -m -0655 ${D}${datadir}/icons/
    install -m 0666 ${WORKDIR}/res/icon_qtdemo.png ${D}${datadir}/icons/
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "qtdemo.service"

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} += " \
    ${bindir}/* \
    ${datadir}/applications \
    ${datadir}/icons \
    ${sysconfdir}/init.d \
    ${systemd_system_unitdir}/qtdemo.service \
"

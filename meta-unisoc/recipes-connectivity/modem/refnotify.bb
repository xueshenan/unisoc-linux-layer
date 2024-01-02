# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc refnotify module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bins"
PROVIDES = "refnotify"
DEPENDS = "modem-utils expat iniparser"
RDEPENDS_${PN} = "modem-utils expat iniparser"

inherit systemd update-rc.d
inherit get_release_mode

REFNOTIFY_SRC_DIR = "${OEROOT}/source/unisoc/connectivity/modem/refnotify"
REFNOTIFY_INSTALL_DIR = "${DEPLOY_OUT_DIR}"
REFNOTIFY_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/refnotify"

export REFNOTIFY_SRC_MODE="${@get_release_mode(d, "${REFNOTIFY_SRC_DIR}")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("REFNOTIFY_SRC_MODE", "customer", "${REFNOTIFY_RELEASE_DIR}", "${REFNOTIFY_SRC_DIR}", d)}"

do_compile () {
     if [ ${REFNOTIFY_SRC_MODE} != "customer" ]; then
         make clean
         make
     fi
}

EXTERNALSRC = "${EXTERNALSRC_DIR}"
EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}"

SRC_URI = " \
    file://refnotify.service \
    file://refnotify-init \
"

do_install_prepend () {
   if [ ${REFNOTIFY_SRC_MODE} != "customer" ]; then
	rm -rf ${REFNOTIFY_INSTALL_DIR}/refnotify
	install -d ${REFNOTIFY_INSTALL_DIR}/refnotify/
	install -m 0755 ${S}/refnotify ${REFNOTIFY_INSTALL_DIR}/refnotify/
   fi
}

do_install () {

    install -d ${D}${bindir}
    install -m 0755 ${S}/refnotify ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/refnotify-init ${D}/${sysconfdir}/init.d/refnotify-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/refnotify.service ${D}${systemd_unitdir}/system

}

FILES_${PN}-refnotify = " \
    ${systemd_unitdir}/system/refnotify.service \
"

FILES_${PN} += "${systemd_unitdir}"
TARGET_CC_ARCH += "${LDFLAGS}"
SYSTEMD_PACKAGES = "${PN}"

INITSCRIPT_NAME = "refnotify-init"
INITSCRIPT_PARAMS = "start 89 3 4 5 . stop 89 0 6 ."


SYSTEMD_SERVICE_${PN} = "refnotify.service"
NATIVE_SYSTEMD_SUPPORT = "1"


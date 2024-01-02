DESCRIPTION = "Unisoc slogmodem module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bins"
PROVIDES = "slogmodem"
DEPENDS = "modem-utils iniparser"

RDEPENDS_${PN} = "modem-utils iniparser"

export SUPPORT_CH_FLAG="${MODEM_CTRL_SUPPORT_CH}"
export ORCA_5G_SET_CFLAGS = "${ORCA_5G_SET}"
export SPRD_GNSS_ENABLE ?= "${@bb.utils.contains('MACHINE_FEATURES','gnss','true','',d)}"
export SPRD_CP_LOG_AGDSP="${SUPPORT_AGDSP}"

inherit systemd update-rc.d
inherit get_release_mode

SLOGMODEM_SRC_DIR = "${OEROOT}/source/unisoc/connectivity/modem/slogmodem"
SLOGMODEM_INSTALL_DIR = "${DEPLOY_OUT_DIR}"
SLOGMODEM_RELEASE_DIR = "${PREBUILTS_OUT_DIR}"

export SLOGMODEM_SRC_MODE="${@get_release_mode(d, "${SLOGMODEM_SRC_DIR}")}"

do_compile () {
     if [ ${SLOGMODEM_SRC_MODE} != "customer" ]; then
         make clean
         make
     fi
}

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/modem/slogmodem"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

SRC_URI = "file://slogmodem.service \
           file://mlogservice.service \
           file://slogmodem-init \
           file://slogmodem_user.service \
           file://mlogservice_user.service \
           file://slogmodem-init-user \
          "

do_install_prepend () {
   if [ ${MODEM_SRC_MODE} != "customer" ]; then
        rm -rf ${SLOGMODEM_INSTALL_DIR}/slogmodem
        install -d ${SLOGMODEM_INSTALL_DIR}/slogmodem
        install -m 0755 ${S}/slogmodem ${SLOGMODEM_INSTALL_DIR}/slogmodem
        install -m 0755 ${S}/mlogservice ${SLOGMODEM_INSTALL_DIR}/slogmodem
        install -m 0755 ${S}/cplogctl ${SLOGMODEM_INSTALL_DIR}/slogmodem

   if [ ${@bb.utils.contains('USERDEBUG', 'userdebug', 'yes', 'no', d)} = yes ]
   then
        install -d ${SLOGMODEM_INSTALL_DIR}/slogmodem
        if [ ${@bb.utils.contains('ORCA_5G_SET_CFLAGS', '-DORCA_5G_SUPPORT', 'yes', 'no', d)} = yes ]
        then
            install -m 644 ${S}/slog_modem_orca_userdebug.conf ${SLOGMODEM_INSTALL_DIR}/slogmodem/slog_modem_orca_userdebug.conf
        else
            install -m 644 ${S}/slog_modem_userdebug.conf ${SLOGMODEM_INSTALL_DIR}/slogmodem/slog_modem_userdebug.conf
        fi
        install -m 644 ${S}/mlogservice_userdebug.conf ${SLOGMODEM_INSTALL_DIR}/slogmodem/mlogservice_userdebug.conf
   else
        install -d ${SLOGMODEM_INSTALL_DIR}/slogmodem
        if [ ${@bb.utils.contains('ORCA_5G_SET_CFLAGS', '-DORCA_5G_SUPPORT', 'yes', 'no', d)} = yes ]
        then
            install -m 644 ${S}/slog_modem_orca_user.conf ${SLOGMODEM_INSTALL_DIR}/slogmodem/slog_modem_orca_user.conf
        else
            install -m 644 ${S}/slog_modem_user.conf ${SLOGMODEM_INSTALL_DIR}/slogmodem/slog_modem_user.conf
        fi
        install -m 644 ${S}/mlogservice_user.conf ${SLOGMODEM_INSTALL_DIR}/slogmodem/mlogservice_user.conf
   fi
   fi
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/slogmodem ${D}${bindir}/
    install -m 0755 ${S}/mlogservice ${D}${bindir}/
    install -m 0755 ${S}/cplogctl ${D}${bindir}/

if [ ${@bb.utils.contains('USERDEBUG', 'userdebug', 'yes', 'no', d)} = yes ]
then
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/slogmodem-init ${D}/${sysconfdir}/init.d/slogmodem-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/mlogservice.service ${D}${systemd_unitdir}/system/mlogservice.service
    install -m 0644 ${WORKDIR}/slogmodem.service ${D}${systemd_unitdir}/system/slogmodem.service

    install -d ${D}${sysconfdir}
    if [ ${@bb.utils.contains('ORCA_5G_SET_CFLAGS', '-DORCA_5G_SUPPORT', 'yes', 'no', d)} = yes ]
    then
        install -m 644 ${S}/slog_modem_orca_userdebug.conf ${D}${sysconfdir}/slog_modem.conf
    else
        install -m 644 ${S}/slog_modem_userdebug.conf ${D}${sysconfdir}/slog_modem.conf
    fi
    install -m 644 ${S}/mlogservice_userdebug.conf ${D}${sysconfdir}/mlogservice.conf
else
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/slogmodem-init-user ${D}/${sysconfdir}/init.d/slogmodem-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/mlogservice_user.service ${D}${systemd_unitdir}/system/mlogservice.service
    install -m 0644 ${WORKDIR}/slogmodem_user.service ${D}${systemd_unitdir}/system/slogmodem.service

    install -d ${D}${sysconfdir}
    if [ ${@bb.utils.contains('ORCA_5G_SET_CFLAGS', '-DORCA_5G_SUPPORT', 'yes', 'no', d)} = yes ]
    then
        install -m 644 ${S}/slog_modem_orca_user.conf ${D}${sysconfdir}/slog_modem.conf
    else
        install -m 644 ${S}/slog_modem_user.conf ${D}${sysconfdir}/slog_modem.conf
    fi
    install -m 644 ${S}/mlogservice_user.conf ${D}${sysconfdir}/mlogservice.conf
fi
}

FILES_${PN}-slogmodem = " \
    ${systemd_unitdir}/system/slogmodem.service \
"
FILES_${PN}-mlogservice = " \
    ${systemd_unitdir}/system/mlogservice.service \
"
FILES_${PN} += "${sysconfdir}/slog_modem.conf"
FILES_${PN} += "${sysconfdir}/mlogservice.conf"
TARGET_CC_ARCH += "${LDFLAGS}"


INITSCRIPT_NAME = "slogmodem-init"
INITSCRIPT_PARAMS = "start 88 3 4 5 . stop 88 0  6 ."

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} += "mlogservice.service slogmodem.service"

NATIVE_SYSTEMD_SUPPORT = "1"

# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://unisoc-logrotate.conf \
    file://lastlog.logrotate \
    file://syslog.logrotate \
    file://logrotate.cron \
"

do_install_append() {
    install -d 644 ${D}${sysconfdir}
    install -d 644 ${D}${sysconfdir}/logrotate.d
    install -m 0644 ${WORKDIR}/unisoc-logrotate.conf   ${D}${sysconfdir}/logrotate.conf
    install -m 0644 ${WORKDIR}/lastlog.logrotate   ${D}${sysconfdir}/logrotate.d/lastlog.logrotate
    install -m 0644 ${WORKDIR}/syslog.logrotate   ${D}${sysconfdir}/logrotate.d/syslog.logrotate

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d 644 ${D}${sysconfdir}/cron.hourly
        install -p -m 0755 ${WORKDIR}/logrotate.cron ${D}${sysconfdir}/cron.hourly/logrotate
    fi
}

deltask rm_work


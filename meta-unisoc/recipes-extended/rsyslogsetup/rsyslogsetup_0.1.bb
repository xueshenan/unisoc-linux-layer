# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc Rsyslog Setup module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/COPYING;md5=ee161291b035a8061bb358e76967f0bf"
PV = "0.1"
PR = "r0"

RDEPENDS_${PN} += "bash rsyslog"

SRC_URI = " \
    file://setup-log-path \
    file://rsyslogsetup \
    file://rsyslogsetup-init \
    file://COPYING \
"

inherit allarch update-rc.d

do_install () {
    install -d ${D}${sysconfdir}/rsyslog.d/
    install -d ${D}${sysconfdir}/init.d/
    install -d ${D}${bindir}/

    install -m 0755 ${WORKDIR}/setup-log-path ${D}${bindir}/setup-log-path
    install -m 0755 ${WORKDIR}/rsyslogsetup ${D}${sysconfdir}/init.d/rsyslogsetup
    install -m 0755 ${WORKDIR}/rsyslogsetup-init ${D}${sysconfdir}/init.d/rsyslogsetup-init
}

INITSCRIPT_NAME = "rsyslogsetup-init"
INITSCRIPT_PARAMS = "start 04 S 2 ."

FILES_${PN} = " \
    ${sysconfdir}/rsyslog.d/ \
    ${sysconfdir}/logrotate.d/ \
    ${sysconfdir}/init.d/ \
    ${bindir}/ \
    ${sysconfdir}/logrotate.d/syslog.logrotate \
    ${bindir}/setup-log-path \
    ${sysconfdir}/init.d/rsyslogsetup \
    ${sysconfdir}/init.d/rsyslogsetup-init \
"



# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://unisoc-rsyslog.conf \
    file://unirlog.ini  \
    file://unirlog-service.ini  \
    file://unisoc-rsyslog-default.conf \
    file://unisoc-rsyslog-cbf.conf \
    file://0001-Bug-1869193-rsyslog-Add-imtest.so-in-rsyslog.patch \
    file://0002-Bug-1915390-unirlog-Add-imevents-plugin-for-events.patch \
    file://0003-Bug-1920033-rsyslog8.2202-Add-S_ISBLK-S_ISFIFO-suppr.patch \
    file://0004-Bug-1920033-rsyslog8.2202-Add-imscript.so-for-runnin.patch \
    file://0005-Bug-1984864-rsyslog-add-S_ISCHR-file.patch \
    file://0006-Bug-1989711-rsyslog-reduce-imklog-memory.patch \
    file://wcn-log.sh \
    file://wcn-dump.sh \
"

SRC_URI_append = " \
    file://0007-Bug-1994955-rsyslog-imscript-Add-independent-thread-.patch \
    file://0008-Bug-2081854-rsyslog-Modify-test-events-scripts-AC_AR.patch \
    file://0009-Bug-2084303-rsyslog-Add-imwcn-plugins-for-unisoc-wcn.patch \
    file://uboot-log.sh \
"

inherit update-rc.d

PACKAGECONFIG_append = "impstats script wcn"

PACKAGECONFIG[impstats] = "--enable-impstats,--disable-impstats,,"
PACKAGECONFIG[test] = "--enable-test,--disable-test,,"
PACKAGECONFIG[events] = "--enable-events,--disable-events,,"
PACKAGECONFIG[script] = "--enable-script,--disable-script,,"
PACKAGECONFIG[wcn] = "--enable-wcn,--disable-wcn,,"

do_install_append() {
    install -m 644 ${WORKDIR}/unisoc-rsyslog.conf ${D}${sysconfdir}/rsyslog.conf

    install -d 644 ${D}${sysconfdir}/rsyslog.d
    install -m 644 ${WORKDIR}/unisoc-rsyslog-default.conf ${D}/${sysconfdir}/rsyslog.d/unisoc-rsyslog-default.conf
    install -m 644 ${WORKDIR}/unisoc-rsyslog-cbf.conf ${D}/${sysconfdir}/rsyslog.d/unisoc-rsyslog-cbf.conf
    install -m 0755 ${WORKDIR}/unirlog.ini ${D}${sysconfdir}/rsyslog.d/unirlog.ini
    install -m 644 ${WORKDIR}/unirlog-service.ini ${D}${sysconfdir}/rsyslog.d/unirlog-service.ini

#script
    install -d ${D}${bindir}/rsyslog-script/
    install -m 0755 ${WORKDIR}/wcn-log.sh ${D}${bindir}/rsyslog-script/wcn-log.sh
    install -m 0755 ${WORKDIR}/wcn-dump.sh ${D}${bindir}/rsyslog-script/wcn-dump.sh
    install -m 0755 ${WORKDIR}/uboot-log.sh ${D}${bindir}/rsyslog-script/uboot-log.sh
}

INITSCRIPT_NAME = "syslog"
#INITSCRIPT_PARAMS = "start 16 2 3 4 5 ."
INITSCRIPT_PARAMS = "start 07 2 S ."

deltask rm_work


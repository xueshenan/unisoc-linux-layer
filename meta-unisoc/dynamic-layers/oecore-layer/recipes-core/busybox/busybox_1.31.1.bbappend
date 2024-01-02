# SPDX-FileCopyrightText: 2019-2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
           file://lsusb.cfg \
           file://iptool.cfg \
           file://pkill.cfg \
           file://lsof.cfg \
           file://ping6.cfg \
           "
SRC_URI_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'rsyslogsetup','file://syslog.cfg','', d)} "


SRC_URI += "file://busybox-syslog.default   \
            file://syslog-startup.conf \
            file://syslog.conf   \
            file://rcS   \
            file://0001-syslogd-savelog-to-userdata.patch \
            ${@bb.utils.contains('SYSLOG_FEATURE', 'log_compress','file://0002-syslogd-compress-log.patch','', d)} \
            file://0003-syslogd-wait-for-filesystem.patch \
            file://syslogctl \
            ${@bb.utils.contains('MACHINE_FEATURES', 'busybox_top','file://top.cfg','', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'busybox_taskset','file://taskset.cfg','', d)}\
            ${@bb.utils.contains('MACHINE_FEATURES', 'ubi_remount','file://mount_after_format.patch','', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'ubi_remount','file://mount_after_cp.patch','', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'native_gzip','file://disable-gzip.cfg','', d)} \
           "
SYSLOG_NO_TCARD = "${@bb.utils.contains('SYSLOG_FEATURE', 'no_Tcard','yes','no', d)}"
SYSLOG_CIRT_LOG = "${@bb.utils.contains('SYSLOG_FEATURE', 'cirt_log','yes','no', d)}"
LOGTYPE = "${@bb.utils.contains('MACHINE_FEATURES', 'rsyslogsetup','rsyslog','syslog', d)}"
do_install_append() {
    if [ ${MACHINE} = "sl8563-cpe" ] || [ ${MACHINE} = "sl8563-cpe-1c" ] || [ ${MACHINE} = "sl8563-cpe-2h10" ] || [ ${MACHINE} = "sl8563-cpe-2h10-vsim" ] ;then
        sed -i "/usbdev.*/iusb* 0:0 0660 */usr/bin/usbenum.sh usbch" ${D}${sysconfdir}/mdev.conf
    fi

    if [ ${LOGTYPE} = "syslog" ];then
        if [ ${USERDEBUG} = "user" ];then
            sed -i 's/\*.\*/\*.warn/' ${D}${sysconfdir}/syslog.conf
        fi

        if [ ${SYSLOG_NO_TCARD} = "yes" ];then
            sed -i 's/media/data/' ${D}${sysconfdir}/syslog.conf
        fi

        if [ ${SYSLOG_CIRT_LOG} = "yes" ];then
            sed -i '/yocto.log/a\*.err /mnt/data/cirt.log' ${D}${sysconfdir}/syslog.conf
        fi
    fi

    install -d ${D}${bindir}/
    install -m 0755 ${WORKDIR}/syslogctl ${D}${bindir}/

    if grep "CONFIG_MDEV=y" ${B}/.config; then
        if grep "CONFIG_FEATURE_MDEV_CONF=y" ${B}/.config; then
            if [ ${STORAGE_TYPE} = "emmc" ];then
                sed -i s#MDEV_AUTOMOUNT=y#MDEV_AUTOMOUNT=n#g ${D}${sysconfdir}/mdev/mdev-mount.sh
            fi
        fi
    fi

    if [ ${USERDEBUG} = "user" ]
    then
        if [ -e ${D}${base_bindir}/busybox.suid ]
        then
            chmod 4754 ${D}${base_bindir}/busybox.suid
        fi
        if [ -e ${D}${base_bindir}/busybox.nosuid ]
        then
            chmod 0754 ${D}${base_bindir}/busybox.nosuid
        fi
    fi

}
# INITSCRIPT_PACKAGES_remove = "${@bb.utils.contains('USERDEBUG', 'userdebug', '', 'busybox-syslog',d)}"
# SYSTEMD_PACKAGES_remove = "${@bb.utils.contains('USERDEBUG', 'userdebug', '', 'busybox-syslog',d)}"

INITSCRIPT_PARAMS_${PN}-syslog = "start 04 S ."

ALTERNATIVE_PRIORITY[hwclock]="100"

# SPDX-FileCopyrightText: 2022 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://crontab-unisoc \
"
do_install_append() {
    install -m 644 ${WORKDIR}/crontab-unisoc ${D}${sysconfdir}/crontab
}

deltask rm_work


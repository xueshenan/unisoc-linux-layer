# SPDX-FileCopyrightText: 2019-2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI += "file://rcS-default \
"
do_install_append() {
    if [ ${MACHINE} = "sl8563-cpe" ] || [ ${MACHINE} = "sl8563-cpe-1c" ] || [ ${MACHINE} = "sl8563-cpe-2h10" ] || [ ${MACHINE} = "sl8563-cpe-2h10-vsim" ] ;then
    	sed -i "/::sysinit:\/bin\/mount\ \-a/i::sysinit:/bin/sh /etc/init.d/mount_ubifs.sh" ${D}${sysconfdir}/inittab
    fi
    sed -i "/\/bin\/mount/d" ${D}${sysconfdir}/inittab
    install -d ${D}${sysconfdir}/default
    install -m 0644    ${WORKDIR}/rcS-default       ${D}${sysconfdir}/default/rcS
}

FILES_${PN} += "${sysconfdir}/default \
               ${sysconfdir}/default/rcS \
"


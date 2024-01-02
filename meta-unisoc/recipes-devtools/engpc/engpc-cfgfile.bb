# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0
DESCRIPTION = "Unisoc Engpc Userdebug/Eng Configure Files"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

SRC_URI = "file://config/ \
           file://*_mach.conf \
"

# Modify these as desired
PV = "1.0"

INHIBIT_DEFAULT_DEPS = "1"

S = "${WORKDIR}"

# Define default chnl message
ENGPC_CHNL ?= "default"

do_install () {
	# Specify install commands here

	install -d ${D}${sysconfdir}/engpc/
	install -m 0644 ${WORKDIR}/config/chnl/${ENGPC_CHNL}/engpc.ini ${D}${sysconfdir}/engpc/engpc.ini

	install -d ${D}${sysconfdir}/engpc/chnl
	if [ -e "${WORKDIR}/autotest_mach.conf" ]; then
		sed -n '15,$p' ${WORKDIR}/autotest_mach.conf >> ${WORKDIR}/config/chnl/${ENGPC_CHNL}/autotest.conf
	fi
	install -m 0644 ${WORKDIR}/config/chnl/${ENGPC_CHNL}/autotest.conf ${D}${sysconfdir}/engpc/chnl/autotest.conf

	if [ -e "${WORKDIR}/cali_mach.conf" ]; then
		sed -n '15,$p' ${WORKDIR}/cali_mach.conf >> ${WORKDIR}/config/chnl/${ENGPC_CHNL}/cali.conf
	fi
	install -m 0644 ${WORKDIR}/config/chnl/${ENGPC_CHNL}/cali.conf ${D}${sysconfdir}/engpc/chnl/cali.conf

	if [ -e "${WORKDIR}/normal_lite_mach.conf" ]; then
		sed -n '15,$p' ${WORKDIR}/normal_lite_mach.conf >> ${WORKDIR}/config/chnl/${ENGPC_CHNL}/normal_lite.conf
	fi
	install -m 0644 ${WORKDIR}/config/chnl/${ENGPC_CHNL}/normal_lite.conf ${D}${sysconfdir}/engpc/chnl/normal_lite.conf

	install -d ${D}${sysconfdir}/engpc/dev/
	install -m 0644 ${WORKDIR}/config/dev/default/*.conf ${D}${sysconfdir}/engpc/dev/
	if [ -d "${WORKDIR}/config/dev/${MACHINE}" ]; then
		install -m 0644 ${WORKDIR}/config/dev/${MACHINE}/*.conf ${D}${sysconfdir}/engpc/dev/
	fi
}

FILES_${PN} = " \
	${sysconfdir}/engpc/* \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
RCONFLICTS_engpc-cfgfile = "engpc-userfile"

deltask rm_work
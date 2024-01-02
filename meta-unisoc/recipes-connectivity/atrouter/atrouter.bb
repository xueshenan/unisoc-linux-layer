# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

DESCRIPTION = "Unisoc Atrouter Module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Unisoc-General-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c3d3a15cada12cfaf94e8a91052a981"
SECTION_${PN} = "base"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/atrouter;protocol=ssh;branch=unc_glp_atrouter"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/telephony/atrouter"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/connectivity/telephony/atrouter"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

DEPENDS = "libatcommon libcptransport iniparser dbus \
           libofono chm-pty libnetcon \
"

RDEPENDS_${PN} = "libatcommon libcptransport iniparser dbus \
                  libofono chm-pty libnetcon \
                  ${PN}-cfgfile \
                  ${PN}-initrc \
"

ATROUTER_PPPSET ?= "${@bb.utils.contains('ATROUTER_PPP_CLOSED','yes','-DATROUTER_CLOSED_PPP_FUNCTION','-DPPP_USE_DBUS',d)}"
export ATROUTER_CONFIG_PPP_FLAG="${ATROUTER_PPPSET}"

do_compile () {
	# Specify compilation commands here
	make clean
	make WORKDIR=${WORKDIR}
}

do_install () {
	# Specify install commands here
	install -d ${D}${bindir}/
	install -m 0755 ${S}/atrouter ${D}${bindir}/
}

FILES_${PN} = " \
    ${bindir}/atrouter \
"

TARGET_CC_ARCH += "${LDFLAGS}"

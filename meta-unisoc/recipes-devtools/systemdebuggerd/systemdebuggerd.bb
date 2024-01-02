DESCRIPTION = "debug tools - systemdebugger"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "bin"
PV = "0.1"
PR = "r0"
inherit update-rc.d
LIC_FILES_CHKSUM = "file://COPYING;md5=36dba8813a86f10cc3ecfbcf89dc6eab"

export USER_FLAGS = "${@bb.utils.contains('USERDEBUG','user','-DCONFIG_USER','',d)}"

export SOC_FLAGS = "${@bb.utils.contains('UNISOC_SOC','s9863a','-DCONFIG_SOC_L3','',d)}"


SRC_URI = " \
            file://systemdebuggerdinit.sh \
	  "
INITSCRIPT_NAME = "systemdebuggerdinit"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 ."

do_compile () {
    oe_runmake  -C ${S}  O=${B}  clean
    oe_runmake  -C ${S}  O=${B}
}

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/systemdebuggerdinit.sh ${D}/${sysconfdir}/init.d/systemdebuggerdinit
	install -d ${D}${bindir}/
	install -m 0755 ${S}/systemdebuggerd ${D}${bindir}/systemdebuggerd
}

FILES_${PN} += "${bindir}/systemdebuggerd \
               "
TARGET_CC_ARCH += "${LDFLAGS}"


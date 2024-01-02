DESCRIPTION = "Unisoc modem control module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
require modem.inc
SECTION = "bins"
PROVIDES = "modem-control"

DEPENDS = "modem-utils expat"
RDEPENDS_${PN} = "modem-utils iniparser expat"

MODEMCONTROL_SECSET ?= "${@bb.utils.contains('SUPPORT_ORCA_LED','true','-DSYS_SUPPORT_LEDDAEMON_','',d)}"
export SUPPORT_LED_FLAG="${MODEMCONTROL_SECSET}"

INSANE_SKIP_${PN} = "file-rdeps"

export MODEM_SECURE_BOOT_FLAG="${MODEM_SECSET}"
export MODEM_KERNELCP_LDFALG="${MODEM_LDKERNELBOOTCP}"

export MODEM_CTRL_REMOVE_MODEM_FLAG="${MODEM_CTRL_REMOVE_MODEM}"
export MODEM_CTRL_SUPPORT_CH_FLAG="${MODEM_CTRL_SUPPORT_CH}"
export SUPPORT_LIBORCALED_FLAG="${SUPPORT_LIBORCALED}"
export HALO_SUPPORT_CFLAGS = "${HALO_SUPPORT_SET}"
export HALO_TYPE_CFLAGS = "${HALO_TYPE_SET}"
export MACHINE_TYPE_FLAG = "${MACHINE}"
export LOW_MEMORY_PLATFORM_FLAG = "${LOW_MEMORY_PLATFORM}"
export MODEM_CTRL_NR_MODEM_FLAG = "${MODEM_CTRL_NR_MODEM}"
inherit systemd update-rc.d
EXTERNALSRC = "${EXTERNALSRC_DIR}/modem_control"
EXTERNALSRC_BUILD =  "${EXTERNALSRC_DIR}/modem_control"

SRC_URI  = "\
  file://modem_control.service \
  file://modem_control-init \
"

do_compile_prepend (){
    if [ ${STORAGE_TYPE} = "nand" ]; then
        STORAGE_TYPE_NAND_FLAGS="-DCONFIG_NAND_UBI_VOL"
        export STORAGE_TYPE_NAND_FLAGS
    fi
}

do_install_prepend () {
   if [ ${MODEM_SRC_MODE} != "customer" ]; then
        rm -rf ${MODEM_INSTALL_DIR}/modem_control
	install -d ${MODEM_INSTALL_DIR}/modem_control
	install -m 0755 ${S}/modem_control ${MODEM_INSTALL_DIR}/modem_control/
        install -m 0755 ${S}/modem_ctrl_dbg ${MODEM_INSTALL_DIR}/modem_control/
   fi
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/modem_control ${D}${bindir}/
    install -m 0755 ${S}/modem_ctrl_dbg ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/modem_control-init ${D}${sysconfdir}/init.d/modem_control-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/modem_control.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/modem/
    install -m 0777 ${THISDIR}/files/${MACHINE}/modem_cp_info.xml ${D}${sysconfdir}/modem/modem_cp_info.xml
    install -m 0777 ${THISDIR}/files/${MACHINE}/modem_sp_info.xml ${D}${sysconfdir}/modem/modem_sp_info.xml
   if [ ${MODEM_CTRL_SUPPORT_CH} = "-DCONFIG_CH_SUPPORT" ]; then
    install -m 0777 ${THISDIR}/files/${MACHINE}/modem_ch_info.xml ${D}${sysconfdir}/modem/modem_ch_info.xml
   fi
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN}-modem_control = " \
    ${systemd_unitdir}/system/modem_control.service \
"
SYSTEMD_PACKAGES = "${PN}"

INITSCRIPT_NAME = "modem_control-init"
INITSCRIPT_PARAMS = "start 08 S ."

SYSTEMD_SERVICE_${PN} = "modem_control.service"
NATIVE_SYSTEMD_SUPPORT = "1"

DESCRIPTION = "Unisoc wcn module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = ""
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
PV = "0.1"
PR = "r0"
PROVIDES = "wcnini"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/wcn;protocol=ssh;branch=unc_linux_trunk_2.0"

do_install () {
    install -d ${D}${sysconfdir}
    install -d ${D}${nonarch_base_libdir}/firmware/
	install -d ${D}${sysconfdir}/wifi
	install -d ${D}${sysconfdir}/bt
	install -d ${D}${sysconfdir}/fm
	install -m 0644 ${OEROOT}/prebuilts/unisoc_bin/marlin3_20a_rls2/sc2355_marlin3_lite_ab_builddir/EXEC_KERNEL_IMAGE.bin ${D}${nonarch_base_libdir}/firmware/wcnmodem.bin
	install -m 0644 ${OEROOT}/prebuilts/unisoc_bin/gnss_20b_new/marlin3lite/marlin3lite_gnss_cm4_builddir/gnssmodem.bin ${D}${nonarch_base_libdir}/firmware/gnssmodem.bin
	install -m 0644 ${THISDIR}/regulatory/regulatory.db ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${THISDIR}/regulatory/regulatory.db.p7s ${D}${nonarch_base_libdir}/firmware/

	if [ -d ${THISDIR}/marlin3lite/${MACHINE} ] && [ -f ${THISDIR}/marlin3lite/${MACHINE}/bt_configure_rf.ini ]; then
		install -m 0644 ${THISDIR}/marlin3lite/${MACHINE}/wifi_board_config.ini ${D}${nonarch_base_libdir}/firmware/
		install -m 0644 ${THISDIR}/marlin3lite/${MACHINE}/wifi_board_config_aa.ini ${D}${nonarch_base_libdir}/firmware/
		install -m 0644 ${THISDIR}/marlin3lite/${MACHINE}/bt_configure_rf.ini ${D}${nonarch_base_libdir}/firmware/
		install -m 0644 ${THISDIR}/marlin3lite/${MACHINE}/bt_configure_pskey.ini ${D}${nonarch_base_libdir}/firmware/
		install -m 0644 ${THISDIR}/marlin3lite/${MACHINE}/bt_configure_rf_aa.ini ${D}${nonarch_base_libdir}/firmware/
		install -m 0644 ${THISDIR}/marlin3lite/${MACHINE}/bt_configure_pskey_aa.ini ${D}${nonarch_base_libdir}/firmware/
		install -m 0644 ${THISDIR}/marlin3lite/${MACHINE}/fm_board_config.ini ${D}${nonarch_base_libdir}/firmware/
	else
	    echo "wcnini not found MACHINE ${MACHINE}, use defaults"
		install -m 0666 ${THISDIR}/marlin3lite/defaults/wifi_board_config.ini ${D}${sysconfdir}/wifi/
		install -m 0666 ${THISDIR}/marlin3lite/defaults/wifi_board_config_aa.ini ${D}${sysconfdir}/wifi/
		install -m 0666 ${THISDIR}/marlin3lite/defaults/bt_configure_rf.ini ${D}${sysconfdir}/bt/
		install -m 0666 ${THISDIR}/marlin3lite/defaults/bt_configure_pskey.ini ${D}${sysconfdir}/bt/
		install -m 0666 ${THISDIR}/marlin3lite/defaults/bt_configure_rf_aa.ini ${D}${sysconfdir}/bt/
		install -m 0666 ${THISDIR}/marlin3lite/defaults/bt_configure_pskey_aa.ini ${D}${sysconfdir}/bt/
		install -m 0666 ${THISDIR}/marlin3lite/defaults/fm_board_config.ini ${D}${sysconfdir}/fm/
	fi
}
FILES_${PN} += "/lib/firmware/*"
FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

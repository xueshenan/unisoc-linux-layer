FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI+=" \
	file://mountadb.sh \
	file://mount_ubifs.sh \
	file://mount_emmc.sh \
	file://mount_emmc_sc9863a_smartcoreboard.sh \
	file://sysdump_switch_enable.sh \
	file://oom_skip.list \
	file://oom_skip_kill.sh \
	file://mount_sd_sl8541e_smartpen32.sh \
	file://mount_sd.sh \
	file://mount_usb.sh \
	file://bind_epf.sh \
	file://calibrator-mode.sh \
	file://rcS_command.sh \
	file://power-off-charge-mode.sh \
	file://thermal_enable.sh \
"

DUJ_STORAGE_TYPE ?= "${@bb.utils.contains('STORAGE_TYPE','nand','TURE','',d)}"
do_install_append(){
	if [ ${USERDEBUG} = "userdebug" ] ;then
		install -m 0755 ${WORKDIR}/mountadb.sh ${D}${sysconfdir}/init.d
		update-rc.d -r ${D} mountadb.sh start 80 S .
	fi
	if [ ${DUJ_STORAGE_TYPE} = "TURE" ] ;then
		install -m 0755 ${WORKDIR}/mount_ubifs.sh ${D}${sysconfdir}/init.d
	else
        if [ ${MACHINE} = "sc9863a-smartcoreboard" ]; then
		    install -m 0755 ${WORKDIR}/mount_emmc_sc9863a_smartcoreboard.sh ${D}${sysconfdir}/init.d/mount_emmc.sh
        elif [ ${MACHINE} = "sc9863a-1h10" ]; then
		    install -m 0755 ${WORKDIR}/mount_emmc_sc9863a_smartcoreboard.sh ${D}${sysconfdir}/init.d/mount_emmc.sh
	else
		    install -m 0755 ${WORKDIR}/mount_emmc.sh ${D}${sysconfdir}/init.d/mount_emmc.sh
        fi
	fi
	install -m 0755 ${WORKDIR}/sysdump_switch_enable.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/oom_skip_kill.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/oom_skip.list ${D}${sysconfdir}/init.d
	install -D -m 0755 ${WORKDIR}/calibrator-mode.sh ${D}${sysconfdir}/default/uni-mode/calibrator-mode.sh
	install -m 0755 ${WORKDIR}/rcS_command.sh ${D}${sysconfdir}/init.d
	install -D -m 0755 ${WORKDIR}/power-off-charge-mode.sh ${D}${sysconfdir}/default/uni-mode/power-off-charge-mode.sh
	install -m 0755 ${WORKDIR}/mount_sd_sl8541e_smartpen32.sh ${D}${sysconfdir}/init.d/mount_sd.sh
	install -m 0755 ${WORKDIR}/mount_usb.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/thermal_enable.sh ${D}${sysconfdir}/init.d
	if [ ${MACHINE} = "udx710-module-pi" ]; then
		install -m 0755 ${WORKDIR}/bind_epf.sh ${D}${sysconfdir}/init.d
	fi
	if [ ${DUJ_STORAGE_TYPE} = "TURE" ] ;then
		update-rc.d -r ${D} mount_ubifs.sh start 04 S .
	else
		update-rc.d -r ${D} mount_emmc.sh start 04 S .
	fi
        if [ ${VIRTUAL-RUNTIME_init_manager} = "busybox" ] ;then
            sed -i "/kill -USR1 1/d" ${D}${sysconfdir}/init.d/mountall.sh
        fi
	# todo：systemd
	if [ ${VIRTUAL-RUNTIME_init_manager} != "systemd" ] && [ -n "`echo ${IMAGE_FEATURES} | grep -w use-overlayfs`" ]; then
		mountVarCmd="mount -t overlay overlay -o lowerdir=/var,upperdir=/mnt/userdata/var,workdir=/mnt/userdata/workdirVar /var"
		mountHomeCmd="mount -t overlay overlay -o lowerdir=/home,upperdir=/mnt/userdata/home,workdir=/mnt/userdata/workdirHome /home"
		sed -i "/mount \-at/a ${mountVarCmd}" ${D}${sysconfdir}/init.d/mountall.sh
		sed -i "/mount \-at/a ${mountHomeCmd}" ${D}${sysconfdir}/init.d/mountall.sh
		# 如何判断执行 mountcmd 会出错，以及执行时的log信息？
	fi
	update-rc.d -r ${D} -f mountall.sh remove
	update-rc.d -r ${D} mountall.sh start 04 S .
	update-rc.d -r ${D} mount_sd.sh start 03 S .
	update-rc.d -r ${D} mount_usb.sh start 07 S .
	update-rc.d -r ${D} rcS_command.sh start 90 S .
	if [ ${MACHINE} = "udx710-module-pi" ]; then
		update-rc.d -r ${D} bind_epf.sh start 81 S .
	fi
	update-rc.d -r ${D} sysdump_switch_enable.sh start 90 S .
	update-rc.d -r ${D} oom_skip_kill.sh start 91 5 .
	update-rc.d -r ${D} thermal_enable.sh start 92 S .
        update-rc.d -r ${D} -f sendsigs remove
        rm ${D}${sysconfdir}/init.d/sendsigs
}
MASKED_SCRIPTS += " \
	mount_emmc.sh \
	mount_emmc_sc9863a_smartcoreboard.sh \
	mount_ubifs \
	mountadb \
	sysdump_switch_enable \
	oom_skip_kill \
	mount_sd_sl8541e_smartpen32.sh \
	mount_sd.sh \
	mount_epf.sh \
	mount_usb.sh \
	thermal_enable.sh \
"

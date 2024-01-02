FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://rcS_nand \
            file://rcS_emmc \
            file://rc \
           "


SRC_URI += "file://0003-Bug-1987074-sysvinit-Modify-the-src-bootlogd-to-adap.patch \
            file://0004-mount-dev-in-init-for-output-log.patch \
	    ${@bb.utils.contains('MACHINE_FEATURES', 'rebootinfo','file://0005-Bug-2096787-add-reboot-print-taskinfo.patch','', d)} \
           "

do_install_append() {
      if [ ${STORAGE_TYPE} = "emmc" ];then
          install -m 0755 ${WORKDIR}/rcS_emmc  ${D}${sysconfdir}/init.d/rcS
      else
          install -m 0755 ${WORKDIR}/rcS_nand  ${D}${sysconfdir}/init.d/rcS
      fi
}

deltask rm_work

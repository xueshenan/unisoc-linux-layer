FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
           file://hotplug-sd.rules \
           file://hotplug-sd.sh \
           file://hotplug-udisk.rules \
           file://hotplug-udisk.sh \
           "
do_install_append() {
     sed -i "69 d" ${D}${sysconfdir}/init.d/udev
     sed -i 'N;68 a udevadm settle --timeout=0' ${D}${sysconfdir}/init.d/udev

     install -d ${D}${sysconfdir}/udev/rules.d
     install -m 0666 ${WORKDIR}/hotplug-sd.rules ${D}${sysconfdir}/udev/rules.d/hotplug-sd.rules
     install -m 0666 ${WORKDIR}/hotplug-udisk.rules ${D}${sysconfdir}/udev/rules.d/hotplug-udisk.rules

     install -d ${D}${sysconfdir}
     install -m 0777 ${WORKDIR}/hotplug-sd.sh ${D}${sysconfdir}
     install -m 0777 ${WORKDIR}/hotplug-udisk.sh ${D}${sysconfdir}
}

INITSCRIPT_PARAMS = "start 07 S ."

FILES_${PN} += " \
               ${sysconfdir}/udev/rules.d/hotplug-sd.rules \
               ${sysconfdir}/hotplug-sd.sh \
               ${sysconfdir}/udev/rules.d/hotplug-udisk.rules \
               ${sysconfdir}/hotplug-udisk.sh \
               "

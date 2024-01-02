DESCRIPTION = "Unisoc usb manager"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/COPYING;md5=f7b40df666d41e6508d03e1c207d498f"
SECTION = "bin"
PV = "0.1"
PR = "r0"
PROVIDES = "usbcontrol"


SRC_URI = "\
"

inherit update-rc.d systemd

do_compile () {
}

do_install () {
  if [ -e ${THISDIR}/files/${MACHINE}/usbenum-user.sh ]; then
    install -d ${D}${bindir}/
    install -m 0711 ${THISDIR}/files/${MACHINE}/usbenum-user.sh ${D}${bindir}/usbenum.sh
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/${MACHINE}/usbenumfirst-user ${D}${sysconfdir}/init.d/usbenumfirst
    install -d ${D}${sysconfdir}/usbenum/
    install -m 0777 ${THISDIR}/files/${MACHINE}/usbenum.ini ${D}${sysconfdir}/usbenum/usbenum.ini
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${THISDIR}/files/${MACHINE}/usbenumfirst.service ${D}${systemd_unitdir}/system
  else
    install -d ${D}${bindir}/
    install -m 0711 ${THISDIR}/files/defaults/usbenum-user.sh ${D}${bindir}/usbenum.sh
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${THISDIR}/files/defaults/usbenumfirst-user ${D}${sysconfdir}/init.d/usbenumfirst
    install -d ${D}${sysconfdir}/usbenum/
    install -m 0777 ${THISDIR}/files/defaults/usbenum.ini ${D}${sysconfdir}/usbenum/usbenum.ini
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${THISDIR}/files/defaults/usbenumfirst.service ${D}${systemd_unitdir}/system
  fi
  if [ ${MACHINE} = "s9863a-1h10" ] || [ ${MACHINE} = "ums9620-1h10" ] || [ ${MACHINE} = "ums9620-2h10" ]; then
    if [ -e ${THISDIR}/files/hotplug-sd-ums.rules ]; then
         install -d ${D}${sysconfdir}/udev/rules.d
         install -m 0644 ${THISDIR}/files/hotplug-sd-ums.rules ${D}${sysconfdir}/udev/rules.d/hotplug-sd-ums.rules
         install -m 0644 ${THISDIR}/files/hotplug-usb.rules ${D}${sysconfdir}/udev/rules.d/hotplug-usb.rules
         install -d ${D}${sysconfdir}
         install -m 0755 ${THISDIR}/files/hotplug-sd-ums.sh ${D}${sysconfdir}
         install -m 0755 ${THISDIR}/files/hotplug-usb.sh ${D}${sysconfdir}
         install -m 0755 ${THISDIR}/files/hotplug-rmsd-ums.sh ${D}${sysconfdir}
         install -m 0755 ${THISDIR}/files/hotplug-rmusb.sh ${D}${sysconfdir}
         install -m 0755 ${THISDIR}/files/hotplug-usb-ums.sh ${D}${sysconfdir}
    fi
  fi
  if [ ${MACHINE} = "ums9620-1h10" ] || [ ${MACHINE} = "ums9620-2h10" ]; then
    if [ -e ${THISDIR}/files/hotplug-sd-ums.rules ]; then
         install -m 0644 ${THISDIR}/files/hotplug-usb-n6p.rules ${D}${sysconfdir}/udev/rules.d/hotplug-usb.rules
    fi
  fi
  install -m 0711 ${THISDIR}/files/rwini.sh ${D}${bindir}/rwini.sh
}
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "usbenumfirst"
INITSCRIPT_PARAMS_${PN} = "start 04 S ."

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "usbenumfirst.service"

FILES_${PN} += " \
	${bindir}/usbenum.sh \
	${sysconfdir}/init.d/usbenumfirst \
	${systemd_unitdir}/system/usbenumfirst.service \
"
TARGET_CC_ARCH += "${LDFLAGS}"

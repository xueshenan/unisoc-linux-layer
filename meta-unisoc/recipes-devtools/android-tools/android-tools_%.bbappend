FILESEXTRAPATHS_append := "${THISDIR}/files"
SRC_URI += " \
    file://core/adb-usb3.0-support.patch;patchdir=system/core\
    file://adbd-init.sh \
    file://adbd.service \
    "
${MACHINE}_USB_MAX_POWER ?="100"

do_install_append() {
    install -d "${D}${sysconfdir}/init.d"
    install -m 0755 ${WORKDIR}/adbd-init.sh ${D}/${sysconfdir}/init.d/adbd-init
    sed -i 's/MAXPOWER/${${MACHINE}_USB_MAX_POWER}/' ${D}/${sysconfdir}/init.d/adbd-init
    sed -i '/echo "Starting adbd..."/a \ \ \ \ setprop ro.sprd.debug 1' ${D}/${sysconfdir}/init.d/adbd-init
    sed -i 's/start-stop-daemon --start --exec \/usr\/bin\/adbd/start-stop-daemon --start --background --exec \/usr\/bin\/adbd/g' ${D}/${sysconfdir}/init.d/adbd-init

    install -d ${D}/${sbindir}
    install -m 0755 ${WORKDIR}/adbd-init.sh ${D}/${sbindir}/adbd-init.sh
    sed -i 's/MAXPOWER/${${MACHINE}_USB_MAX_POWER}/' ${D}/${sbindir}/adbd-init.sh

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/adbd.service ${D}${systemd_unitdir}/system

    install -d ${D}${bindir}/
}

PACKAGES =+ " ${PN}-adbd-init"

RDEPENDS_${BPN}-adbd_remove = "${BPN}-conf"
RDEPENDS_${BPN}-adbd_append = " ${PN}-adbd-init "
FILES_${PN}-adbd-init = " \
    ${sysconfdir}/init.d/adbd-init \
"

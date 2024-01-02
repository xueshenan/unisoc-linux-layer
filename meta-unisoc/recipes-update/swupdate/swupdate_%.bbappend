FILESEXTRAPATHS_append := "${THISDIR}/${PN}:${THISDIR}/${PN}/default:${THISDIR}/files"
DEPENDS += "otaarith gzip swupdate-support"
RDEPENDS_{PN} += "otaarith"
PACKAGECONFIG_CONFARGS = ""
SRC_URI += " \
    ${@bb.utils.contains('AB_PARTITIONS', 'true', 'file://misc-partition/double-copy/misc-config.bin', 'file://misc-partition/single/misc-config.bin', d)} \
    file://0001-Bug-1871263-add-ota-function-support.patch \
    file://board.cfg \
    file://update-init.sh \
    file://update.sh \
    file://swupdate-unisoc.service \
    "

#    file://0001-support-ubi-delta-update.patch \
#    ${@bb.utils.contains('GCCVERSION', '4.6.3', 'file://0002-swupdate-fixed-4.6.3-toolchain-build-issue.patch', '', d)} \
#    file://0003-support-rdiff-raw-progress.patch \
#

do_install_append() {
    rm ${D}${sysconfdir}/init.d/swupdate
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -m 644 ${WORKDIR}/swupdate-unisoc.service ${D}${systemd_unitdir}/system
        install -m 755 ${WORKDIR}/update-init.sh ${D}${bindir}/
        install -m 755 ${WORKDIR}/update.sh ${D}${bindir}/
    else
        install -m 755 ${WORKDIR}/update-init.sh ${D}${sysconfdir}/init.d
        install -m 755 ${WORKDIR}/update.sh ${D}${sysconfdir}/init.d
    fi
    install -m 644 ${WORKDIR}/board.cfg ${D}${sysconfdir}/
    install -d ${DEPLOY_DIR_IMAGE}/
    install -m 755 ${WORKDIR}/image/usr/bin/swupdate ${DEPLOY_DIR_IMAGE}/swupdateext
    install -m 755 ${WORKDIR}/update.sh ${DEPLOY_DIR_IMAGE}/update.sh
    if ${@bb.utils.contains('AB_PARTITIONS','true','true','false',d)}; then
        install -d ${DEPLOY_DIR_IMAGE}
        install ${WORKDIR}/misc-partition/double-copy/misc-config.bin ${DEPLOY_DIR_IMAGE}/misc-config.bin
    else
        install -d ${DEPLOY_DIR_IMAGE}
        install ${WORKDIR}/misc-partition/single/misc-config.bin ${DEPLOY_DIR_IMAGE}/misc-config.bin
    fi
}

SERVICEFILES ?= " \
    ${sysconfdir}/init.d/update-init.sh \
    ${sysconfdir}/init.d/update.sh \
    "

PACKAGES =+ "${PN}-base ${PN}-service"

RDEPENDS_${PN}-service += "${PN}-base"

FILES_${PN}-base = "${sysconfdir}/board.cfg"

FILES_${PN}-service = "${SERVICEFILES}"

SYSTEMD_PACKAGES += "${PN}-service"
SYSTEMD_SERVICE_${PN}-service = "swupdate-unisoc.service"

INITSCRIPT_PACKAGES += "${PN}-service"
INITSCRIPT_NAME_${PN}-service = "update-init.sh"
INITSCRIPT_PARAMS_${PN}-service = "start 99 S ."

FILES_${PN}-tools += " \
        ${bindir}/client \
        ${bindir}/progress \
        ${bindir}/hawkbitcfg \
        ${bindir}/sendtohawkbit \
        "

PREFERRED_VERSION_librsync = "2.0.2"

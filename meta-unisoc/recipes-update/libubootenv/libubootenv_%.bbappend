FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/${PN}/default:"

SRCREV = "6c623d18b43d605cb73399d51666f6cad933e6a4"

CFLAGS_prepend = "${@bb.utils.contains('GCCVERSION', '4.6.3', ' -std=c99', '', d)}"

SRC_URI += " \
    file://fw_env.config \
    file://u-boot-initial-env \
    "
RRECOMMENDS_${PN}-bin_remove_class-target = " u-boot-default-env"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -m 644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
    install -m 644 ${WORKDIR}/u-boot-initial-env ${D}${sysconfdir}/u-boot-initial-env
}

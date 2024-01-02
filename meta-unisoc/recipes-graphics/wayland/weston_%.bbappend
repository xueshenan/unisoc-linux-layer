FILESEXTRAPATHS_append := "${THISDIR}/${PN}:${THISDIR}/${PN}/default:${THISDIR}/files"

SRC_URI += " \
    file://weston.config \
    file://set_xdg_env.sh \
    file://weston_virt.config \
    file://plane-0_and_lightsleep.diff \
    file://res/qterminal-init.sh \
    file://res/qterminal.desktop \
    file://res/icon_terminal.png \
    "

RRECOMMENDS_${PN} = "liberation-fonts"

# todo need add otaab board
CONTROL_LIBWAYLAND = "${@bb.utils.contains('GPU_BOARD', 'ums9620-2h10', '', '-Dbackend-wayland=true ', d)}"
# Weston on Wayland (nested Weston)
PACKAGECONFIG[wayland] = "${CONTROL_LIBWAYLAND}-Dbackend-wayland=false,virtual/egl virtual/libgles2"

do_install_append() {
    if [ ${HYPUD710_CAR_VIRT} = "hypUd710-car-virt" ]; then
        install -m 0644 ${WORKDIR}/weston_virt.config ${D}${datadir}/weston.config
    else
        install -m 0644 ${WORKDIR}/weston.config ${D}${datadir}/
    fi

    install -d ${D}${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/res/qterminal-init.sh ${D}${sysconfdir}/init.d/qterminal-init

    install -dv -m -0655 ${D}${datadir}/applications/
    install -m 0666 ${WORKDIR}/res/qterminal.desktop ${D}${datadir}/applications/

    install -dv -m -0655 ${D}${datadir}/icons/
    install -m 0666 ${WORKDIR}/res/icon_terminal.png ${D}${datadir}/icons/

    install -d ${D}${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/set_xdg_env.sh ${D}${sysconfdir}/profile.d
}

FILES_${PN} += " \
    ${sysconfdir}/init.d \
    ${sysconfdir}/profile.d/set_xdg_env.sh \
    ${datadir}/weston.config \
    ${datadir}/applications \
    ${datadir}/icons \
    ${bindir}/* \
    "

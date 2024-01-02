FILESEXTRAPATHS_append := "${THISDIR}/${PN}:${THISDIR}/${PN}/default:${THISDIR}/wayland"

SRC_URI += " \
     ${@bb.utils.contains('GPU_BOARD', 'ums9620-2h10', 'file://not_install_libwayland.diff', '', d)} \
     ${@bb.utils.contains('GPU_BOARD', 'ums9620-2h10-otaab', 'file://not_install_libwayland.diff', '', d)} \
     "

FILESEXTRAPATHS_append := "${THISDIR}/${PN}:${THISDIR}/${PN}/default:${THISDIR}/files"

SRC_URI += " \
           file://libwayland-egl.diff \
           "
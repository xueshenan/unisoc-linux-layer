FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:"

SRC_URI_append += " \
           file://0001-porting-for-unisoc-omx-components.patch \
           "

GSTREAMER_1_0_OMX_TARGET = "zynqultrascaleplus"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libsprd_omx_core.so"

python __anonymous () {
        d.appendVar("CFLAGS", " -I${S}/omx/openmax")
}

copy_header_files() {
    install -d ${D}${includedir}/gstreamer1.0-omx/omx/openmax/
    install -m 0777 ${S}/omx/openmax/*.h ${D}${includedir}/gstreamer1.0-omx/omx/openmax/
}

do_install[postfuncs] += " copy_header_files "

#deltask do_rm_work

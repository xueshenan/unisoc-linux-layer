FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-plugins-ugly:"


PACKAGECONFIG_append ="amrnb amrwb"

SRC_URI_append += " \
           file://0001-bug-1619290-add-audio-header-in-amrnb-record.patch \
           "

RDEPENDS_gstreamer1.0-plugins-ugly += " \
    gstreamer1.0-plugins-ugly-amrnb \
    gstreamer1.0-plugins-ugly-amrwbdec \
"
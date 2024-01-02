FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-plugins-good:"

SRC_URI_append += " \
           file://0001-Bug-1706102-overflow-of-audio-length.patch \
           "

#deltask do_rm_work
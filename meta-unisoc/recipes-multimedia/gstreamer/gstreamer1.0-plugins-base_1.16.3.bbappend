FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-plugins-base:"

SRC_URI_append += " \
           file://0001-bug-1728575-delay-gst-read-keyboard-code.patch \
           file://0002-bug-1849323-add-bluetooth-control-next-or-previous-song.patch \
           file://0003-Bug-1964609-add-some-configurable-to-alsasrc.patch \
           "
#deltask do_rm_work

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://sshd_config \
           "

INITSCRIPT_PARAMS_${PN}-sshd = "${@bb.utils.contains('USERDEBUG','user','start 9 3 4 5 . stop 20 0 1 6 .','start 9 3 4 5 . stop 9 0 1 6 .',d)}"

require connman.inc

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/connman;protocol=ssh;branch=unc_linux_trunk"
EXTERNALSRC_BUILD = "${WORKDIR}/build"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI  = "\
  file://connman  \
"
SRC_URI_append_libc-musl = " file://0002-resolve-musl-does-not-implement-res_ninit.patch"

SRC_URI[md5sum] = "75012084f14fb63a84b116e66c6e94fb"
SRC_URI[sha256sum] = "6ce29b3eb0bb16a7387bc609c39455fd13064bdcde5a4d185fab3a0c71946e16"

RRECOMMENDS_${PN} = "connman-conf"
RCONFLICTS_${PN} = "networkmanager"
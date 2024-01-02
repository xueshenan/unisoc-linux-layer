FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0032-sysdeps_unix_sysv_linux_socket.patch \
	"

# deltask do_rm_work
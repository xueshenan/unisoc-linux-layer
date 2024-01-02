DESCRIPTION = "libwifieut.so"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5abd27680acec55893a065d21d3ffaff"
PN = "libwifieut"
PV = "1.0"
PR = "r0"

do_compile() {
	oe_runmake -C ${S}
}

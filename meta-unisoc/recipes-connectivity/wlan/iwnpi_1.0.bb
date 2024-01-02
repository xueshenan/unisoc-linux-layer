DESCRIPTION = "iwnpi tools"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f3f99872d65078978ecb8cd71c70c0a5"
PN = "iwnpi"
PV = "1.0"
PR = "r0"

DEPENDS = "libnl"

inherit pkgconfig

do_compile() {
	oe_runmake -C ${S}
}

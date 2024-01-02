DESCRIPTION = "Unisoc wlan sc2355 drivers"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "kmodules"
DEPENDS = ""
PV = "1.0"
PR = "r0"
PROVIDES = "kernel-module-wlan-sc2355"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module

module_do_compile_prepend () {
	export SPRDWL_PLATFORM=sc2355
}


DESCRIPTION = "Unisoc r8125 drivers"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "kmodules"
DEPENDS = ""
PV = "1.0"
PR = "r0"
PROVIDES = "kernel-module-r8125"

KERNEL_MODULE_AUTOLOAD += "r8125"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module

module_do_compile_prepend () {
    export SPRDWL_PLATFORM=r8125
}


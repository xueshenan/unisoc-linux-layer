CRIPTION = "ARM mali natt gpu kernel mode driver"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "kmodules"
DEPENDS = ""
PV = "r32"
PR = "p0"

KERNEL_MODULE_AUTOLOAD += "mali_kbase"

EXTERNALSRC_BUILD = "${EXTERNALSRC}/mali"

inherit kernel-arch
inherit module

export KDIR="${KBUILD_OUTPUT}"
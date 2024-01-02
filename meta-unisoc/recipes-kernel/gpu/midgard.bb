CRIPTION = "ARM mali midgard gpu kernel mode driver"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "kmodules"
DEPENDS = ""
PV = "r28"
PR = "p0"

KERNEL_MODULE_AUTOLOAD += "mali_kbase"

EXTERNALSRC_BUILD = "${EXTERNALSRC}/drivers/gpu/arm/midgard"

inherit kernel-arch
inherit module

export KDIR="${KBUILD_OUTPUT}"
export SCONS_CONFIGS="CONFIG_MALI_MIDGARD=m CONFIG_MALI_DEBUG=y CONFIG_MALI_PLATFORM_NAME=sharkle CONFIG_MALI_DEVFREQ=y CONFIG_INIT_VOLTAGE=y CONFIG_ADJUST_VOLTAGE=y"

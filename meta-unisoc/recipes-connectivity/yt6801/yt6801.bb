DESCRIPTION = "yt6801 drivers"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "kmodules"
DEPENDS = ""
PV = "1.0"
PR = "r0"
PROVIDES = "kernel-module-yt6801"

KERNEL_MODULE_AUTOLOAD += "yt6801"

EXTERNALSRC = "${OEROOT}/source/unisoc/connectivity/wcn/yt6801"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module

export BSP_MODULES_OUT="${B}"
export CURDIR="${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"

DESCRIPTION = "Unisoc vdsp driver"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION =  "kmodules"
DEPENDS = ""
RDEPENDS_${PN} = ""
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

PV = "0.1"
PR = "r0"
PROVIDES = "kernel-module-sprd-vdsp"

inherit kernel-arch
inherit module

KERNEL_MODULE_AUTOLOAD += "sprd_vdsp"

MODULES_MODULE_SYMVERS_LOCATION = "sprd_vdsp"

BPN = "kernel-module-sprd-vdsp"

export BSP_MODULES_OUT="${B}"
export CURDIR="${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"

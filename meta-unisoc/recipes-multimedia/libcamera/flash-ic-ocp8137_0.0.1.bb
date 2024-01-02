DESCRIPTION = "Unisoc camera kernel driver module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION =  "kmodules"
DEPENDS = "kernel-module-sprd-flash-drv"
RDEPENDS_${PN} = "sprd-flash-drv"

PV = "0.1"
PR = "r0"
PROVIDES = "flash-ic-ocp8137 kernel-module-flash-ic-ocp8137"
PROVIDES_${PN} += "kernel-module-flash-ic-ocp8137"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module

KERNEL_MODULE_AUTOLOAD += "flash_ic_ocp8137"

export BSP_MODULES_OUT="${B}"
export CURDIR="${S} "
export BSP_KERNEL_PATH="${KERNEL_SRC}"
export DRIVER_DIR="${CAMERA_DRIVER_DIR}"
export FLASH_IC="${CAMERA_FLASH_IC_VERSION}"

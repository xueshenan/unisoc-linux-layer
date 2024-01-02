DESCRIPTION = "Unisoc camera kernel driver module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION =  "kmodules"
DEPENDS = ""
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

PV = "0.1"
PR = "r0"
PROVIDES = "sprd-flash-drv kernel-module-sprd-flash-drv"


inherit kernel-arch
inherit module

KERNEL_MODULE_AUTOLOAD += "sprd_flash_drv"

MODULES_MODULE_SYMVERS_LOCATION = "sprd_flash_drv"

BPN = "kernel-module-sprd-flash-drv"

export BSP_MODULES_OUT="${B}"
export CURDIR="${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"
export DRIVER_DIR="${CAMERA_DRIVER_DIR}"

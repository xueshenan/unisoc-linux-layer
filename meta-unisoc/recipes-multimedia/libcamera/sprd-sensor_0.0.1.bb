DESCRIPTION = "Unisoc camera kernel driver module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION =  "kmodules"
DEPENDS = "kernel-module-sprd-camsys-pw-domain"
RDEPENDS_${PN} = "sprd-camsys-pw-domain"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

PV = "0.1"
PR = "r0"
PROVIDES = "sprd-sensor kernel-module-sprd-sensor"


inherit kernel-arch
inherit module

KERNEL_MODULE_AUTOLOAD += "sprd_sensor"

MODULES_MODULE_SYMVERS_LOCATION = "sprd_sensor"

BPN = "kernel-module-sprd-sensor"

export BSP_MODULES_OUT="${B}"
export CURDIR="${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"
export DRIVER_DIR="${CAMERA_DRIVER_DIR}"
export BSP_BOARD_CAMERA_MODULE_ISP_VERSION="${CAMERA_BSP_BOARD_CAMERA_MODULE_ISP_VERSION}"
export BSP_BOARD_CAMERA_MODULE_CPP_VERSION="${CAMERA_BSP_BOARD_CAMERA_MODULE_CPP_VERSION}"
export BSP_BOARD_CAMERA_MODULE_CSI_VERSION="${CAMERA_BSP_BOARD_CAMERA_MODULE_CSI_VERSION}"
export BSP_BOARD_CAMERA_MODULE_ISP_ADAPT_VERSION="${CAMERA_BSP_BOARD_CAMERA_MODULE_ISP_ADAPT_VERSION}"

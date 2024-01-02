DESCRIPTION = "Unisoc camera post processing/kernel driver module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION =  "kmodules"
#DEPENDS = "kernel-module-sprd-camsys-pw-domain"
#RDEPENDS_${PN} = "sprd-camsys-pw-domain"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

PV = "0.1"
PR = "r0"
PROVIDES = "sprd-camsys-pw-domain kernel-module-sprd-camsys-pw-domain"


inherit kernel-arch
inherit module

KERNEL_MODULE_AUTOLOAD += "sprd_camsys_pw_domain"

MODULES_MODULE_SYMVERS_LOCATION = "sprd_camsys_pw_domain"

BPN = "kernel-module-sprd-camsys-pw-domain"

export BSP_MODULES_OUT="${B}"
export CURDIR="${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"
export DRIVER_DIR="${CAMERA_DRIVER_DIR}"
export BSP_BOARD_CAMERA_MODULE_CPP_VERSION="${CAMERA_BSP_BOARD_CAMERA_MODULE_CPP_VERSION}"

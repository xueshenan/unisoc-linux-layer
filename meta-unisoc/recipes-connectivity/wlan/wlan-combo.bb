SUMMARY = "Unisoc wlan combo module"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module
#inherit copy-kernel-module
RPROVIDES_${PN} += "kernel-module-sprd_wlan_combo"
KERNEL_MODULE_AUTOLOAD += "sprd_wlan_combo"

MODULES_MODULE_SYMVERS_LOCATION = "sprd_wlan_combo"

export BSP_MODULES_OUT="${B}"
export CURDIR="${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"

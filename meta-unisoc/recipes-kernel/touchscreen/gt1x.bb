SUMMARY = "Example of how to build an external Linux kernel module"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/touchscreen/gt1x"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module
#inherit copy-kernel-module
RPROVIDES_${PN} += "kernel-module-gt1x_tpd"
KERNEL_MODULE_AUTOLOAD += "gt1x_tpd"

export BSP_MODULES_OUT = "${B}"
export CURDIR = "${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"
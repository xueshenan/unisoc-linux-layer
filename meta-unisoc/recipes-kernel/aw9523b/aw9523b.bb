SUMMARY = "Example of how to build an external Linux kernel module"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"
PROVIDES = "kernel-module-aw9523b"

EXTERNALSRC = "${OEROOT}/source/unisoc/modules/aw9523b"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module
#inherit copy-kernel-module
#RPROVIDES_${PN} += "kernel-module-aw9523b"
KERNEL_MODULE_AUTOLOAD += "aw9523b"

export BSP_MODULES_OUT = "${B}"
export CURDIR = "${S}"
export BSP_KERNEL_PATH="${KERNEL_SRC}"
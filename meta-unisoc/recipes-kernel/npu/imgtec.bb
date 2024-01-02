CRIPTION = "Unisoc imgtec  drivers"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "kmodules"
DEPENDS = ""
PV = "0.1"
PR = "r0"
RPROVIDES_${PN} += "kernel-module-imgtec"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit kernel-arch
inherit module

KERNEL_MODULE_AUTOLOAD+="npu_img_vha"

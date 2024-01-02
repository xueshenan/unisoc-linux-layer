CRIPTION = "Unisoc imgtec gpu VZ kernel mode driver"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "kmodules"
DEPENDS = ""
PV = "1.11RTM2"
PR = "5516664"
#RPROVIDES_${PN} += "kernel-module-rogue"

KERNEL_MODULE_AUTOLOAD += "pvrsrvkm"

EXTERNALSRC_BUILD = "${EXTERNALSRC}/build/linux/roc1_linux"

inherit kernel-arch
inherit module

export BUILD = "debug"
export WINDOW_SYSTEM = "wayland"
export KERNELDIR = "${KBUILD_OUTPUT}"
export DISCIMAGE = "${D}"
export SPRD_BUILD="${USERDEBUG}"

export GPU_DRIVER_MODE = "${GPU_VZ_MODE}"

do_install() {
     install -d ${D}/lib/modules/4.14.98/extra/
     install -m 644 ${S}/binary_roc1_linux_${WINDOW_SYSTEM}_${BUILD}/target_aarch64/pvrsrvkm.ko ${D}/lib/modules/4.14.98/extra/
}


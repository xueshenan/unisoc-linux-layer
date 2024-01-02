DESCRIPTION = "Imgtec PowerVR Rogue GPU Kernel Mode Driver"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "MIT"
SECTION = "kmodules"
PV = "1.11RTM2"
PR = "5516664"

# make sure the pvrsrvkm.ko was auto-loaded when system power on
KERNEL_MODULE_AUTOLOAD += "pvrsrvkm"

# the location of ddk kernel source code
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# avoid "WARNING: Module.symvers not found in ..."
MODULES_MODULE_SYMVERS_LOCATION = "obj/target_aarch64/kbuild"

# the rogue ddk makefile doesn't support "make modules_install" command, use "make install" instead
MODULES_INSTALL_TARGET = "install"

inherit kernel-arch
inherit module

# Rogue DDK build parameters
export PVR_BUILD_DIR="sprd_linux"
export BUILD="debug"
export WINDOW_SYSTEM="wayland"
export KERNELDIR="${KBUILD_OUTPUT}"
export DISCIMAGE="${D}"
export OUT="obj"
export RGX_BVNC="${GPU_RGX_BVNC}"
export SPRD_PLATFORM="${GPU_PLATFORM}"
export SPRD_BUILD="${USERDEBUG}"


DESCRIPTION = "Linux kernel 5.4 for UNISOC platforms"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit pythonnative

export USERDEBUG

###
require linux-unisoc-common.inc
require linux-unisoc-bootimg.inc

SRC_URI = "file://serial.cfg \
           file://0001-Bug-00000-yocto-3.1-bringup-kernel5.4-fix-compile-error.patch \
           file://0001-Bug-1982891-change-androidboot-to-sprdboot-in-kernel.patch \
           file://bluetooth_operation.patch \
           file://kernel-config/ipc.cfg \
           file://kernel-config/pid.cfg \
           file://kernel-config/user-namespace.cfg \
		   file://kernel-config/mouse.cfg \
		   file://0001-update-dts.patch \
		   file://0001-add-lcm-iovdd.patch \
		   file://0001-charger.patch \
		   file://usb.patch \
		   file://0001-add-key-okey-value.patch \
		   file://0001-change-pcie-config.patch \
		   file://pcie_config.patch \
           ${KERNEL_MACHINE_PATCH} \
           ${@bb.utils.contains('USERDEBUG', 'userdebug', 'file://userdebug.cfg', 'file://user.cfg', d)} \
	  "

PV = "5.4"
SRCREV_FORMAT = "kernel"


KERNEL_IMAGETYPE ?= "Image"
KERNEL_VERSION_SANITY_SKIP="1"



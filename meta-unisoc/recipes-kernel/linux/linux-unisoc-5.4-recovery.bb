inherit pythonnative

DESCRIPTION = "Linux recovery kernel 5.4 for UNISOC platforms"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-unisoc-common.inc
require recovery.inc

SRC_URI = "file://serial-recovery.cfg \
           file://0001-Bug-00000-yocto-3.1-bringup-kernel5.4-fix-compile-error.patch \
           ${KERNEL_PATCH_FOR_RECOVERY} \
		   file://0001-update-dts.patch \
		   file://0001-add-lcm-iovdd.patch \
		   file://0001-add-key-okey-value.patch \
	   ${@bb.utils.contains('USERDEBUG', 'userdebug', '', 'file://user.cfg', d)} \
	  "
PV = "5.4"
SRCREV_FORMAT = "kernel"

KERNEL_IMAGETYPE ?= "Image"
KERNEL_VERSION_SANITY_SKIP="1"

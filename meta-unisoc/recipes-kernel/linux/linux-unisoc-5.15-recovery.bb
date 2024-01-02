inherit pythonnative

DESCRIPTION = "Linux recovery kernel 5.15 for UNISOC platforms"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-unisoc-common.inc
require recovery.inc

SRC_URI = "file://serial-recovery.cfg \
           ${KERNEL_PATCH_FOR_RECOVERY} \
           file://solve-K515-build-and-boot-error.patch \
	   ${@bb.utils.contains('USERDEBUG', 'userdebug', '', 'file://user.cfg', d)} \
	  "
PV = "5.15"
SRCREV_FORMAT = "kernel"

KERNEL_IMAGETYPE ?= "Image"
KERNEL_VERSION_SANITY_SKIP="1"

# dtb compile
KERNEL_DTC_FLAGS += "-@"

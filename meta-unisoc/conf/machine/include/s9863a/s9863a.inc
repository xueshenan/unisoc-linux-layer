
require conf/machine/include/arm/arch-arm64.inc

UNISOC_SOC = "s9863a"
KERNEL_BOARD_BASE = "sp9863a"

UNISOC_KERNEL_VERSION = "linux-unisoc-5.4"
PREFERRED_PROVIDER_virtual/kernel = "${UNISOC_KERNEL_VERSION}"

KERNEL_IMAGETYPE = "Image"
UNISOC_BOOTIMG_PAGE_SIZE = "2048"
UNISOC_BOOTIMG_KERNEL_BASE = "0x00000000"
BOARD_MKBOOTIMG_ARGS = "--kernel_offset 0x00008000 --ramdisk_offset 0x012e9800"

SML_VERSION = "1.4"
TEE_64BIT = "true"

SENSOR_PROJECT="sharkl3_cm4"
EXTRA_IMAGEDEPENDS += "u-boot15"
EXTRA_IMAGEDEPENDS += "chipram"

# virtualization configuration
SKIP_META_VIRT_SANITY_CHECK = "1"

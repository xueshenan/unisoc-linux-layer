# the inc file store commone configuration for unisoc machine
# and would be overwriten by <machine>.conf
# assign should use "?=" and in <machine>.conf use "=" to overwriten

### console configuration
# console config need same as kernel cmdline
CONSOLE_LINUX = "ttyS1"
CONSOLE ?= "${CONSOLE_LINUX}"
SERIAL_CONSOLES ?= "115200;ttyS1"

### kernel dtb/defconfig/patch configuration
KERNEL_DEVICETREE ?= "${KERNEL_DT_SUBDIR}${KERNEL_BOARD_BASE}.dtb"
KERNEL_DEVICETREE_OVERLAY ?= "${KERNEL_DT_SUBDIR}${KERNEL_BOARD}-overlay.dtbo"
KERNEL_DEFCONFIG ?= "sprd_sl8581e_defconfig"
KCONFIG_MODE="--alldefconfig"
KERNEL_PATCH_FILEPATH = "${THISDIR}/${UNISOC_KERNEL_VERSION}/${UNISOC_SOC}"

### For Recovery, recovery now is prebuilts bin
KERNEL_RECOVERY_DEVICETREE ?= "${KERNEL_DT_SUBDIR}recovery-${KERNEL_BOARD}.dtb"
#KERNEL_RECOVERY_DEFCONFIG ?= "recovery_sprd_sharkl3_defconfig"
#UNISOC_KERNEL = "${UNISOC_KERNEL_VERSION}"

### chipram defconfig/patch
CHIPRAM_MACHINE ?= "${UBOOT_BOARD}_config"
CHIPRAM_PATCH_FILEPATH ?= "${THISDIR}/${UNISOC_SOC}"

# uboot dtb/defconfig/patch
UBOOT_MACHINE ?= "${UBOOT_BOARD}_defconfig"
UBOOT_TARGET_DTB ?= "${UBOOT_BOARD}"
U_BOOT_PATCH_FILEPATH = "${THISDIR}/u-boot15/${UNISOC_SOC}"

UBOOT_MERGE_KERNEL_CMDLINE_TO_BOOTARGS ?= "y"


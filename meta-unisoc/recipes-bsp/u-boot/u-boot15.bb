SUMMARY = "U-Boot bootloader for uboot15 UNISOC Development Platform"
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${U_BOOT_PATCH_FILEPATH}:"
SRC_URI = " \
${U_BOOT_MACHINE_PATCH} \
file://0001-update-pinmap-and-lcm-config.patch \
file://0001-add-lcm-dts.patch \
file://0001-Configure-PCIE-related-muxpins.patch \
file://0001-aw9623.patch \
"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native bc-native"
DEPENDS += "${@bb.utils.contains('STORAGE_TYPE', 'nand', 'mcp-gen-native', '', d)}"

PV = "v2015.07+git${SRCPV}"

UBOOT_DEBUG ?="${@bb.utils.contains('USERDEBUG', 'userdebug', '-DDEBUG', '', d)}"
export UBOOT_DEBUG_FLAG="${UBOOT_DEBUG}"

export UBOOT_SECURE_BOOT_FLAG="${UBOOT_SECSET}"

### START tos configs for uboot
UBOOT_TOS_TRUSTY = "${@bb.utils.contains('TEE_CONFIG','trusty','-DTOS_TRUSTY','',d)}"
export UBOOT_TOS_TRUSTY_FLAG="${UBOOT_TOS_TRUSTY}"
### END tos configs for uboot


S = "${WORKDIR}/git"


inherit sign_unisoc_binary
UNISOC_SIGN_ENABLE ?= "no"
DEPENDS += "unisoc-sign-native"


B = "${WORKDIR}/build"
do_configure[cleandirs] = "${B}"
EXTRA_OEMAKE += 'ARCH=arm'
UBOOT_BINARY  = "u-boot-dtb.bin"
UNISOC_SIGN_BINARY = "${B}/u-boot-dtb.bin"

UBOOT_MAKE_TARGET = "DEVICE_TREE=${UBOOT_TARGET_DTB} \
                     DTB=arch/arm/dts/${UBOOT_TARGET_DTB}.dtb \
"

addtask delete_uboot_patch   before do_patch after do_unpack
UBOOT_INITIAL_ENV = ""
do_delete_uboot_patch(){
    rm -rf ${S}patches
}



do_deploy_append () {
    install -d  ${DEPLOY_DIR_IMAGE}
    install ${B}/${UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/u-boot.bin
    sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/u-boot.bin"

    install ${B}/${UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/fdl2.bin
    sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/fdl2.bin"

   # install -D -m 644 ${B}/${UBOOT_BINARY} ${DEPLOYDIR}/fdl2-sign.bin
}


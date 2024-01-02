SUMMARY = "U-Boot bootloader for uboot22 UNISOC Development Platform"
PROVIDES = "virtual/bootloader_uboot22"
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${U_BOOT_PATCH_FILEPATH}:"
SRC_URI = " \
${U_BOOT_MACHINE_PATCH} \
"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native bc-native"
DEPENDS += "${@bb.utils.contains('STORAGE_TYPE', 'nand', 'mcp-gen-native', '', d)}"

PV = "v2022.01+git${SRCPV}"
UBOOT_INITIAL_ENV = ""
#S = "${WORKDIR}/git"
#B = "${WORKDIR}/build"

### for uboot export variables
UBOOT_TOS_TRUSTY = "${@bb.utils.contains('TEE_CONFIG','trusty','-DTOS_TRUSTY','',d)}"
export UBOOT_TOS_TRUSTY_FLAG="${UBOOT_TOS_TRUSTY}"

UBOOT_DEBUG ?="${@bb.utils.contains('USERDEBUG', 'userdebug', '-DDEBUG', '', d)}"
export UBOOT_DEBUG_FLAG="${UBOOT_DEBUG}"
export UBOOT_SECURE_BOOT_FLAG="${UBOOT_SECSET}"

unset CFLAGS

### sign image
inherit sign_unisoc_binary
UNISOC_SIGN_ENABLE ?= "no"
DEPENDS += "unisoc-sign-native"

### fix me: temp compile qemu board
UBOOT_BOARD = "qemu_arm64"
UBOOT_TARGET_DTB = "qemu-arm64"

EXTRA_OEMAKE += 'ARCH=arm'
UBOOT_BINARY  = "u-boot.bin"

addtask delete_uboot_patch   before do_patch after do_unpack
do_delete_uboot_patch(){
    rm -rf ${S}patches
}

do_compile () {
    oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE}
    oe_runmake -C ${S} O=${B} DEVICE_TREE="${UBOOT_TARGET_DTB}"
}

do_deploy () {
    if [ -f "${B}/u-boot.bin" ]; then
        install -d  ${DEPLOY_DIR_IMAGE}
        install ${B}/${UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/u-boot.bin
        sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/u-boot.bin"

        install ${B}/${UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/fdl2.bin
        sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/fdl2.bin"
    fi
}


SUMMARY = "U-Boot bootloader for UNISOC u-boot64 Development Platform"
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"

# u-boot needs devtree compiler to parse dts files
DEPENDS += "bc-native"

SRCREV = "bd5c55b90a77e6d215885e7cef12c033fcdec146"
PV = "v2014.01+git${SRCREV}"

FILESEXTRAPATHS_prepend := "${U_BOOT_PATCH_FILEPATH}:"
SRC_URI = "git://gitadmin@gitmirror.unisoc.com/android/u-boot64;protocol=ssh;branch=unc_debian_trunk \
${U_BOOT_MACHINE_PATCH} \
"

S = "${WORKDIR}/git"

#UBOOT_BINARY="u-boot-dtb.bin"
PACKAGE_ARCH = "${MACHINE_ARCH}"
UBOOT_MAKE_TARGET = "all"
UBOOT_BINARY="u-boot.bin"

do_compile () {
     make distclean -C ${S} O=${B}
     export LDFLAGS="-O1 --hash-style=gnu --as-needed"
     export CROSS_COMPILE=${TARGET_PREFIX}

     make ARCH=arm USE_PRIVATE_LIBGCC=yes -C ${S} O=${B} ${UBOOT_MACHINE}
     echo "#define CONFIG_WITH_SECURE_TOS 1" >> include/config.h
     echo "CONFIG_WITH_VMM = y" >> include/config.mk
     make ARCH=arm USE_PRIVATE_LIBGCC=yes -C ${S} O=${B}
}

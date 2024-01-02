SUMMARY = "U-Boot bootloader for uboot UNISOC Development Platform"
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native bc-native"
DEPENDS += "${@bb.utils.contains('STORAGE_TYPE', 'nand', 'mcp-gen-native', '', d)}"

PV = "v2010.12+git${SRCPV}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
UBOOT_MAKE_TARGET = "fdl2"
#UBOOT_BINARY="u-boot-dtb.bin"
UBOOT_BINARY="u-boot.bin"
STORAGE_TYPE ?= "none"

UBOOT_DEBUG ?="${@bb.utils.contains('USERDEBUG', 'userdebug', '-DDEBUG', '', d)}"
export UBOOT_DEBUG_FLAG="${UBOOT_DEBUG}"

do_compile () {
        if [ ${STORAGE_TYPE} = "nand" ]; then
            #add include file of nand paramters before compiling
            perl ${STAGING_BINDIR_NATIVE}/nandgen.pl \
                 -h ${EXTERNALSRC}/drivers/mtd/nand_sprd/sprd_nand_param.h \
                 -x ${STAGING_BINDIR_NATIVE}/mcp.xls
        fi

	export LDFLAGS="-O1 --hash-style=gnu --as-needed"
	export CROSS_COMPILE=${TARGET_PREFIX}

	make -C ${S} distclean
	oe_runmake ARCH=arm -C ${S} O=${B} ${UBOOT_MACHINE}
	oe_runmake ARCH=arm -C ${S} O=${B}
	oe_runmake ARCH=arm -C ${S} O=${B} ${UBOOT_MAKE_TARGET}
}

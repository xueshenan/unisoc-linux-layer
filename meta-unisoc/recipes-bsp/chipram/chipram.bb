SUMMARY = "Initial chipram"

LICENSE = "GPLv2"
PV = "0.1"
PR = "r0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

inherit sign_unisoc_binary
UNISOC_SIGN_ENABLE ?= "no"
DEPENDS += "unisoc-sign-native"

CHIPRAM_MACHINE ?=""
PACKAGE_ARCH = "${MACHINE_ARCH}"

CHIPRAM_PATCH_FILEPATH ?=""
FILESEXTRAPATHS_prepend := "${CHIPRAM_PATCH_FILEPATH}:"

CHIPRAM_MACHINE_PATCH ?=""
SRC_URI = " \
${CHIPRAM_MACHINE_PATCH} \
 "

STORAGE_TYPE ?= "none"
DEPENDS += "${@bb.utils.contains('STORAGE_TYPE', 'nand', 'mcp-gen-native', '', d)}"
export CHIPRAM_SECURE_BOOT_FLAG="${CHIPRAM_SECSET}"

do_compile () {
	if [ ${STORAGE_TYPE} = "nand" ]; then
		#add include file of nand paramters before compiling
		perl ${STAGING_BINDIR_NATIVE}/nandgen.pl \
			 -h ${EXTERNALSRC}/include/sprd_nand_param.h \
			 -x ${STAGING_BINDIR_NATIVE}/mcp.xls
	fi

	if [ -n "${CHIPRAM_MACHINE}" ] ;then
		if [ -z "${CHIPRAM_CROSS_COMPILE_DIR}" ] ;then
			export CROSS_COMPILE=${TARGET_PREFIX}
			if [ ${CHIPRAM_SUPPORT_BOTH_EMMC_UFS} = "yes" ]; then
				#########build spl-emmc.bin###########################
				export CONFIG_EMMC_SPL=true
				oe_runmake -C ${S} O=${B} distclean
				oe_runmake   -C ${S} O=${B} ${CHIPRAM_MACHINE}
				oe_runmake -C ${S} O=${B}

				install -d ${DEPLOY_DIR_IMAGE}/
				install -m 0644 ${B}nand_fdl/fdl1.bin  ${DEPLOY_DIR_IMAGE}/
				sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/fdl1.bin"

				install -m 0644  ${B}nand_spl/u-boot-spl-16k.bin  ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k.bin
				sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/u-boot-spl-16k.bin"
				cp ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k.bin ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k-emmc.bin
				cp ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k-sign.bin ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k-emmc-sign.bin

				#########build spl-ufs.bin###########################
				export CONFIG_UFS_SPL=true
				oe_runmake -C ${S} O=${B} distclean
				oe_runmake   -C ${S} O=${B} ${CHIPRAM_MACHINE}
				oe_runmake -C ${S} O=${B}

				install -d ${DEPLOY_DIR_IMAGE}/
				install -m 0644  ${B}nand_spl/u-boot-spl-16k.bin  ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k.bin
				sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/u-boot-spl-16k.bin"
				cp ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k.bin ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k-ufs.bin
                                cp ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k-sign.bin ${DEPLOY_DIR_IMAGE}/u-boot-spl-16k-ufs-sign.bin
			else
				oe_runmake -C ${S} O=${B} distclean
				oe_runmake   -C ${S} O=${B} ${CHIPRAM_MACHINE}
				oe_runmake -C ${S} O=${B}
			fi

		else
			mkdir -p ${CHIPRAM_CROSS_COMPILE_DIR}/unisoc-tool
			${CHIPRAM_CROSS_COMPILE_DIR}/${CHIPRAM_CROSS_COMPILE_NAME} -d ${CHIPRAM_CROSS_COMPILE_DIR}/unisoc-tool -y
			export CROSS_COMPILE="${CHIPRAM_CROSS_COMPILE_DIR}/unisoc-tool/sysroots/x86_64-unisocsdk-linux/usr/bin/aarch64-unisoc-linux/aarch64-unisoc-linux-"
			oe_runmake -C ${S} O=${B} distclean
			oe_runmake   -C ${S} O=${B} ${CHIPRAM_MACHINE}
			oe_runmake -C ${S} O=${B}
			rm -rf ${CHIPRAM_CROSS_COMPILE_DIR}/unisoc-tool
		fi
	fi

}

do_install(){
	if [ -n "${CHIPRAM_MACHINE}" ] ;then
		if [ ${CHIPRAM_SUPPORT_BOTH_EMMC_UFS} = "yes" ]; then
			echo "chipram build both emmc&ufs bin success"
		else
			install -d ${DEPLOY_DIR_IMAGE}/
			install -m 0644 ${B}nand_fdl/fdl1.bin  ${DEPLOY_DIR_IMAGE}/
			sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/fdl1.bin"

			install -m 0644  ${B}nand_spl/u-boot-spl-16k.bin  ${DEPLOY_DIR_IMAGE}/
			sign_unisoc_bin "${DEPLOY_DIR_IMAGE}/u-boot-spl-16k.bin"
		fi
	fi
}


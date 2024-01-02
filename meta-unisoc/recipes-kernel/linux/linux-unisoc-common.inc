inherit kernel
#inherit externalsrc
inherit kernel-yocto

# replace kernel.bbclass oe-user@oe-host to local username@hostname
export KBUILD_BUILD_USER=""
export KBUILD_BUILD_HOST=""

# Put a local version until we have a true SRCREV to point to

# default var
KERNEL_MACHINE_PATCH ?= ""
UNISOC_KERNEL_VERSION ?= ""
KERNEL_PATCH_FILEPATH ?= ""

SRCTREECOVEREDTASKS_remove += "do_kernel_configme do_patch"
LINUX_UNISOC_GIT ?= ""
FILESEXTRAPATHS_prepend := "${THISDIR}/${UNISOC_KERNEL_VERSION}/files:${KERNEL_PATCH_FILEPATH}:"

S = "${WORKDIR}/git"

DEPENDS += "openssl-native"
HOST_EXTRACFLAGS += "-I${STAGING_INCDIR_NATIVE}"
CFLAGS += "-Wno-error=misleading-indentation"

KBUILD_DEFCONFIG ?= "${KERNEL_DEFCONFIG}"
KMACHINE ?= "${MACHINE}"
KTYPE ?= "standard"
KBRANCH ?= "master"

STORAGE_TYPE ?= "none"
NAND_TYPE ?= "none"
DEPENDS += "${@bb.utils.contains('STORAGE_TYPE', 'nand', 'mcp-gen-native', '', d)}"

addtask delete_kernel_patch   before do_patch
addtask copy_kernel_defconfig before do_kernel_metadata after do_unpack
# addtask do_menuconfig before do_compile after do_configure

do_delete_kernel_patch() {
    rm -rf ${S}patches

}

do_copy_kernel_defconfig() {
    # cp defconfig to kernel source code tree
    cp ${KERNEL_PATCH_FILEPATH}/${KERNEL_DEFCONFIG} ${S}/arch/${ARCH}/configs/
    cp ${KERNEL_PATCH_FILEPATH}/${KERNEL_RECOVERY_DEFCONFIG} ${S}/arch/${ARCH}/configs/
}

do_kernel_configme_append(){
    if [ ${MACHINE} = "t710-smartcoreboard" ]; then
        sed -i s#versionMode#${USERDEBUG}#g ${B}/.config
    fi
}

do_compile_prepend(){
    if [ ${STORAGE_TYPE} = "nand" ]; then
        if [ ${NAND_TYPE} = "spinand" ]; then
            perl ${STAGING_BINDIR_NATIVE}/nandgen_kernel.pl \
                -h ${EXTERNALSRC}/drivers/mtd/nand/sprd_spi_nand/sprd_nand_param.h \
                -x ${STAGING_BINDIR_NATIVE}/mcp.xls
        else
            #add include file of nand paramters before compiling
            perl ${STAGING_BINDIR_NATIVE}/nandgen_kernel.pl \
                -h ${EXTERNALSRC}/drivers/mtd/nand/sprd_nand/sprd_nand_param.h \
                -x ${STAGING_BINDIR_NATIVE}/mcp.xls
        fi
    fi

    # cp dts to kernel source code tree

    # if kernel source tree have no KERNEL_BOARD_BASE.dts, then user unisoc layer
    #[ -e ${S}/arch/${ARCH}/boot/dts/${KERNEL_DT_SUBDIR}/${KERNEL_BOARD_BASE}.dts ] || cp ${KERNEL_PATCH_FILEPATH}/${KERNEL_BOARD_BASE}.dts ${S}/arch/${ARCH}/boot/dts/${KERNEL_DT_SUBDIR}/
    [ -e ${KERNEL_PATCH_FILEPATH}/${KERNEL_BOARD_BASE}.dts ] && cp ${KERNEL_PATCH_FILEPATH}/${KERNEL_BOARD_BASE}.dts ${S}/arch/${ARCH}/boot/dts/${KERNEL_DT_SUBDIR}/
    cp ${KERNEL_PATCH_FILEPATH}/${KERNEL_BOARD}-overlay.dts ${S}/arch/${ARCH}/boot/dts/${KERNEL_DT_SUBDIR}/
    cp ${KERNEL_PATCH_FILEPATH}/recovery-${KERNEL_BOARD}.dts ${S}/arch/${ARCH}/boot/dts/${KERNEL_DT_SUBDIR}/
}

python do_patch() {
    bb.build.exec_func('patch_do_patch', d)
}
do_patch[nostamp] = "1"

#do_configure_prepend() {
#
#        oe_runmake -C ${S} O=${B} ${KERNEL_DEFCONFIG}
#
#	yes '' | oe_runmake -C ${S} O=${B} oldconfig
#        oe_runmake -C ${S} O=${B} savedefconfig && cp ${B}/defconfig ${WORKDIR}/defconfig
#}

# append DTB, since bootloader doesn't support DTB
do_compile_append() {
    oe_runmake ARCH=${ARCH} -C ${S} O=${B} dtbs

    if [ -n "${DOMD_KERNEL_DEVICETREE}" ] ; then
        install -d ${DEPLOY_DIR_IMAGE}/sprd/
        install -m 0666 ${B}/arch/${ARCH}/boot/dts/${DOMD_KERNEL_DEVICETREE} ${DEPLOY_DIR_IMAGE}/${DOMD_KERNEL_DEVICETREE}
    fi
    if [ -n "${KERNEL_DEVICETREE_OVERLAY}" ] ;then
        find ${B}/arch/${ARCH} -name *.dtb* ! -name `basename ${KERNEL_DEVICETREE}` ! -name `basename ${KERNEL_DEVICETREE_OVERLAY}`| xargs -I{} rm {}
    else
        find ${B}/arch/${ARCH} -name *.dtb* ! -name `basename ${KERNEL_DEVICETREE}` | xargs -I{} rm {}
    fi
}

do_install_append() {
    for type in ${KERNEL_IMAGETYPES} ; do
        if [ -e ${D}/${KERNEL_IMAGEDEST}/${type}-${KERNEL_VERSION} ]; then
            rm ${D}/${KERNEL_IMAGEDEST}/${type}-${KERNEL_VERSION}
        fi
        if [ "${KERNEL_PACKAGE_NAME}" = "kernel" ]; then
            if [ -L ${D}/${KERNEL_IMAGEDEST}/${type} ]; then
                rm -rf ${D}/${KERNEL_IMAGEDEST}/${type}
            fi
        fi
    done
}

do_deploy_append() {
    # save kernel source path for idh
    install -d ${DEPLOY_DIR_IMAGE}
    echo "${S}" > ${DEPLOY_DIR_IMAGE}/kernel_path
}


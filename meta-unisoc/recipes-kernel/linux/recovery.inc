DESCRIPTION = "Recovery image"

SRCREV_FORMAT = "kernel"
KERNEL_IMAGETYPE ?= "Image"
KERNEL_PACKAGE_NAME := "recovery-kernel"
KERNEL_DEPLOYSUBDIR := "recovery"
UNISOC_SUBDIR_RECOVERY := "${KERNEL_DEPLOYSUBDIR}"
PROVIDES += "virtual/recovery-kernel"
KERNEL_IMAGE_BASE_NAME := "recovery"

KERNEL_PATCH_FOR_RECOVERY ?= "${KERNEL_MACHINE_PATCH}"
KERNEL_RECOVERY_DEVICETREE ?= ""
KERNEL_RECOVERY_DEFCONFIG ?= "${KERNEL_DEFCONFIG}"
KERNEL_DEVICETREE := "${KERNEL_RECOVERY_DEVICETREE}"
KBUILD_DEFCONFIG := "${KERNEL_RECOVERY_DEFCONFIG}"
UNISOC_RECOVERY_CMDLINE ?= "${CMDLINE}"

inherit sign_unisoc_binary
UNISOC_SIGN_ENABLE ?= "no"

inherit pythonnative
#inherit kernel
#do_compile[depends] += "swupdate-image:do_image_complete"
#DEPENDS += "dtbtool-native mkbootimg-native unisoc-sign-native"
DEPENDS += "unisoc-sign-native"


normalize_dtb () {
        dtb="$1"
        if echo $dtb | grep -q '/dts/'; then
                bbwarn "$dtb contains the full path to the the dts file, but only the dtb name should be used."
                dtb=`basename $dtb | sed 's,\.dts$,.dtb,g'`
        fi
        echo "$dtb"
}



do_deploy_append() {
    ##mv ${DEPLOYDIR}//*.dtb ${DEPLOYDIR}/${UNISOC_SUBDIR_RECOVERY}/


    for dtbf in ${KERNEL_DEVICETREE}; do
        dtb=`normalize_dtb "$dtbf"`
        dtb_ext=${dtb##*.}
        dtb_base_name=`basename $dtb .$dtb_ext`
        ln -sf $dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext $deployDir/recovery-${KERNEL_DTB_LINK_NAME}.$dtb_ext
        break
    done


    if false; then
        mkbootimg_dtarg=""
        export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:${STAGING_LIBDIR_NATIVE}"
        ${STAGING_BINDIR_NATIVE}/dtbTool -o ${DEPLOYDIR}/${UNISOC_SUBDIR_RECOVERY}/dt.img -s ${UNISOC_BOOTIMG_PAGE_SIZE} -p ${B}/scripts/dtc/ ${B}/arch/${ARCH}/boot/dts/`dirname ${KERNEL_DEVICETREE}`/
        mkbootimg_dtarg="--dt ${DEPLOYDIR}/${UNISOC_SUBDIR_RECOVERY}/dt.img"

        ${STAGING_BINDIR_NATIVE}/mkbootimg --kernel ${DEPLOYDIR}/${UNISOC_SUBDIR_RECOVERY}/${KERNEL_IMAGETYPE} \
              ${UNISOC_MKBOOTIMG_ARGS} \
              --ramdisk ${DEPLOY_DIR_IMAGE}/swupdate-image-${MACHINE}.cpio.gz \
              --output ${DEPLOYDIR}/recovery.img \
              $mkbootimg_dtarg \
              --pagesize ${UNISOC_BOOTIMG_PAGE_SIZE} \
              --base ${UNISOC_BOOTIMG_KERNEL_BASE} \
              --cmdline \
              "${UNISOC_RECOVERY_CMDLINE}"
        sign_unisoc_bin "${DEPLOYDIR}/recovery.img"
    fi
    if [ -e "${DEPLOYDIR}/${KERNEL_DEPLOYSUBDIR}/${KERNEL_IMAGETYPE}-${MACHINE}.bin" ] ; then
        sign_unisoc_bin "${DEPLOYDIR}/${KERNEL_DEPLOYSUBDIR}/${KERNEL_IMAGETYPE}-${MACHINE}.bin"
    fi
    if [ -e "${DEPLOYDIR}/${KERNEL_DEPLOYSUBDIR}/recovery-${MACHINE}.dtb" ] ; then
        sign_unisoc_bin "${DEPLOYDIR}/${KERNEL_DEPLOYSUBDIR}/recovery-${MACHINE}.dtb"
    fi
}

do_deploy[nostamp] = "1"

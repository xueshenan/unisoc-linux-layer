#DEPENDS += "dtbtool-native mkbootimg-native"

UNISOC_BOOTIMG_ROOTFS ?= "undefined"

KERNEL_COMPRESS_TYPE ?= "none"

# set output file names
BOOT_IMAGE_BASE_NAME = "boot"

DEPENDS += "lzop-native lz4-native bzip2-native gzip-native unisoc-sign-native"
inherit sign_unisoc_binary
UNISOC_SIGN_ENABLE ?= "no"

PROVIDES += "virtual/linux-kernel"


normalize_dtb () {
        dtb="$1"
        if echo $dtb | grep -q '/dts/'; then
                bbwarn "$dtb contains the full path to the the dts file, but only the dtb name should be used."
                dtb=`basename $dtb | sed 's,\.dts$,.dtb,g'`
        fi
        echo "$dtb"
}

do_deploy_append() {
    export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:${STAGING_LIBDIR_NATIVE}"

    # add symbol link for the first dtb
    for dtbf in ${KERNEL_DEVICETREE}; do
        dtb=`normalize_dtb "$dtbf"`
        dtb_ext=${dtb##*.}
        dtb_base_name=`basename $dtb .$dtb_ext`
        ln -sf $dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext $deployDir/Image-${KERNEL_DTB_LINK_NAME}.$dtb_ext
        break
    done

    # add symbol link for the first dtbo
    for dtbf in ${KERNEL_DEVICETREE_OVERLAY}; do
        dtb=`normalize_dtb "$dtbf"`
        dtb_ext=${dtb##*.}
        dtb_base_name=`basename $dtb .$dtb_ext`
        ln -sf $dtb_base_name.$dtb_ext $deployDir/Image-${KERNEL_DTB_LINK_NAME}-overlay.$dtb_ext
        break
    done

    # for kernel5.4 not use boot.img, use Image
    if false; then
            # mkbootimg requires an initrd file, make fake one that will be ignored
            # during boot
            echo "This is not an initrd" > ${B}/ramdisk.img
            gzip ${B}/ramdisk.img
            mv ${B}/ramdisk.img.gz ${DEPLOYDIR}/ramdisk.img

            if [ -z ${KERNEL_DEVICETREE} ] ; then
                 mkbootimg_dtarg=""
            else
                 DT_IMAGE_BASE_NAME="dt"
                 ${STAGING_BINDIR_NATIVE}/dtbTool -o ${DEPLOYDIR}/${DT_IMAGE_BASE_NAME}.img \
                                              -s ${UNISOC_BOOTIMG_PAGE_SIZE} \
                                                  -p ${B}/scripts/dtc/ \
                                 ${B}/arch/${ARCH}/boot/dts/`dirname ${KERNEL_DEVICETREE}`/
                mkbootimg_dtarg="--dt ${DEPLOYDIR}/${DT_IMAGE_BASE_NAME}.img"
                sign_unisoc_bin "${DEPLOYDIR}/${DT_IMAGE_BASE_NAME}.img"
            fi

            if [ ${KERNEL_IMAGETYPE} = "Image" ] ;then

                case "${KERNEL_COMPRESS_TYPE}" in
                    lzo)
                        install ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.bak
                        ${STAGING_BINDIR_NATIVE}/lzop -f ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}
                        install  ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.lzo ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}-${KERNEL_COMPRESS_TYPE}
                        ;;
                    lz4)
                        install ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.bak
                        ${STAGING_BINDIR_NATIVE}/lz4  -zf ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.lz4
                        install ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.lz4 ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}-${KERNEL_COMPRESS_TYPE}
                        ;;
                    bzip2)
                        install ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.bak
                        ${STAGING_BINDIR_NATIVE}/bzip2-native/bzip2  -zkf ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}
                        install ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.bz2 ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}-${KERNEL_COMPRESS_TYPE}
                        ;;
                    gzip)
                        install ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.bak
                        # ${STAGING_DIR_NATIVE}/bin/gzip -knf  ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}
                        ${STAGING_DIR_NATIVE}/${bindir}/gzip -knf  ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}
                        install ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}.gz ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}-${KERNEL_COMPRESS_TYPE}
                        ;;
                    *)
                        ;;
                esac
            fi

            if [ ${KERNEL_COMPRESS_TYPE} != "none" ] ;then
                ${STAGING_BINDIR_NATIVE}/mkbootimg --kernel ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}-${KERNEL_COMPRESS_TYPE} \
                                               ${UNISOC_MKBOOTIMG_ARGS} \
                                               --ramdisk ${DEPLOYDIR}/ramdisk.img \
                                               --output ${DEPLOYDIR}/${BOOT_IMAGE_BASE_NAME}.img \
                                               ${mkbootimg_dtarg} \
                                               --pagesize ${UNISOC_BOOTIMG_PAGE_SIZE} \
                                               --base ${UNISOC_BOOTIMG_KERNEL_BASE} \
                                               --cmdline "${CMDLINE}"
            else
                ${STAGING_BINDIR_NATIVE}/mkbootimg --kernel ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE} \
                                               ${UNISOC_MKBOOTIMG_ARGS} \
                                               --ramdisk ${DEPLOYDIR}/ramdisk.img \
                                               --output ${DEPLOYDIR}/${BOOT_IMAGE_BASE_NAME}.img \
                                               ${mkbootimg_dtarg} \
                                               --pagesize ${UNISOC_BOOTIMG_PAGE_SIZE} \
                                               --base ${UNISOC_BOOTIMG_KERNEL_BASE} \
                                               --cmdline "${CMDLINE}"
            fi

            sign_unisoc_bin "${DEPLOYDIR}/${BOOT_IMAGE_BASE_NAME}.img"
    fi 

    if [ -n "${KERNEL_DEVICETREE_OVERLAY}" ] ; then
        install -d ${DEPLOYDIR}
        install -m 0666 ${B}/arch/${ARCH}/boot/dts/${KERNEL_DEVICETREE_OVERLAY} ${DEPLOYDIR}
        # ${STAGING_BINDIR_NATIVE}/mkdtimg create ${DEPLOYDIR}/dtbo.img ${B}/arch/${ARCH}/boot/dts/${KERNEL_DEVICETREE_OVERLAY}
        # sign_unisoc_bin "${DEPLOYDIR}/dtbo.img"
        sign_unisoc_bin "${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${MACHINE}-overlay.dtbo"
    fi

    if [ -e "${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${MACHINE}.dtb" ] ; then
        sign_unisoc_bin "${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${MACHINE}.dtb"
    fi

    if [ -e "${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${MACHINE}.bin" ] ; then
        sign_unisoc_bin "${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${MACHINE}.bin"
    fi
}

SUMMARY = "xen dom0 image"
DESCRIPTION = "null partition"

LICENSE = "CLOSED"
inherit image

IMAGE_INSTALL = ""
PACKAGE_INSTALL = ""
IMAGE_FEATURES = ""
DISTRO_FEATURES = ""
MACHINE_FEATURES = ""

do_build[noexec] = "1"
do_build[depends] = ""
KERNELDEPMODDEPEND = ""

DomD_DIR ?= "/DomD"
XEN_DOM0_IMAGE_SIZE ?= "128000"
IMAGE_ROOTFS_SIZE ?= "${XEN_DOM0_IMAGE_SIZE}"

do_image(){

    rm -rf ${IMAGE_ROOTFS}/*
    tar -zxf ${DEPLOY_DIR_IMAGE}/unisoc-console-image-${MACHINE}.tar.gz -C ${IMAGE_ROOTFS}/

    install -d  ${IMAGE_ROOTFS}/${DomD_DIR}
    install -m 0666 ${DEPLOY_DIR_IMAGE}/Image.gz ${IMAGE_ROOTFS}/${DomD_DIR}/

    if [ -n "${DOMD_KERNEL_DEVICETREE}" ] ; then
        install -m 0666 ${DEPLOY_DIR_IMAGE}/${DOMD_KERNEL_DEVICETREE}   ${IMAGE_ROOTFS}/${DomD_DIR}/Image.gz.dtb
    fi

    install -m 0666 ${DEPLOY_DIR_IMAGE}/unisoc-console-image-${MACHINE}.ext4 ${IMAGE_ROOTFS}/${DomD_DIR}/rootfs.ext4
}


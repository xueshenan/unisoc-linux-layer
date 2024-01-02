SUMMARY = "xen domU image"
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

do_image(){

    rm -rf ${IMAGE_ROOTFS}/*
    install -m 0666 ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE} ${IMAGE_ROOTFS}/
    install -m 0666 ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}--*.dtb   ${IMAGE_ROOTFS}/${KERNEL_IMAGETYPE}.dtb
    if [ -e ${DEPLOY_DIR_IMAGE}/unisoc-console-image-${MACHINE}.${IMAGE_FSTYPES} ]; then
        install -m 0666 ${DEPLOY_DIR_IMAGE}/unisoc-console-image-${MACHINE}.${IMAGE_FSTYPES} ${IMAGE_ROOTFS}/rootfs.${IMAGE_FSTYPES}
    elif [ -e ${DEPLOY_DIR_IMAGE}/unisoc-wayland-image-${MACHINE}.${IMAGE_FSTYPES} ]; then
        install -m 0666 ${DEPLOY_DIR_IMAGE}/unisoc-wayland-image-${MACHINE}.${IMAGE_FSTYPES} ${IMAGE_ROOTFS}/rootfs.${IMAGE_FSTYPES}
    fi
}

DOMU_IMAGE_SIZE ?="128"
IMAGE_ROOTFS_SIZE = "${DOMU_IMAGE_SIZE}"

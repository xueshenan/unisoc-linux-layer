LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

DEPENDS += "dosfstools-native"

do_install() {
    install -d  ${DEPLOY_DIR_IMAGE}
    dd if=/dev/zero  of=${DEPLOY_DIR_IMAGE}/vfat.img   bs=1024 count=524288
    ${STAGING_DIR_NATIVE}/usr/sbin//mkfs.vfat ${DEPLOY_DIR_IMAGE}/vfat.img
}

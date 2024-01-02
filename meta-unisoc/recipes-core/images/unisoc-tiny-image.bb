require unisoc-image-common.inc

SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    packagegroup-unisoc-tiny \
    "
# ROOTFS_RO_UNNEEDED ??= "update-rc.d base-passwd shadow ${VIRTUAL-RUNTIME_update-alternatives} ${ROOTFS_BOOTSTRAP_INSTALL}" in image.bbclass
# FORCE_RO_REMOVE = '1' means remove ROOTFS_RO_UNNEEDED package from rootfs not only in read only system,but also in other rootfs
FORCE_RO_REMOVE = '1'
# shadow-base shadow-securetty util-linux-sulogin are the base of shadow, when remove shadow, those are no use.
ROOTFS_RO_UNNEEDED_append = " shadow-base shadow-securetty util-linux-sulogin"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image


# IMAGE_ROOTFS_SIZE ?= "8192"
# IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}

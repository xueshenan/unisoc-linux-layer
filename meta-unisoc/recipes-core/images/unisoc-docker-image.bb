require unisoc-console-image.bb

SUMMARY = "image with docker"

LICENSE = "MIT"

CORE_IMAGE_BASE_INSTALL += "  \
    packagegroup-unisoc-docker \
"
IMAGE_FEATURES += "ssh-server-openssh \
                  "

EXTRA_USERS_PARAMS_append = "\
    useradd -p '' osf; \
    usermod -a -G docker osf; \
"

IMAGE_ROOTFS_EXTRA_SPACE = "100"

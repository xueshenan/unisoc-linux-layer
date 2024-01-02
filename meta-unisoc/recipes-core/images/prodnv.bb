SUMMARY = "prodnv image"
DESCRIPTION = "null partition \
                "
LICENSE = "CLOSED"


IMAGE_INSTALL = ""
IMAGE_LINGUAS = ""
PACKAGE_INSTALL = ""

ROOTFS_POSTPROCESS_COMMAND_remove += " zap_empty_root_password rootfs_update_timestamp read_only_rootfs_hook; write_image_test_data empty_var_volatile;rootfs_reproducible; ; "
SORT_PASSWD_POSTPROCESS_COMMAND = ""
ROOTFS_POSTPROCESS_COMMAND_remove = " set_user_group;"

inherit image
do_build[noexec] = "1"
do_build[depends] = ""
KERNELDEPMODDEPEND = ""
LDCONFIGDEPEND = ""

do_image() {
    rm -rf ${IMAGE_ROOTFS}/*
}


# This variable is triggered to check if sysvinit must be overwritten by a single rcS
export SYSVINIT = "no"


PRODNV_MKUBIFS_ARGS ?= "${MKUBIFS_ARGS}"
PRODNV_FSTYPES ?= ""
PRODNV_SIZE ?= ""

IMAGE_ROOTFS_ALIGNMENT = "2048"

MKUBIFS_ARGS = "${PRODNV_MKUBIFS_ARGS}"

IMAGE_FSTYPES = "${PRODNV_FSTYPES}"
EXTRA_IMAGEDEPENDS = ""
IMAGE_POSTPROCESS_COMMAND = ""

PSEUDO_IGNORE_PATHS .= ",${WORKDIR}"

EXTRA_USERS_PARAMS_remove = "${EXTRA_USERS_PARAMS}"
EXTRA_USERS_PARAMS_remove += "${EXTRA_USERS_PARAMS_append}"

IMAGE_ROOTFS_SIZE = "${PRODNV_SIZE}"


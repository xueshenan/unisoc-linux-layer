SUMMARY = "userdata image"
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
include unisoc-image-features.inc

do_build[noexec] = "1"
do_build[depends] = ""
KERNELDEPMODDEPEND = ""
LDCONFIGDEPEND = ""

IMAGE_PREPROCESS_COMMAND = "redo_rootfs;chown_userdata_data;"

python chown_userdata_data() {
    import os
    image_rootfs_data=d.getVar('IMAGE_ROOTFS') + '/data'
    if os.path.exists(image_rootfs_data):
        os.chown(image_rootfs_data, 1000, 1000)
}

redo_rootfs(){
    if [ -n "`echo ${IMAGE_FEATURES} | grep -w use-overlayfs`" ]; then
        redo_rootfs_overlay
    else
        redo_rootfs_normal
    fi
}


redo_rootfs_normal(){
    rm -rf ${IMAGE_ROOTFS}/*
    userdata_size="${USERDATA_SIZE}"
    if [ "$userdata_size" != "" -a -f ${DEPLOY_DIR_IMAGE}/.var.tar.gz ]; then
        cd ${IMAGE_ROOTFS} && tar xzf ${DEPLOY_DIR_IMAGE}/.var.tar.gz
    fi
    if [ "$userdata_size" != "" -a -f ${DEPLOY_DIR_IMAGE}/.home.tar.gz ]; then
        cd ${IMAGE_ROOTFS} && tar xzf ${DEPLOY_DIR_IMAGE}/.home.tar.gz
	cd ${IMAGE_ROOTFS} && touch .check
        echo "no format" >.check
    fi

    tmp="${USERDATA_PARTITION}"
    user_dir=`echo ${tmp} | awk -F' ' '{print $2}' | awk '$1=$1'`

    extra_partitions="${EXTRA_PARTITIONS}"

        while [ "${extra_partitions}" != "" ]
        do
        partition=`echo ${extra_partitions} | awk -F' ' '{print $1}' | awk '$1=$1'`
        directory=`echo ${extra_partitions} | awk -F' ' '{print $2}' | awk '$1=$1'`
        fstype=`echo ${extra_partitions} | awk -F' ' '{print $3}' | awk '$1=$1'`

        tmp="${extra_partitions}"
        extra_partitions=`echo ${tmp} | sed -e "s|${partition}||"`
        tmp="${extra_partitions}"
        extra_partitions=`echo ${tmp} | sed -e "s|${directory}||"`
        tmp="${extra_partitions}"
        extra_partitions=`echo ${tmp} | sed -e "s|${fstype}||"`

        devdir=`echo ${partition} | awk -F'/' '{print $2}'`
        if [ "${devdir}" != "dev" ]
        then
            parent_dir=`echo ${partition} | grep ^${user_dir}`
            if [ "${parent_dir}" != "" ]
            then
                child_dir="${partition#${user_dir}}"
                install -d -m 755 ${IMAGE_ROOTFS}/${child_dir}
            fi
        fi
        done
}

redo_rootfs_overlay(){
    rm -rf ${IMAGE_ROOTFS}/*
    install -d -m 755 ${IMAGE_ROOTFS}/data

    install -d -m 755 ${IMAGE_ROOTFS}/var
    install -d -m 755 ${IMAGE_ROOTFS}/home

    install -d -m 755 ${IMAGE_ROOTFS}/workdirVar
    install -d -m 755 ${IMAGE_ROOTFS}/workdirHome
}

# This variable is triggered to check if sysvinit must be overwritten by a single rcS
export SYSVINIT = "no"

USERDATA_FSTYPES ?= "ext4"
USERDATA_SIZE ?= "20480"

IMAGE_FSTYPES = "${USERDATA_FSTYPES}"
#EXTRA_IMAGEDEPENDS = ""

INHERIT_remove += "extrausers"
EXTRA_USERS_PARAMS_remove = "${EXTRA_USERS_PARAMS}"
EXTRA_USERS_PARAMS_remove += "${EXTRA_USERS_PARAMS_append}"

IMAGE_LINGUAS = " "

USERDATA_MKUBIFS_ARGS ?= ""
MKUBIFS_ARGS = "${USERDATA_MKUBIFS_ARGS}"
IMAGE_ROOTFS_SIZE = "${USERDATA_SIZE}"

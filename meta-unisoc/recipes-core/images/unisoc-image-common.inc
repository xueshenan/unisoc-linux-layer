include unisoc-image-features.inc

# if IMAGE_FEATURE += "use-overlayfs"， then use enable_rootfs_overlay replace rootfs_mount_extra
ROOTFS_POSTPROCESS_COMMAND += '${@bb.utils.contains("IMAGE_FEATURES", "use-overlayfs", "local_use_overlayfs;", "rootfs_mount_extra;",d)}'

ROOTFS_POSTPROCESS_COMMAND += '${@bb.utils.contains("IMAGE_FEATURES", "autologin-root", "local_autologin_root; ", "",d)}'

# if  IMAGE_FEATURE += "prc-localtime" ， then use tzdata provide usr/share/zoneinfo/PRC as /etc/localtime
ROOTFS_POSTPROCESS_COMMAND += '${@bb.utils.contains("IMAGE_FEATURES", "prc-localtime", "local_prc_localtime; ", "",d)}'

# put image-feature correspond func into an include file
# use-overlayfs
include image-use-overlayfs.inc
# autologin-root
include image-autologin-root.inc
# prc-localtime
include image-prc-localtime.inc

rootfs_mount_extra () {
    extra_partitions="${EXTRA_PARTITIONS}"
    rm -f ${DEPLOY_DIR_IMAGE}/.var
    rm -f ${DEPLOY_DIR_IMAGE}/.home
    userdata_size="${USERDATA_SIZE}"
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

        install -d ${IMAGE_ROOTFS}/${directory}
	install -d ${IMAGE_ROOTFS}/mnt/media
	install -d ${IMAGE_ROOTFS}/mnt/udisk
        if [ "${fstype}" = "ubifs" ]
        then
            echo "${partition}    ${directory}    ${fstype}    noatime,nosuid,nodev,compr=lzo          wait,encryptable=footer" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
        elif [ "${fstype}" = "ext4" ] && [ "${partition}" = "/dev/mmcblk0p1" ]
        then
	       echo "${partition} ${directory} ${fstype} data=journal 0  0" >>${IMAGE_ROOTFS}${sysconfdir}/fstab
        else
            devdir=`echo ${partition} | awk -F'/' '{print $2}'`
            subdir=`echo ${partition} | awk -F'/' '{print $3}'`
            if [ "${devdir}" = "dev" ]
            then
                echo "${partition}    ${directory}    ${fstype}    defaults    0   0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
            elif [ "${devdir}" = "mnt" ] && [ "${subdir}" = "userdata" ]
            then
                bbwarn "this partion will mount userdata ,so don't mkdir dir here"
                echo "${partition}    ${directory}    none         rw,bind     0   0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
                if [ ! -d "${IMAGE_ROOTFS}${directory}" ]
                then
                   install -d ${IMAGE_ROOTFS}/${directory}
                fi
            else
                install -d ${IMAGE_ROOTFS}/${partition}
                install -d ${IMAGE_ROOTFS}/${directory}
                echo "${partition}    ${directory}    none         rw,bind     0   0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
            fi
            if [ "${partition}" = "/mnt/userdata/data" ]
            then
                install -d -m 755 ${IMAGE_ROOTFS}/${partition}
                touch ${DEPLOY_DIR_IMAGE}/.data
            fi
            if [ "${directory}" = "/var" ]
            then
                var_volatile_line=`sed -n '0,/var\/volatile/{//p}' ${IMAGE_ROOTFS}${sysconfdir}/fstab`
                sed -i '/var\/volatile/d' ${IMAGE_ROOTFS}${sysconfdir}/fstab
                echo "${var_volatile_line}" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
                touch ${DEPLOY_DIR_IMAGE}/.var
            fi
            if [ "${directory}" = "/home" ]
            then
                touch ${DEPLOY_DIR_IMAGE}/.home
            fi

        fi

    done
}

# todo：use image feature control，now system have su cmd and can use
include unisoc-delete-su.inc

IMAGE_PREPROCESS_COMMAND += " chown_mnt_media; chown_userdata_data; data_to_tarball; var_to_tarball; home_to_tarball; cp_tarball; "
IMAGE_PREPROCESS_COMMAND_remove = '${@bb.utils.contains("IMAGE_FEATURES", "use-overlayfs", "data_to_tarball; var_to_tarball; home_to_tarball; cp_tarball;", "",d)}'

python chown_userdata_data() {
    import os
    image_rootfs_data=d.getVar('IMAGE_ROOTFS') + '/mnt/userdata/data'
    if os.path.exists(image_rootfs_data):
        os.chown(image_rootfs_data, 1000, 1000)
}
data_to_tarball () {
    userdata_size="${USERDATA_SIZE}"
    rm -f ${DEPLOY_DIR_IMAGE}/.data.tar.gz
    if [ "${userdata_size}" != "" -a -f "${DEPLOY_DIR_IMAGE}/.data" ]
    then
        cd ${IMAGE_ROOTFS}/mnt/userdata && tar czf ${DEPLOY_DIR_IMAGE}/.data.tar.gz data
        rm -rf ${IMAGE_ROOTFS}/mnt/userdata/data
    fi
}

python chown_mnt_media() {
    import os
    image_rootfs_data=d.getVar('IMAGE_ROOTFS') + '/mnt/media'
    if os.path.exists(image_rootfs_data):
        os.chown(image_rootfs_data, 1000, 1000)
}
var_to_tarball () {
    echo mountvar $mountvar
    userdata_size="${USERDATA_SIZE}"

    rm -f ${DEPLOY_DIR_IMAGE}/.var.tar.gz

    if [ "${userdata_size}" != "" -a -f "${DEPLOY_DIR_IMAGE}/.var" ]
    then
        cd ${IMAGE_ROOTFS} && tar czf ${DEPLOY_DIR_IMAGE}/.var.tar.gz var
        rm -rf ${IMAGE_ROOTFS}/var/*
    fi
}

home_to_tarball () {
    echo mounthome $mounthome
    userdata_size="${USERDATA_SIZE}"

    rm -f ${DEPLOY_DIR_IMAGE}/.home.tar.gz

    if [ "${userdata_size}" != "" -a -f "${DEPLOY_DIR_IMAGE}/.home" ]
    then
        cd ${IMAGE_ROOTFS} && tar czf ${DEPLOY_DIR_IMAGE}/.home.tar.gz home
        rm -rf ${IMAGE_ROOTFS}/home/*
    fi
}

cp_tarball () {
    if [ -f ${DEPLOY_DIR_IMAGE}/.data.tar.gz ]
    then
    cp ${DEPLOY_DIR_IMAGE}/.data.tar.gz ${IMAGE_ROOTFS}/mnt/
    fi

    if [ -f ${DEPLOY_DIR_IMAGE}/.var.tar.gz ]
    then
    cp ${DEPLOY_DIR_IMAGE}/.var.tar.gz ${IMAGE_ROOTFS}/mnt/
    fi

    if [ -f ${DEPLOY_DIR_IMAGE}/.home.tar.gz ]
    then
    cp ${DEPLOY_DIR_IMAGE}/.home.tar.gz ${IMAGE_ROOTFS}/mnt/
    fi
}

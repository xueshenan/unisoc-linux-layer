OVERRIDES_append = ":${VIRTUAL-RUNTIME_init_manager}"
IMAGE_INSTALL = " \
    base-files \
    busybox \
    ${VIRTUAL-RUNTIME_dev_manager} \
    ${VIRTUAL-RUNTIME_init_manager} \
    libconfig \
    swupdate \
    ${@bb.utils.contains('SWUPDATE_INIT', 'tiny', 'virtual/initscripts-swupdate', 'initscripts', d)} \
    util-linux-sfdisk \
    mtd-utils \
    mtd-utils-ubifs \
    signature-key \
    gzip \
    u-boot-fw-utils \
    nvmerge \
"

#    lvgl \
#    lvdrv \
#    lvglota \
#    "

IMAGE_INSTALL_systemd = " \
    base-files \
    busybox \
    libconfig \
    swupdate \
    ${@bb.utils.contains('SWUPDATE_INIT', 'tiny', 'virtual/initscripts-swupdate', 'systemd', d)} \
    util-linux-sfdisk \
    mtd-utils \
    mtd-utils-ubifs \
    signature-key \
    gzip \
    u-boot-fw-utils \
    nvmerge \
    "

#


IMAGE_FSTYPES = "cpio.gz"
IMAGE_INSTALL += " \
    swupdate-service \
"

inherit extrausers
EXTRA_USERS_PARAMS = "\
    groupadd -g 1000 user; \
    useradd -p '' -u 1000 -g 1000 user; \
"
EXTRA_USERS_PARAMS_append = " \
    ${@bb.utils.contains('USERDEBUG','userdebug','usermod -p \'\' root;','usermod -p \'abu2Y6QjfZKeU\' root;',d)}\
"

# FORCE_RO_REMOVE = '1' means remove ROOTFS_RO_UNNEEDED package from rootfs not only in read only system,but also in other rootfs
FORCE_RO_REMOVE = '1'
# shadow-base shadow-securetty util-linux-sulogin are the base of shadow, when remove shadow, those are no use.
ROOTFS_RO_UNNEEDED_append = " shadow-base shadow-securetty util-linux-sulogin"

ROOTFS_POSTPROCESS_COMMAND += "rootfs_mount_extra ;"
rootfs_mount_extra () {
    extra_partitions="${USERDATA_PARTITION} ${SDCARD_PARTITION} ${DATA_DIR}"
    bbdebug  1 "extra_partitions: ${extra_partitions}"

    while [ "${extra_partitions}" != "" ] && [ "${extra_partitions}" != " " ]
    do
        partition=`echo ${extra_partitions} | awk -F' ' '{print $1}'`
        directory=`echo ${extra_partitions} | awk -F' ' '{print $2}'`
        fstype=`echo ${extra_partitions} | awk -F' ' '{print $3}'`

        tmp="${extra_partitions}"
        extra_partitions=`echo ${tmp} | sed -e "s|${partition}||"`
        tmp="${extra_partitions}"
        extra_partitions=`echo ${tmp} | sed -e "s|${directory}||"`
        tmp="${extra_partitions}"
        extra_partitions=`echo ${tmp} | sed -e "s|${fstype}||"`

        install -d ${IMAGE_ROOTFS}/${directory}

        if [ "${fstype}" = "ubifs" ]; then
            echo "${partition}    ${directory}    ${fstype}    noatime,nosuid,nodev,compr=lzo          wait,encryptable=footer" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
        else
            devdir=`echo ${partition} | awk -F'/' '{print $2}'`

            if [ "${devdir}" = "dev" ]
            then
                echo "${partition}    ${directory}    ${fstype}    defaults    0   0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
            else
                install -d ${IMAGE_ROOTFS}/${partition}
                echo "${partition}    ${directory}    none         rw,bind     0   0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
            fi
        fi
    done
}

EXTRA_IMAGEDEPENDS = ""

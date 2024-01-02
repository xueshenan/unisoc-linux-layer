
# func for IMAGE_FEATURES use-overlayfs
# when IMAGE_FEATURES += "use-overlayfs" in unisoc-image-features.inc: then you can use userdata overlay read-only rootfs /var and /home with rw

local_use_overlayfs () {
    # mount userdata-image to /mnt/userdata
    install -d -m 755 ${IMAGE_ROOTFS}/mnt/userdata
    install -d -m 755 ${IMAGE_ROOTFS}/mnt/data
    echo "/dev/userdata    /mnt/userdata    ext4    defaults    0   0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
    echo "/mnt/userdata/data /mnt/data none rw,bind 0 0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab

    # todo： overlay cmd to fstab instead of modify mountall.sh
}
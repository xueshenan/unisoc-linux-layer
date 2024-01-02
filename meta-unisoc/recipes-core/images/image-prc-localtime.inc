
# func for IMAGE_FEATURES prc-localtime
# when IMAGE_FEATURES += "prc-localtime" in unisoc-image-features.inc:  then you can use tzdata provide usr/share/zoneinfo/PRC as /etc/localtime

local_prc_localtime () {
    if [ -h "${IMAGE_ROOTFS}/etc/localtime" ]; then
        cp -f ${IMAGE_ROOTFS}/usr/share/zoneinfo/PRC ${IMAGE_ROOTFS}/home/localtime
        rm -rf ${IMAGE_ROOTFS}/etc/localtime
        echo "ln -sf /home/localtime ${IMAGE_ROOTFS}/etc/localtime"
        ln -sf /home/localtime ${IMAGE_ROOTFS}/etc/localtime
    fi
}




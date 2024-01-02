
# func for IMAGE_FEATURES autologin-root
# when IMAGE_FEATURES += "autologin-root" in unisoc-image-features.inc: then you can login auto, no need input user and password

local_autologin_root () {
    # note : busybox getty and util-linux getty have different
    # todo ： test systemd and busybox init
    echo  "begin local-autologin-root"
    getty="${IMAGE_ROOTFS}/sbin/getty"
    case $(readlink -f "${getty}") in
        */busybox*)
            sed -i -e s/'${setsid:-} ${getty} -L $1 $2 $3'/'${setsid:-} ${getty} -L $1 $2 $3 -n -l /bin/autologin'/g ${IMAGE_ROOTFS}/bin/start_getty
            cat > ${IMAGE_ROOTFS}/bin/autologin << END
            #!/bin/sh
            exec /bin/login -f root
            END
            chmod 0777 ${IMAGE_ROOTFS}/bin/autologin
            ;;
        *)
            sed -i -e s/'${setsid:-} ${getty} -L $1 $2 $3'/'${setsid:-} ${getty} --autologin root -L $1 $2 $3'/g ${IMAGE_ROOTFS}/bin/start_getty
            ;;
    esac
}

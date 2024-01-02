
ROOTFS_POSTPROCESS_COMMAND += "rm_su ;"

rm_su() {
    if [ "${MACHINE}" = "udx710-base" ] || [ "${MACHINE}" = "udx710-mifi" ] || [ "${MACHINE}" = "udx710-module-op" ] || [ "${MACHINE}" = "udx710-module-pi" ] || [ "${MACHINE}" = "udx710-ecell" ] || [ "${MACHINE}" = "udx710-module" ] || [ "${MACHINE}" = "udx710-module-op-pi" ] || [ "${MACHINE}" = "udx710-module-vddmodem-pi" ]
    then
        rm -rf ${IMAGE_ROOTFS}/bin/su
        rm -rf ${IMAGE_ROOTFS}/bin/su.shadow
    fi
}



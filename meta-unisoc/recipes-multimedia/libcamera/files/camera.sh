#!/bin/sh
if ! [ -e /dev/sprd_image ]; then
    insmod /lib/modules/4.14.174/extra/sprd_cpp.ko
    if ! [ -e /dev/sprd_sensor ]; then
        insmod /lib/modules/4.14.174/extra/sprd_sensor.ko
        insmod /lib/modules/4.14.174/extra/sprd_flash_drv.ko
        if [ -e /lib/modules/4.14.174/extra/flash_ic_ocp8137.ko ]; then
            if ! [ -e /sys/bus/i2c/drivers/sprd_ocp8137 ]; then
                insmod /lib/modules/4.14.174/extra/flash_ic_ocp8137.ko
            fi
        fi
        if [ -e /lib/modules/4.14.174/extra/flash_ic_sc2721s.ko ]; then
            if ! [ -e /sys/bus/platform/drivers/sc2721-flash ]; then
                insmod /lib/modules/4.14.174/extra/flash_ic_sc2721s.ko
            fi
        fi
    fi
    insmod /lib/modules/4.14.174/extra/sprd_camera.ko
fi

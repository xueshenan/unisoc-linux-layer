#! /bin/sh

usb_mode=$(cat /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration)

if [ "$usb_mode"x = "ums"x ];then
        /etc/hotplug-sd.sh
else
        /etc/init.d/mount_sd.sh
fi

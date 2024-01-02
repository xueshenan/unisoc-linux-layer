#! /bin/sh
curr=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property curr-usbch)
if [ "$curr"x == "mtp"x ];then
	/usr/bin/usbenum.sh usbch
	echo interactive > /sys/devices/system/cpu/cpufreq/policy0/scaling_governor
fi

usb_mode=$(cat /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration)
if [ "$usb_mode"x = "ums"x ];then
        /etc/init.d/mount_sd.sh
fi

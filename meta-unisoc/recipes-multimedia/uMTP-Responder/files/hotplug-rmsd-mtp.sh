#! /bin/sh
curr=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property curr-usbch)
if [ "$curr"x == "mtp"x ];then
        /usr/bin/usbenum.sh usbch
        echo interactive > /sys/devices/system/cpu/cpufreq/policy0/scaling_governor
fi

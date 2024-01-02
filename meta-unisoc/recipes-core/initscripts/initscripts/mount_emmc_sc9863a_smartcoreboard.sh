#!/bin/sh
#emmc dev name set
echo "rcS:mount_emmc start"
for folder in $(ls /sys/class/block/ | grep 'mmcblk0p');do
id=${folder#*mmcblk0p}
name=$(cat /sys/class/block/mmcblk0/$folder/uevent)
subname=${name##*=}
ln -s /dev/mmcblk0p$id /dev/mmcblk0p_$subname

done


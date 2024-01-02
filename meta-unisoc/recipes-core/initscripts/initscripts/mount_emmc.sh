#!/bin/sh
#emmc dev name set
echo "rcS:mount_emmc start"
for folder in $(ls /sys/class/block/ | grep 'mmcblk0p');do
    id=${folder#*mmcblk0p}
    name=$(cat /sys/class/block/mmcblk0/$folder/uevent)
    subname=${name##*=}
    ln -s /dev/mmcblk0p$id /dev/mmcblk0p_$subname
    ln -s /dev/mmcblk0p$id /dev/$subname
done

#ufs dev name set
for folder in $(ls /sys/class/block/sda/ | grep 'sda');do
    id=${folder#*sda}
    name=$(cat /sys/class/block/sda/$folder/uevent)
    subname=${name##*=}
    ln -s /dev/sda$id /dev/$subname
done


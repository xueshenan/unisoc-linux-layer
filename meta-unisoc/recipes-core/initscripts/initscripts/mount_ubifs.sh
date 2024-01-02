#!/bin/sh
#--------------------------------------------
# description：This script is used for soft
# connection ubi0_* device node under /dev
# note: this is workaround!
# author：junchao.ni
# site：https://www.unisoc.com/
# date：30-03-2021
#--------------------------------------------

#ubi dev name set
echo "rcS:mount_ubifs start"
for folder in $(ls /sys/class/ubi/ubi0 | grep 'ubi0_*');do
id=${folder#*ubi0_}
name=$(cat /sys/class/ubi/ubi0/$folder/name)
ln -s /dev/ubi0_$id /dev/ubi0_$name
ln -s /dev/ubi0_$id /dev/$name
done

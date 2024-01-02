#!/bin/sh
#
#mount adb usb
#
 echo "rcS:mountadb start"
 mount -t configfs none /sys/kernel/config
 mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
 mkdir -p /dev/usb-ffs/adb
 /bin/mount -t functionfs adb /dev/usb-ffs/adb


#! /bin/sh
curr=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property curr-usbch)
if [ "$curr"x == "ums"x ];then
	/usr/bin/usbenum.sh down
	echo 0 > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.1/removable
	echo  > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.1/file
	/usr/bin/usbenum.sh up
fi

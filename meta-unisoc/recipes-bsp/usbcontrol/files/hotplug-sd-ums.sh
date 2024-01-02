#! /bin/sh
device=""
if [ -c /dev/ubi0 ]
then
	if [ -b /dev/mmcblk0p1 ]
	then
		device=/dev/mmcblk0p1
	elif [ -b /dev/mmcblk1p1 ]
	then
		device=/dev/mmcblk1p1
	elif [ -b /dev/mmcblk0 ]
	then
		device=/dev/mmcblk0
	elif [ -b /dev/mmcblk1 ]
	then
		device=/dev/mmcblk1
	else
		echo "ums: sdcard is not exist"
	fi
elif [ -b /dev/mmcblk0p1 ]
then
	if [ -b /dev/mmcblk1p1 ]
	then
		device=/dev/mmcblk1p1
	elif [ -b /dev/mmcblk1 ]
	then
		device=/dev/mmcblk1
	else
		echo "ums: sdcard is not exist"
	fi
fi
if [ "$device"x != ""x ]
then
	$(/usr/bin/rwini.sh /mnt/data/usbenum.ini ums sdfile $device)
fi

curr=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property curr-usbch)
sdfile=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini ums sdfile)
if [ "$curr"x == "ums"x ];then
	/usr/bin/usbenum.sh down
	echo $sdfile > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.1/file
	echo 1 > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.1/removable
	/usr/bin/usbenum.sh up
fi

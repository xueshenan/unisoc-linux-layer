#!/bin/sh

mountpoint=/mnt/media
device=/dev/null
if [ -c /dev/ubi0 ]
then
	if [ -b /dev/mmcblk0p1 ]
	then
        	echo "/dev/mmcblk0p1 exist"
		device=/dev/mmcblk0p1
	elif [ -b /dev/mmcblk1p1 ]
	then
		echo "/dev/mmcblk1p1 exist"
                device=/dev/mmcblk1p1
	elif [ -b /dev/mmcblk0 ]
	then
		echo "/dev/mmcblk0 exist"
		device=/dev/mmcblk0
	elif [ -b /dev/mmcblk1 ]
        then
                echo "/dev/mmcblk1 exist"
		device=/dev/mmcblk1
        else
                echo "sdcard is not exist"
        fi

elif [ -b /dev/mmcblk0p1 ]
then
        if [ -b /dev/mmcblk1p1 ]
        then
                echo "/dev/mmcblk1p1 exist"
		device=/dev/mmcblk1p1
	elif [ -b /dev/mmcblk1 ]
        then
                echo "/dev/mmcblk1 exist"
		device=/dev/mmcblk1
        else
                echo "sdcard is not exist"
        fi

fi
if [ "device" != "/dev/null" ]
then
	mount $device $mountpoint
	if [ $? -eq 0 ]
	then
		echo "sdcard mount successful"

	else
		echo "sdcard mount failed, please run fsck"
	fi
fi
if [ -f /var/run/syslogd.pid ]
then
	/etc/init.d/syslog restart
	echo "syslog restart successful by mount_sd.sh"
fi
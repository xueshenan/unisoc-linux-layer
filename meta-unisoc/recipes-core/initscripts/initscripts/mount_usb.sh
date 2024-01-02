#!/bin/sh

if [ -e /dev/sda1 ]
then
	echo "/dev/sda1 exist"
	mount /dev/sda1 /mnt/udisk
	if [ $? -eq 0 ];then
		echo "usb mount successful"
	else
		echo "usb mount failed"
	fi
else
	echo "/dev/sda1 not exist"
fi

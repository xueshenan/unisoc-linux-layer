#!/bin/sh
first=$1
second=$2
if [ ! -d /mnt/udisk ];then
    mkdir /mnt/udisk
fi
if [ "$second"x == "1"x ];then
    echo "start mount udisk"
	if [ -b /dev/$1 ]; then
        mount /dev/$1 /mnt/udisk
        sync
	fi
else
	echo "start umount udisk"
    	sync
        for pid in $(lsof | grep "/mnt/udisk" | awk '{ print $1 }')
	do
		echo $pid
		kill -9  $pid
		if [ $? -eq 0 ]
		then
			echo "kill process success"
		else
			echo "kill process fail"
		fi
	done
	sleep 1
    	umount /mnt/udisk
fi

#!/bin/sh

umount  /mnt/media
if [ $? -eq 0 ]
then
        echo "umount sdcard success"
else
        echo "device is busy, try to kill process"
        for pid in $(lsof | grep "/mnt/media" | awk '{ print $1 }')
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
        umount /mnt/media
        if [ $? -eq 0 ]
        then
                echo "umount sdcard success after kill process"

	else
                echo "still umount failed"
        fi
	if [ -f /var/run/rsyslogd.pid ]
	then
		/etc/init.d/rsyslogsetup
		/etc/init.d/syslog restart
		echo "restart syslog"
	fi
fi


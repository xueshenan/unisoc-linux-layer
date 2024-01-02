#!/bin/sh

a=3
b=0
c=vfat

mountpoint=/mnt/media
device=/dev/null

log(){
	echo $1 >/dev/kmsg
}

cat /proc/mounts | grep /mnt/media
if [ $? -eq 0 ]; then
	log "sdcard has been mounted"
	exit 0
fi

if [ ! -n "$1" ] || [ "$1" = "start" ]; then
        log "mount_sd.sh is used no para"
        hotdev=null
else
	hotdev=$1
fi

if [ -c /dev/ubi0 ]
then
	if [ -b /dev/mmcblk0p1 ]
	then
        	log "/dev/mmcblk0p1 exist"
		device=/dev/mmcblk0p1
	elif [ -b /dev/mmcblk1p1 ]
	then
		log "/dev/mmcblk1p1 exist"
                device=/dev/mmcblk1p1
	elif [ -b /dev/mmcblk0 ]
	then
		log "/dev/mmcblk0 exist"
		device=/dev/mmcblk0
	elif [ -b /dev/mmcblk1 ]
        then
                log "/dev/mmcblk1 exist"
		device=/dev/mmcblk1
        else
                log "sdcard is not exist"
		exit 0
        fi

elif [ -b /dev/mmcblk0p1 ]
then
        if [ -b /dev/mmcblk1p1 ]
        then
                log "/dev/mmcblk1p1 exist"
		device=/dev/mmcblk1p1
	elif [ -b /dev/mmcblk1 ]
        then
                log "/dev/mmcblk1 exist"
		device=/dev/mmcblk1
        else
                log "sdcard is not exist"
		exit 0
        fi

fi

dev=${device: 5: 7}
if [ $hotdev != null ]
then
        log "mount_sd.sh is used by hotplug"
        if [ $dev != $hotdev ]; then
                log "hotdev $hotdev is not equal to sdcard $dev"
                exit 0
        fi
fi

exec 9<>/dev/mount_sd.lock
flock -n 9
[ $? -eq 1 ] && { log "mount_sd.sh is using"; exit; }

if [ $device != /dev/null ]
then
	ret=$(blkid $device | awk -F ' ' '{print $NF}' | sed 's/TYPE=//g' | sed 's/"//g')
	log "$(date): $ret"
        	if [ "$ret" = "vfat" ] ; then
			while [ $a -gt 0 -a $b -eq 0 ]; do
				fsck_msdos -y $device >/dev/kmsg
				case $? in
				0)
					log "$(date): sdcard check successful"
					mount $device $mountpoint
					if [ $? -eq 0 ]
					then
						log "sdcard mount successful"
						b=1
					else
						log "sdcard mount failed after fsck"
						exit 0
					fi
					;;
				2)
					log "sdcard check failed"
					b=1
					exit 0
					;;
				4)
					log "sdcard need to recheck"
					a=`expr $a - 1`
					if [ $a -eq 0 ]; then
					log "sdcard recheck failed"
					exit 0
					fi
					;;
				8)
					log "sdcard check failed (sdcard may be no filesystem)"
					b=1
					exit 0
					;;
				*)
					log "There may be no fsck_msdos command"
					b=1
					mount $device $mountpoint
					if [ $? -eq 0 ]
					then
						log "sdcard mount successful"
					else
						log "sdcard mount failed, please run fsck"
						exit 0
					fi
					;;
				esac
			done
		else
			mount $device $mountpoint
			if [ $? -eq 0 ]
			then
				log "sdcard mount successful"
			else
				log "sdcard mount failed, please run fsck"
				exit 0
			fi
		fi
fi
if [ -f /var/run/rsyslogd.pid ]
then
	/etc/init.d/rsyslogsetup
	/etc/init.d/syslog restart
	log "syslog restart successful by mount_sd.sh"
fi

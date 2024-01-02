#! /bin/sh

let found=`grep "sprdboot.mode=cali" /proc/cmdline -o | wc -l`

if [ $found -eq 0 ]; then
	/sbin/resize2fs `df -h | grep userdata | awk '{print $1}'`
	sync
fi
exit 0

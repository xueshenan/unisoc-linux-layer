#!/bin/sh

for element in `ls /sys/class/thermal/thermal_zone* |grep thermal_zone |cut -c 20-`
do
	tz_id=${element%:*}
	thermal_type=`cat /sys/class/thermal/$tz_id/type`
	if [ $thermal_type == "soc-thmzone" -o $thermal_type == "cpu-thmzone" ]; then
		soc_tz=$tz_id
		echo power_allocator > /sys/class/thermal/$soc_tz/policy
		echo 1 > /sys/class/thermal/$soc_tz/thm_enable
		echo "Set soc thermal policy and thm_enable over!"
		break
	fi
done

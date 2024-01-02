#!/bin/sh
# Add common simple commands here, and add relevant comments ,if there is a large section of code, 
# please add the startup script according to the document.

chmod 666 /dev/vha0    #change NPU Driver mod

#Config cpufreq margin for ums9620_2h10 board
cat /proc/cmdline | grep "sprdboot.hardware=ums9620_2h10"
if [ $? -eq 0 ]; then
	echo 40 > /sys/devices/system/cpu/cpufreq/policy0/schedutil/freq_margin
fi

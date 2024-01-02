#!/bin/sh

#get the name of PCIE firmware under the bin folder
check_results_name=`ls /usr/bin/ | grep "AQC" | tail -n 1`
echo "check_results_name is $check_results_name"
if [ -z $check_results_name ]; then
    echo "There is no PCIE FW under the bin folder"
    exit 1
fi

#get the version number
check_results_bin=${check_results_name##*-}
fw_num_in_bin=`echo ${check_results_bin%%_*}`

#get the version number in system
fw_num_in_sys=$(readstat | grep 'Firmware Version' | grep -v 'PHY' | cut -d "=" -f2)
if [ -z $fw_num_in_sys ]; then
    echo "Download the PCIE FW"
    result=`flashBurn -d 0000-01:00.0 /usr/bin/$check_results_name | grep "OK" | cut -d " " -f1`
    if [ $result = "OK" ]; then
        busybox reboot
    else
        echo "dowmload firmware fail"
        exit 1
    fi
fi

#the firmware versions are compared
echo "Fw_num_in_sys is $fw_num_in_sys"
echo "Fw_num_in_bin is $fw_num_in_bin"
compare_result=$(echo $fw_num_in_sys $fw_num_in_bin | awk '$1>$2 {print 1} $1==$2 {print 0} $1<$2 {print 2}')
if [ $compare_result -eq 0 ]; then
    echo "The firmware is normal, next step"
elif [ $compare_result -eq 2 ]; then
    echo "Need to update"
    result=`flashBurn -d 0000-01:00.0 /usr/bin/$check_results_name | grep "OK" | cut -d " " -f1`
    if [ $result = "OK" ]; then
        busybox reboot
    else
        echo "dowmload firmware fail"
        exit 1
    fi
elif [ $compare_result -eq 1 ]; then
    echo "The firmware of PCIE under the bin folder is too old"
fi


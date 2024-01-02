#!/bin/sh

LEVEL=$1

CpuP0="/sys/devices/system/cpu/cpufreq/policy0"
CpuP4="/sys/devices/system/cpu/cpufreq/policy4"
CpuFreqMax="scaling_max_freq"
CpuFreqScal="scaling_available_frequencies"
BlBrightness="/sys/class/backlight/sprd_backlight/brightness"
GpuFreqAvail="/sys/class/devfreq/60000000.gpu/available_frequencies"
GpuFreqMax="/sys/class/devfreq/60000000.gpu/max_freq"
GpuFreqMin="/sys/class/devfreq/60000000.gpu/min_freq"

mode_lowpower()
{
    if [ -f "$CpuP0/$CpuFreqMax" ]
    then
        cpufreqmax=$(cat $CpuP0/$CpuFreqScal | awk '{print $1}')
        echo $cpufreqmax > $CpuP0/$CpuFreqMax
    fi

    if [ -f "$CpuP4/$CpuFreqMax" ]
    then
        cpufreqmax=$(cat $CpuP4/$CpuFreqScal | awk '{print $1}')
        echo $cpufreqmax > $CpuP4/$CpuFreqMax
    fi

    if [ -f "$GpuFreqMax" ]&&[ -f "$GpuFreqMin" ]
    then
        gpufreqmin=$(cat $GpuFreqAvail | awk '{print $1}')
        gpufreqmax=$(cat $GpuFreqAvail | awk '{print $NF}')
        echo $gpufreqmax > $GpuFreqMax
        echo $gpufreqmin > $GpuFreqMin
    fi
}

mode_critical()
{
    mode_lowpower

    if [ -f "$GpuFreqMax" ]&&[ -f "$GpuFreqMin" ]
    then
        gpufreqmax=$(cat $GpuFreqAvail | awk '{print $1}')
        echo $gpufreqmax > $GpuFreqMax
        echo $gpufreqmax > $GpuFreqMin
    fi

    if [ -f "$BlBrightness" ]
    then
        if [ "$(cat $BlBrightness)" -gt 30 ]
        then
            echo 30 > $BlBrightness
        fi
    fi
}

mode_normal()
{
    if [ -f "$CpuP0/$CpuFreqScal" ]
    then
        cpufreqmax=$(cat $CpuP0/$CpuFreqScal | awk '{print $NF}')
        echo $cpufreqmax > $CpuP0/$CpuFreqMax
    fi

    if [ -f "$CpuP4/$CpuFreqScal" ]
    then
        cpufreqmax=$(cat $CpuP4/$CpuFreqScal | awk '{print $NF}')
        echo $cpufreqmax > $CpuP4/$CpuFreqMax
    fi

}

case "${LEVEL}" in
    critical)
        mode_critical
    ;;
    lowpower)
        mode_lowpower
    ;;
    normal)
        mode_normal
    ;;
    *)
    echo "Usage: $0 [ critical | lowpower | normal ]"
    ;;

esac

exit 0

#!/bin/sh

# Copyright (c) 2020 by UNISOC Technology(Shanghai), Inc.  ALL RIGHTS RESERVED.
#
# These coded instructions, statements, and computer programs are the
# copyrighted works and confidential proprietary information of
# UNISOC Technology(Shanghai), Inc. They may not be modified, copied, reproduced,
# distributed, or disclosed to third parties in any manner, medium, or form,
# in whole or in part, without the prior written consent of UNISOC Technology(Shanghai), Inc.

#-------------------------- global variable define -----------------------------
BOARD_CFG="/etc/board.cfg"
board_cfg_keys="board_name fixnv_modem_prefix fixnv_dev_prefix"

board_name=""
fixnv_modem_prefix=""
fixnv_dev_prefix=""

local_update_sh=/etc/init.d/update.sh

LED_GREEN=/sys/devices/platform/soc/soc:aon/63a00000.spi/spi_master/spi3/spi3.0/sc27xx-bltc/leds/sc27xx:green/brightness
LED_BLUE=/sys/devices/platform/soc/soc:aon/63a00000.spi/spi_master/spi3/spi3.0/sc27xx-bltc/leds/sc27xx:blue/brightness
LED_RED=/sys/devices/platform/soc/soc:aon/63a00000.spi/spi_master/spi3/spi3.0/sc27xx-bltc/leds/sc27xx:red/brightness


#add factory reset log path
factoryreset_logfile=/mnt/data/upgrade.log

log_file=/tmp/upgrade.log

# misc partition env value
mode=""
recovery_debug=""
wipestate=""
update_recovery=""
counts=""
extrpath=""

#-------------------------- function define ------------------------------------
INFO() {
    echo "$(date +%Y-%m-%d\ %H:%M:%S) [Otascripts INFO] $*" >> $log_file
    echo "$(date +%Y-%m-%d\ %H:%M:%S) [Otascripts INFO] $*"
}

WARN() {
    echo "$(date +%Y-%m-%d\ %H:%M:%S) [Otascripts WARN] $*" >> $log_file
    echo "$(date +%Y-%m-%d\ %H:%M:%S) [Otascripts WARN] $*"
}

ERR() {
    echo "$(date +%Y-%m-%d\ %H:%M:%S) [Otascripts ERROR] $*" >> $log_file
    echo "$(date +%Y-%m-%d\ %H:%M:%S) [Otascripts ERROR] $*"
}

led_enable() {
    echo 200 > ${LED_GREEN}
    echo 200 > ${LED_RED}
    echo 200 > ${LED_BLUE}
}

led_fail() {
    echo 200 > ${LED_GREEN}
    echo 0 > ${LED_RED}
    echo 200 > ${LED_BLUE}
}

led_disable() {
    echo 0 > ${LED_GREEN}
    echo 0 > ${LED_RED}
    echo 0 > ${LED_BLUE}
}

parse_board_info() {
    local board_cfg_value0=""
    local board_cfg_value1=""
    local board_cfg_value2=""

    INFO "file name: $1"
    while read line
    do
        key=`echo $line | awk -F "=" '{print $1}'`
        value=`echo $line | awk -F "=" '{print $2}'`
        INFO "key: $key"
        INFO "value: $value"
        i=0
        for cfg_key in ${board_cfg_keys};do
            INFO "cfg key: $cfg_key"
            if test "$key" = "$cfg_key"
            then
                eval board_cfg_value$i="$value"
                break
            fi
            i=`expr $i + 1`
        done
    done < $1

    INFO "board_cfg_value: $board_cfg_value0 $board_cfg_value1 $board_cfg_value2"
    board_name=${board_cfg_value0}
    fixnv_modem_prefix=${board_cfg_value1}
    fixnv_dev_prefix=${board_cfg_value2}

    INFO "board_name:$board_name"
    INFO "fixnv_modem_prefix:$fixnv_modem_prefix"
    INFO "fixnv_dev_prefix:$fixnv_dev_prefix"
}


# recovery_debug = 1 : boot-recovery entering the console
# recovery_debug =   : boot-recovery reboot without entering the console
reboot_debug() {
    if [ "$recovery_debug" == "1" ]; then
        INFO "recovery-debug: stay in console to debug"
        fw_setenv recovery_debug
        exit
    fi
    INFO "reboot -f"
    reboot -f
}

wipe_data() {
    local i=1
    local DIR="/mnt/data"
    INFO "Start factory reset"
    rm -rf /mnt/data/*

    while [ $i -le 3 ]; do
        if [ "$(ls $DIR)" ]; then
            INFO "$DIR is not Empty"
            rm -rf /mnt/data/*
        else
            INFO "$DIR is Empty"
            break
        fi
        let i++
    done

    rm -rf /mnt/data/*
    # echo $?
    return $?
}

clear_ui_show_tmp_file() {
    local state_file=$ota_path/complete.txt
    local progress_file=$ota_path/progress.txt

    if [ -e "$state_file" ]; then
        rm $state_file
    fi

    if [ -e "$progress_file" ]; then
        rm $progress_file
    fi
    INFO "Clear ui tmp file"
}

factory_reset() {
    if [ "$wipestate" != "0" ]; then
        INFO "wipestate: $wipestate"
        if [ "$wipestate" == "1" ]; then
            led_enable
            INFO "Wipe userdata process"
            # rm -rf /mnt/data/*
            wipe_data
            if [ $? -ne 0 ]; then
                ERR "Wipe data failed"
                fw_setenv recovery_status wipe_fail
                sleep 0.5
                fw_setenv mode
                led_fail
		cp $log_file $factoryreset_logfile
		log_file=$factoryreset_logfile
                reboot_debug
            fi
	    INFO "End factory reset"
            INFO "Wipe data success, clear state"
            led_disable
            sleep 0.5
            fw_setenv wipe-data
            sleep 0.5
            fw_setenv recovery_status wipe_ok
            sleep 0.5
            fw_setenv mode
            INFO "Wipe data success"
	    cp $log_file $factoryreset_logfile
	    log_file=$factoryreset_logfile
            reboot_debug
        else
            INFO "Wipe other data"
        fi
    else
        INFO "Not wipe data process"
    fi
}

get_extrpath_env() {
    counts=`fw_printenv | grep "^extractpath" | grep -o '/' | wc -l`
    extrpath=`fw_printenv | grep "^extractpath" | cut -d "=" -f2 | cut -d "/" -f -$counts`
    INFO "counts: $counts"
    INFO "extrpath: $extrpath"
}

clear_ota_misc_flag() {
    fw_setenv ota_status no_ota_package
    fw_setenv update_recovery 0
    fw_setenv mode
}

clear_ab_ota_misc_flag() {
    fw_setenv ota_status no_ota_package
    fw_setenv update_recovery 0
    fw_setenv startota 0
}

check_ota_package() {
    if [ -e "/mnt/media/recovery/ota.swu" ]; then
        ota_path="/mnt/media/recovery"
    elif [ -e "/mnt/data/recovery/ota.swu" ]; then
        ota_path="/mnt/data/recovery"
    elif [ -e "/home/root/recovery/ota.swu" ]; then
        ota_path="/home/root/recovery"
    elif [ -e "/home/unisoc/recovery/ota.swu" ]; then
        ota_path="/home/unisoc/recovery"
    else
        INFO "No ota package available so clear flag"
        if [ "$double_copy" == "y" ]; then
            clear_ab_ota_misc_flag
        else
            clear_ota_misc_flag
        fi
        return 0
    fi

    return 1
}

upgrade_pre() {
    clear_ui_show_tmp_file
    # start lvglota
    INFO "start lvglota"
    lvglota &

    if [ -e "$ota_path/recovery.txt" ]; then
        rm $ota_path/recovery.txt
    fi

    if [ -e "$ota_path/normal.txt" ]; then
        rm $ota_path/normal.txt
    fi

    if [ -e "/etc/swupdate_key/public.pem" ]; then
        cp -rf /etc/swupdate_key/public.pem $ota_path/public.pem
    fi

    led_enable
}

upgrade_os() {
    export board_name
    export fixnv_modem_prefix
    export fixnv_dev_prefix
    export update_mode
    export ota_path
    export log_file
    INFO "before swupdate"
    swupdate -l 3 -H $board_name -k $ota_path/public.pem -e stable,preextact -i $ota_path/ota.swu
    ret=$?
    INFO "after swupdate ret=$ret"
    if [ $ret -eq 0 ]; then
        fw_setenv ota_status update_extr_act_ok
        get_extrpath_env
        if [ -e "$extrpath/update.sh" ]; then
            INFO "Ready to use the extracted tools"
            chmod 777 $extrpath/update.sh
            chmod 777 $extrpath/swupdateext
            export extrpath
            $extrpath/update.sh --exec
        else
            INFO "Ready to use the local tools"
            $local_update_sh --exec
        fi
    fi
    INFO "upgrade_os done"
}

update_normal_os() {
    INFO "Upgrade Normal OS"
    INFO "OTA file path $ota_path"
    INFO "Extract update tools PID $$"
    #add flag to disabled power key
    touch /mnt/data/recovery/power_key_disable.txt

    upgrade_pre

    update_mode=0
    upgrade_os
}

update_recovery_os() {
    INFO "Upgrade Recovery OS"
    INFO "OTA file path $ota_path"
    INFO "Extract update tools PID $$"
    #add flag to disabled power key
    touch /mnt/data/recovery/power_key_disable.txt

    upgrade_pre

    update_mode=1
    upgrade_os
}

in_recovery_mode() {
    INFO "########################################"
    INFO "In recovery mode"

    # factory reset wipe userdata
    factory_reset

    # check ota.swu package
    check_ota_package
    if [ $? -eq 0 ]; then
        reboot_debug
    fi

    mv $log_file $ota_path
    log_file=$ota_path/upgrade.log

    update_normal_os
}

in_normal_mode() {
    if [ "$update_recovery" != "1" ]; then
        exit
    fi
    INFO "########################################"
    INFO "In normal mode"

    # check ota.swu package
    check_ota_package
    if [ $? -eq 0 ]; then
        exit
    fi

    cat $log_file >> $ota_path/upgrade.log
    log_file=$ota_path/upgrade.log

    update_recovery_os
}

check_boot_flag() {
    fw_setenv retry_count 0
    if [ $startota -eq '0' ]; then
        INFO "Normal boot $current_mode partition"
        return 0
    elif [ $startota -eq '2' ]; then
        INFO "Upgrade boot $retry_mode partition"
        fw_setenv current_mode $retry_mode
        fw_setenv retry_mode 0
        fw_setenv startota 0
        fw_setenv ota_status upgrade_boot_success
        return 0
    elif [ $startota -eq '3' ]; then
        INFO "Rollback boot $current_mode partition"
        fw_setenv retry_mode 0
        fw_setenv startota 0
        fw_setenv ota_status upgrade_boot_fail
        return 0
    fi

    return 1
}

in_ab_normal_mode() {
    INFO "########################################"
    INFO "In double copy normal mode"

    # check the boot flag
    check_boot_flag
    if [ $? -eq 0 ]; then
        exit
    fi

    if [ "$startota" == "1" ]; then
        # check ota.swu package
        check_ota_package
        if [ $? -eq 0 ]; then
            exit
        fi
        mv $log_file $ota_path
        log_file=$ota_path/upgrade.log
        update_normal_os
    fi
}

in_ab_recovery_mode() {
    INFO "########################################"
    INFO "In double copy recovery mode"

    # factory reset wipe userdata
    factory_reset
}

get_misc_env() {
    fw_printenv

    mode=`fw_printenv | grep "^mode=" | cut -f 2 -d'='`
    recovery_debug=`fw_printenv | grep "^recovery_debug=" | cut -f 2 -d'='`
    wipestate=`fw_printenv | grep "^wipe-data=" | cut -f 2 -d'='`
    update_recovery=`fw_printenv | grep "^update_recovery=" | cut -d "=" -f2`
    double_copy=`fw_printenv | grep "^double_copy=" | cut -d "=" -f2`
    retry_mode=`fw_printenv | grep "^retry_mode=" | cut -d "=" -f2`
    retry_count=`fw_printenv | grep "^retry_count=" | cut -d "=" -f2`
    startota=`fw_printenv | grep "^startota=" | cut -d "=" -f2`
    current_mode=`fw_printenv | grep "^current_mode=" | cut -d "=" -f2`

    INFO "mode:$mode"
    INFO "wipestate:$sipestate"
    INFO "recovery_status:$recovery_status"
    INFO "update_recovery:$update_recovery"
    INFO "double_copy:$double_copy"
    INFO "retry_mode:$retry_mode"
    INFO "retry_count:$retry_count"
    INFO "startota:$startota"
    INFO "current_mode:$current_mode"
}

main() {
    parse_board_info $BOARD_CFG

    if [ -z "$board_name" ] || [ -z "$fixnv_modem_prefix" ] || [ -z "$fixnv_dev_prefix" ]; then
        ERR "CANNOT GET BOARD INFO!!"
        exit
    fi

    # print misc partition env and get vlaue
    get_misc_env

    if [ "$double_copy" == "y" ]; then
        # double copy partition
        if [ "$mode" == "boot-recovery" ]; then
            in_ab_recovery_mode
        else
            in_ab_normal_mode
        fi
    else
        # sigle partition
        if [ "$mode" == "boot-recovery" ]; then
            in_recovery_mode
        else
            in_normal_mode
        fi
    fi
}

main

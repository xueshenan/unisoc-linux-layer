#!/bin/sh

# Copyright (c) 2020 by UNISOC Technology(Shanghai), Inc.  ALL RIGHTS RESERVED.
#
# These coded instructions, statements, and computer programs are the
# copyrighted works and confidential proprietary information of
# UNISOC Technology(Shanghai), Inc. They may not be modified, copied, reproduced,
# distributed, or disclosed to third parties in any manner, medium, or form,
# in whole or in part, without the prior written consent of UNISOC Technology(Shanghai), Inc.

#-------------------------- global variable define -----------------------------
LED_GREEN=/sys/devices/platform/soc/soc:aon/63a00000.spi/spi_master/spi3/spi3.0/sc27xx-bltc/leds/sc27xx:green/brightness
LED_BLUE=/sys/devices/platform/soc/soc:aon/63a00000.spi/spi_master/spi3/spi3.0/sc27xx-bltc/leds/sc27xx:blue/brightness
LED_RED=/sys/devices/platform/soc/soc:aon/63a00000.spi/spi_master/spi3/spi3.0/sc27xx-bltc/leds/sc27xx:red/brightness

# misc partition env value
recovery_debug=""

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

get_misc_env() {
    fw_printenv

    recovery_debug=`fw_printenv | grep "^recovery_debug=" | cut -f 2 -d'='`
    double_copy=`fw_printenv | grep "^double_copy=" | cut -d "=" -f2`
    retry_mode=`fw_printenv | grep "^retry_mode=" | cut -d "=" -f2`
    retry_count=`fw_printenv | grep "^retry_count=" | cut -d "=" -f2`
    startota=`fw_printenv | grep "^startota=" | cut -d "=" -f2`
    current_mode=`fw_printenv | grep "^current_mode=" | cut -d "=" -f2`

    INFO "recovery_debug:$recovery_debug"
    INFO "double_copy:$double_copy"
    INFO "retry_mode:$retry_mode"
    INFO "retry_count:$retry_count"
    INFO "startota:$startota"
    INFO "current_mode:$current_mode"
}

finish_func() {
    if [ -e "$ota_path/recovery.txt" ]; then
        mv $ota_path/recovery.txt $ota_path/recoverybk.txt
    fi
    if [ -e "$ota_path/normal.txt" ]; then
        mv $ota_path/normal.txt $ota_path/normalbk.txt
    fi
    if [ -e "$ota_path/preextact.txt" ]; then
        rm -rf $ota_path/preextact.txt
    fi
    if [ -e "$extrpath" ]; then
        rm -rf $extrpath
    fi
}

# eecovery_debug = 1 : boot-recovery entering the console
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

update_nv() {
    INFO "Ready to update NV"
    fw_setenv nvmerge_installing 1
    INFO "fw_setenv nvmerge_installing 1"
    ls -l $extrpath
    if [ -e "$extrpath/nvmerge.cfg" ] && [ -e "$extrpath/${fixnv_modem_prefix}fixnv1.bin" ]; then
        INFO "NV need to be upgraded"
        nvsize=`ls -l $extrpath/${fixnv_modem_prefix}fixnv1.bin | awk '{print $5}'`
        nvsize=`printf "0x%x\n" $nvsize`
        INFO "NV SIZE: $nvsize"
        if [ "$double_copy" == "y" ]; then
            nvmerge $extrpath/nvmerge.cfg "/dev/${fixnv_modem_prefix}fixnv1_${current_mode}" "/dev/${fixnv_modem_prefix}fixnv2_${current_mode}" $extrpath/${fixnv_modem_prefix}fixnv1.bin $nvsize
        else
            nvmerge $extrpath/nvmerge.cfg "/dev/${fixnv_modem_prefix}fixnv1" "/dev/${fixnv_modem_prefix}fixnv2" $extrpath/${fixnv_modem_prefix}fixnv1.bin $nvsize
        fi
        if [ $? -eq 0 ]; then
            INFO "Update NV successed"
            led_disable
        else
            ERR "Failed to update NV"
            fw_setenv ota_status update_nv_fail
            finish_func
            fw_setenv mode
            led_fail
            sleep 5
        fi
    else
        WARN "No nv files exist"
        led_disable
    fi
    INFO "clear nvmerge_installing"
    fw_setenv nvmerge_installing
}

wait_ui_show_complete() {
    local state_file=$ota_path/complete.txt
    local progress_file=$ota_path/progress.txt
    local i=0
    if [ -e "$state_file" ]; then
        if [ ! -e "$progress_file" ]; then
            INFO "$progress_file not exist!"
            echo 100.00 > $progress_file
        fi

        while [ $i -le 15 ]; do
            local current_percent=`cat $progress_file | cut -d "." -f1`
            local show_percent=`cat $state_file | cut -d "." -f1`
            INFO "show:$show_percent current:$current_percent"

            if [ "$show_percent" -ge "$current_percent" ]; then
                INFO "show complete!"
                return 0
            fi
            let i+=1
            sleep 2
        done
    else
        INFO "$state_file not exist!"
    fi
    return 1
}

set_misc_flag_ok() {
    fw_setenv ota_status update_normal_ok
    fw_setenv base_images_checked
    fw_setenv mode
}

set_misc_flag_fail() {
    fw_setenv ota_status update_normal_fail
    fw_setenv update_recovery 0
    fw_setenv mode
}

set_ab_misc_flag_ok() {
    if [ "$current_mode" == "a" ]; then
        retry_mode="b"
    else
        retry_mode="a"
    fi
    fw_setenv ota_status update_ok
    fw_setenv base_images_checked
    fw_setenv retry_mode $retry_mode
    fw_setenv startota 2
}

set_ab_misc_flag_fail() {
    fw_setenv ota_status update_fail
    fw_setenv startota 3
}

check_upgrade_normal_result() {
    if [ $1 -eq 0 ]; then
        update_nv
        INFO "Update normal parts successfully"
        if [ "$double_copy" == "y" ]; then
            set_ab_misc_flag_ok
	    status_update_normal=1
        else
            set_misc_flag_ok
        fi
    else
        led_fail
        ERR "Update normal parts failed"
        if [ "$double_copy" == "y" ]; then
            set_ab_misc_flag_fail
        else
            set_misc_flag_fail
        fi
        sleep 10
    fi
    finish_func
    #delete flag to disabled power key
    rm /mnt/data/recovery/power_key_disable.txt
    INFO "########################################"
    wait_ui_show_complete
}

check_upgrade_recovery_result() {
    fw_setenv base_images_checked
    fw_setenv update_recovery 0
    if [ $1 -eq 0 ]; then
        INFO "Update recovery parts successfully"
        led_disable
        if [ "$double_copy" == "y" ]; then
            set_ab_misc_flag_ok
        else
            fw_setenv ota_status update_recovery_ok
        fi
    else
        ERR "Update recovery parts failed"
        led_fail
        sleep 10
        if [ "$double_copy" == "y" ]; then
            set_ab_misc_flag_fail
        else
            fw_setenv ota_status update_recovery_fail
        fi
    fi
    #delete flag to disabled power key
    rm /mnt/data/recovery/power_key_disable.txt
    finish_func
    INFO "########################################"
    wait_ui_show_complete
}

upgrade_recovery() {
    INFO "Update recovery parts"
    if [ -e "$extrpath/swupdateext" ]; then
        export LD_LIBRARY_PATH=$extrpath
        $extrpath/swupdateext -l 3 -H $board_name -k $ota_path/public.pem -e stable,recovery -i $ota_path/ota.swu
    else
        swupdate -l 3 -H $board_name -k $ota_path/public.pem -e stable,recovery -i $ota_path/ota.swu
    fi
    check_upgrade_recovery_result $?
}

upgrade_normal() {
    INFO "Update normal parts"
    if [ -e "$extrpath/swupdateext" ]; then
        export LD_LIBRARY_PATH=$extrpath
        $extrpath/swupdateext -l 3 -H $board_name -k $ota_path/public.pem -e stable,normal -i $ota_path/ota.swu
    else
        swupdate -l 3 -H $board_name -k $ota_path/public.pem -e stable,normal -i $ota_path/ota.swu
    fi
    check_upgrade_normal_result $?
}

print_export_env() {
    INFO "board_name: $board_name"
    INFO "fixnv_modem_prefix: $fixnv_modem_prefix"
    INFO "finnv_dev_prefix: $fixnv_dev_prefix"
    INFO "update_mode: $update_mode"
    INFO "ota_path: $ota_path"
    INFO "extrpath: $extrpath"
    INFO "log_file: $log_file"
}

main() {
    get_misc_env

    print_export_env

    if [ "$double_copy" == "y" ]; then
        upgrade_normal
	if [ $status_update_normal -eq 1 ]; then
            upgrade_recovery
	fi
    else
        if [ $update_mode == 0 ]; then
            upgrade_normal
        else
            upgrade_recovery
        fi
        INFO "start reboot!"
        sleep 2
        reboot_debug
    fi
}

main

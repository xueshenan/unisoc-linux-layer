#! /bin/sh
illega_info(){
    echo "Unknown parameter $1.
           ---start---
    -i(Time interval) value;
    -c(Times of reboot) value;
           ---read---
    -r(Read the reboot times)";
    sed -i "1s/.*/0/" /home/root/auto_reboot_conf;
    sed -i "2s/.*/0/" /home/root/auto_reboot_conf;
}

help_info(){
    echo "Please input the correct value.
           ---start---
    -i(Time interval) value;
    -c(Times of reboot) value;
           ---read---
    -r(Read the reboot times)";
}

read_info(){
    cnt=$(sed -n '3p' /home/root/auto_reboot_conf);
    echo "The times of reboot is $cnt";
    interval=$(sed -n '1p' /home/root/auto_reboot_conf);
    echo "The interval time of reboot is $interval second";
}

set_cnt(){
    sed -i "2s/.*/$1/" /home/root/auto_reboot_conf;
    sed -i "3s/.*/0/" /home/root/auto_reboot_conf;
    reboot -f;
}

set_delay(){
    if [ $1 -lt 15 ] ; then
        echo "The interval time of your input should be greater than 15";
        break;
    fi
    sed -i "1s/.*/$1/" /home/root/auto_reboot_conf;
}

create_reboot_conf(){
    touch /home/root/auto_reboot_conf;
    echo "0" > /home/root/auto_reboot_conf;
    sed -i "1 a 0" /home/root/auto_reboot_conf;
    sed -i "2 a 0" /home/root/auto_reboot_conf;
}

if [ ! -e "/home/root/auto_reboot_conf" ]; then
    create_reboot_conf;
fi

while getopts "i:c:rh" opt; do
    case $opt in
         i)
           set_delay "$OPTARG";;
         c)
           set_cnt "$OPTARG";;
         r)
           read_info;;
         h)
           help_info;;
         ?)
           illega_info;;
    esac
done

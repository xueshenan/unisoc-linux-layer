#! /bin/sh
sleep 1
interval=$(sed -n '1p' /home/root/auto_reboot_conf)
echo interval=$interval

cnt=$(sed -n '2p' /home/root/auto_reboot_conf)
echo cnt=$cnt

if [ $cnt -gt 0 ];then
   cnt=`expr $cnt - 1`
   sed -i "2s/.*/$cnt/" /home/root/auto_reboot_conf
   cnt_after=$(sed -n '2p' /home/root/auto_reboot_conf)
   if [ $cnt_after == $cnt ];then
      echo ">>>>>>>>>>>enter the autoreboot<<<<<<<<<<"
      sleep $interval
      cnt_reboot=$(sed -n '3p' /home/root/auto_reboot_conf)
      cnt_reboot=`expr $cnt_reboot + 1`
      sed -i "3s/.*/$cnt_reboot/" /home/root/auto_reboot_conf
      reboot -f
   fi
fi
exit 0

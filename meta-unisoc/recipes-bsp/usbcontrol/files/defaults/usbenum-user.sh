#! /bin/sh
#default
sn=`awk '{for(i=1;i<=NF;i++) if($i~/serialno/) print $i}' /proc/cmdline`
sn=${sn#*=}
usbch=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property usbch)
first=$1
second=$2
third=$3

if [ -f /mnt/data/usbenum.lock ]
then
        isself
else
        echo $$ > /mnt/data/usbenum.lock
fi
isself()
{
        if [ "$(cat /mnt/data/usbenum.lock)"x != "$$"x ]
        then
                kill $(cat /mnt/data/usbenum.lock)
                echo $$ > /mnt/data/usbenum.lock
        fi
}

#adb+Diag+Log+AT
debug()
{
		$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property usbch debug)
		/bin/sh /etc/init.d/adbd-init stop
		echo "Starting debug mode"
		setconfig
		echo 0x1782 > /sys/kernel/config/usb_gadget/g1/idVendor
		echo 0x5d07 > /sys/kernel/config/usb_gadget/g1/idProduct
		echo "debug" > /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration
		rmlink
		mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
		chmod 755 /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
		mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs0
		chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs0
		mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs1
		chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs1
		mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs2
		chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs2
		ln -s /sys/kernel/config/usb_gadget/g1/functions/ffs.adb /sys/kernel/config/usb_gadget/g1/configs/b.1/f1
		ln -s /sys/kernel/config/usb_gadget/g1/functions/gser.gs0 /sys/kernel/config/usb_gadget/g1/configs/b.1/f2
		ln -s /sys/kernel/config/usb_gadget/g1/functions/gser.gs1 /sys/kernel/config/usb_gadget/g1/configs/b.1/f3
		ln -s /sys/kernel/config/usb_gadget/g1/functions/gser.gs2 /sys/kernel/config/usb_gadget/g1/configs/b.1/f4

		/bin/sh /etc/init.d/adbd-init start
}

autotest()
{
		echo none > /sys/kernel/config/usb_gadget/g1/UDC
		#echo "Starting autotest mode"
		setconfig
		echo 0x1782 > /sys/kernel/config/usb_gadget/g1/idVendor
		echo 0x4d00 > /sys/kernel/config/usb_gadget/g1/idProduct
		echo "autotest" > /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration
		rmlink
		mkdir -p /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
		chmod 755 /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
		ln -s /sys/kernel/config/usb_gadget/g1/functions/vser.gs7 /sys/kernel/config/usb_gadget/g1/configs/b.1/f1

		ls /sys/class/udc/|xargs echo > /sys/kernel/config/usb_gadget/g1/UDC
}

cali()
{
		echo none > /sys/kernel/config/usb_gadget/g1/UDC
		#echo "Starting cali mode"
		setconfig
		echo 0x1782 > /sys/kernel/config/usb_gadget/g1/idVendor
		echo 0x4d00 > /sys/kernel/config/usb_gadget/g1/idProduct
		echo "cali" > /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration
		rmlink
		mkdir -p /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
		chmod 755 /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
		ln -s /sys/kernel/config/usb_gadget/g1/functions/vser.gs7 /sys/kernel/config/usb_gadget/g1/configs/b.1/f1

		ls /sys/class/udc/|xargs echo > /sys/kernel/config/usb_gadget/g1/UDC
}

setconfig()
{
		mount -t configfs none /sys/kernel/config
		mkdir -p /sys/kernel/config/usb_gadget/g1
		chmod 755 /sys/kernel/config/usb_gadget/g1
		mkdir -p /sys/kernel/config/usb_gadget/g1/strings/0x409
		chmod 775 /sys/kernel/config/usb_gadget/g1/strings/0x409
		mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1
		chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1
		mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
		chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
		echo 0x0404 > /sys/kernel/config/usb_gadget/g1/bcdDevice
		echo 0 > /sys/kernel/config/usb_gadget/g1/bDeviceClass
		echo $sn > /sys/kernel/config/usb_gadget/g1/strings/0x409/serialnumber
		echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/manufacturer
		echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/product
		echo 500 > /sys/kernel/config/usb_gadget/g1/configs/b.1/MaxPower
		echo 0xc0 > /sys/kernel/config/usb_gadget/g1/configs/b.1/bmAttributes
}

rmlink()
{
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f1
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f2
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f3
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f4
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f5
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f6
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f7
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f8
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f9
		rm -f /sys/kernel/config/usb_gadget/g1/configs/b.1/f10
}

UpUSB()
{
		ls /sys/class/udc/|xargs echo > /sys/kernel/config/usb_gadget/g1/UDC
}
usbmode()
{
 vcn=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property virtualcn)
 diag=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property diag)
 log=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property log)
 debug=$(/usr/bin/rwini.sh /mnt/data/usbenum.ini property debug)
 usbch="debug"
 usbch
}

setini()
{
        /usr/bin/rwini.sh /mnt/data/usbenum.ini property $second $third
}

enable()
{
        /usr/bin/rwini.sh /mnt/data/usbenum.ini property $second 1
}

disable()
{
        /usr/bin/rwini.sh /mnt/data/usbenum.ini property $second 0
}


usbch()
{
	if [ $usbch ];then
		case "$usbch" in
			debug)
				debug
				;;
			autotest)
				autotest
				;;
			cali)
				cali
				;;
			*)
				UpUSB
				;;
		esac
	else
		default
		#echo "usbch not exist, use default"
	fi
}

case "$1" in
	debug)
		debug
		;;
	autotest)
		autotest
		;;
	cali)
		cali
		;;
	usbmode)
		usbmode
		;;
	usbch)
		usbch
		;;
        enable)
                enable
                ;;
        disable)
                disable
                ;;
	set)
		setini
		;;
	*)
		UpUSB
		#echo "Usage: /usr/bin/usbenum.sh {default|usbmode|usbch|cali|autotest|debug}"
		;;
esac
rm -f /mnt/data/usbenum.lock
exit 0

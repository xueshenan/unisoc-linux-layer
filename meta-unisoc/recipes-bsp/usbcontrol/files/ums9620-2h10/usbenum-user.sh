#! /bin/sh
#ums9620-2h10
sn=`awk '{for(i=1;i<=NF;i++) if($i~/serialno/) print $i}' /proc/cmdline`
sn=${sn#*=}
if [ -s /mnt/data/usbenum.ini ];then
	flag_ini=/mnt/data/usbenum.ini
else
	flag_ini=/etc/usbenum/usbenum.ini
fi
usbch=$(/usr/bin/rwini.sh $flag_ini property usbch)
first=$1
second=$2
third=$3
isadb=0

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

if [ -f /etc/init.d/adbd-init ]
then
	isadb=1
fi

setdir()
{
	echo "usbenum Init"
	mount -t configfs none /sys/kernel/config
	mkdir -p /sys/kernel/config/usb_gadget/g1
	chmod 755 /sys/kernel/config/usb_gadget/g1
	mkdir -p /sys/kernel/config/usb_gadget/g1/strings/0x409
	chmod 775 /sys/kernel/config/usb_gadget/g1/strings/0x409
	mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1
	chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1
	mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
	chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
	echo $sn > /sys/kernel/config/usb_gadget/g1/strings/0x409/serialnumber

	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs0
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs0
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs1
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs1
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs2
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs2
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs3
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs3
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs4
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs4
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs5
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs5
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0
        chmod 755 /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0
        mkdir -p /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0
        chmod 755 /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0
        echo 0 > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0/cdrom
        echo 0 > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0/ro

	if [ "$isadb"x == "1"x ];then
		mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
		mkdir -p /dev/usb-ffs/adb
		/bin/mount -t functionfs adb /dev/usb-ffs/adb
	fi
}

#example: setVNIC RNDIS
setVNIC()
{
	if [ $1 ];then
		case "$1" in
			RNDIS)
				rmVNIC ecm.gs0
				rmVNIC ncm.gs0
				rmVNIC ncm.gs1
				rmVNIC ncm.gs2
				rmVNIC ncm.gs3
				if [ ! -d /sys/kernel/config/usb_gadget/g1/functions/$1 ];then
					mkdir -p /sys/kernel/config/usb_gadget/g1/functions/rndis.gs4
				        chmod 755 /sys/kernel/config/usb_gadget/g1/functions/rndis.gs4
        			fi
				;;
			ECM)
				rmVNIC rndis.gs4
				rmVNIC ncm.gs0
				rmVNIC ncm.gs1
				rmVNIC ncm.gs2
				rmVNIC ncm.gs3
				if [ ! -d /sys/kernel/config/usb_gadget/g1/functions/$1 ];then
					mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ecm.gs0
				        chmod 755 /sys/kernel/config/usb_gadget/g1/functions/ecm.gs0
        			fi
				;;
			NCM)
				rmVNIC ecm.gs0
				rmVNIC rndis.gs4
				if [ ! -d /sys/kernel/config/usb_gadget/g1/functions/$1 ];then
					mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ncm.gs0
				        chmod 755 /sys/kernel/config/usb_gadget/g1/functions/ncm.gs0
					mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ncm.gs1
					chmod 755 /sys/kernel/config/usb_gadget/g1/functions/ncm.gs1
					mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ncm.gs2
					chmod 755 /sys/kernel/config/usb_gadget/g1/functions/ncm.gs2
					mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ncm.gs3
					chmod 755 /sys/kernel/config/usb_gadget/g1/functions/ncm.gs3
        			fi
				;;
			MBIM)
				rmVNIC ecm.gs0
				rmVNIC ncm.gs0
				rmVNIC ncm.gs1
				rmVNIC ncm.gs2
				rmVNIC ncm.gs3
				rmVNIC rndis.gs4
				if [ ! -d /sys/kernel/config/usb_gadget/g1/functions/$1 ];then
					mkdir -p /sys/kernel/config/usb_gadget/g1/functions/mbim.gs0
				        chmod 755 /sys/kernel/config/usb_gadget/g1/functions/mbim.gs0
        			fi
				;;
			NONE)
				rmVNIC ecm.gs0
				rmVNIC ncm.gs0
				rmVNIC ncm.gs1
				rmVNIC ncm.gs2
				rmVNIC ncm.gs3
				rmVNIC rndis.gs4
				;;
			ums)
                                rmVNIC ecm.gs0
                                rmVNIC ncm.gs0
				rmVNIC ncm.gs1
				rmVNIC ncm.gs2
				rmVNIC ncm.gs3
                                rmVNIC rndis.gs4
                                ;;
			*)
				echo "usbenumerr:VNIC $1!"
				exit 1
				;;
		esac
	else
		echo "usbenumerr:VNIC no arg!"
		exit 1
	fi
}

#example: rmVNIC rndis.gs4
rmVNIC()
{
	if [ -d /sys/kernel/config/usb_gadget/g1/functions/$1 ];then
		rmdir /sys/kernel/config/usb_gadget/g1/functions/$1
	fi
}

#example: setid 1782 5d07 debug
setid()
{
	echo 0x$1 > /sys/kernel/config/usb_gadget/g1/idVendor
        echo 0x$2 > /sys/kernel/config/usb_gadget/g1/idProduct
        echo "$3" > /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration
}

#example: setdevice 0404 0
setdevice()
{
	echo 0x$1 > /sys/kernel/config/usb_gadget/g1/bcdDevice
        echo $2 > /sys/kernel/config/usb_gadget/g1/bDeviceClass
}

#example: setname Unisoc-phone Unisoc-phone
setname()
{
	echo "$1" > /sys/kernel/config/usb_gadget/g1/strings/0x409/manufacturer
        echo "$2" > /sys/kernel/config/usb_gadget/g1/strings/0x409/product
}

#example: setpower 500 c0
setpower()
{
	echo $1 > /sys/kernel/config/usb_gadget/g1/configs/b.1/MaxPower
        echo 0x$2 > /sys/kernel/config/usb_gadget/g1/configs/b.1/bmAttributes
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

#example: setln gser.gs0 f1
setln()
{
	debug=$(/usr/bin/rwini.sh $flag_ini $usbch debug)
	if [ "$1"x == "ffs.adb"x ];then
		if [ "$isadb"x == "1"x ]&&[ "$debug"x == "1"x ];then
			ln -s /sys/kernel/config/usb_gadget/g1/functions/$1 /sys/kernel/config/usb_gadget/g1/configs/b.1/$2
		fi
	else
		if [ -d /sys/kernel/config/usb_gadget/g1/functions/$1 ];then
			ln -s /sys/kernel/config/usb_gadget/g1/functions/$1 /sys/kernel/config/usb_gadget/g1/configs/b.1/$2
		fi
	fi
}

startadb()
{
	start-stop-daemon --start --exec /usr/bin/adbd &
}

stopadb()
{
	start-stop-daemon --stop  --name adbd &
}

UpUSB()
{
	if [ "$isadb"x == "1"x ]&&[ "$debug"x == "1"x ];then
		startadb
	else
		echo 25100000.dwc3 > /sys/kernel/config/usb_gadget/g1/UDC
	fi
}

DownUSB()
{
	if [ "$isadb"x == "1"x ];then
		stopadb
	fi
		echo none > /sys/kernel/config/usb_gadget/g1/UDC
}

debug()
{
                /bin/sh /etc/init.d/adbd-init stop
                echo "Starting debug mode"
                mount -t configfs none /sys/kernel/config
                mkdir -p /sys/kernel/config/usb_gadget/g1
                chmod 755 /sys/kernel/config/usb_gadget/g1
                echo 0x1782 > /sys/kernel/config/usb_gadget/g1/idVendor
                echo 0x5d07 > /sys/kernel/config/usb_gadget/g1/idProduct
                echo 0x0404 > /sys/kernel/config/usb_gadget/g1/bcdDevice
                echo 0 > /sys/kernel/config/usb_gadget/g1/bDeviceClass

                mkdir -p /sys/kernel/config/usb_gadget/g1/strings/0x409
                chmod 775 /sys/kernel/config/usb_gadget/g1/strings/0x409
                echo $sn > /sys/kernel/config/usb_gadget/g1/strings/0x409/serialnumber
                echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/manufacturer
                echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/product
                mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1
                chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1
                mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
                chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
                echo "debug" > /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration
                echo 500 > /sys/kernel/config/usb_gadget/g1/configs/b.1/MaxPower
                echo 0xc0 > /sys/kernel/config/usb_gadget/g1/configs/b.1/bmAttributes
		rmlink
		rm /sys/kernel/config/usb_gadget/g1/functions/rndis.gs4
                rm /sys/kernel/config/usb_gadget/g1/functions/ecm.gs0
                rm /sys/kernel/config/usb_gadget/g1/functions/ncm.gs0
                mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
                chmod 755 /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
                ln -s /sys/kernel/config/usb_gadget/g1/functions/ffs.adb /sys/kernel/config/usb_gadget/g1/configs/b.1/f1
                mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs0
                chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs0
                ln -s /sys/kernel/config/usb_gadget/g1/functions/gser.gs0 /sys/kernel/config/usb_gadget/g1/configs/b.1/f2
                mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs1
                chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs1
                ln -s /sys/kernel/config/usb_gadget/g1/functions/gser.gs1 /sys/kernel/config/usb_gadget/g1/configs/b.1/f3
                mkdir -p /sys/kernel/config/usb_gadget/g1/functions/gser.gs2
                chmod 755 /sys/kernel/config/usb_gadget/g1/functions/gser.gs2
                ln -s /sys/kernel/config/usb_gadget/g1/functions/gser.gs2 /sys/kernel/config/usb_gadget/g1/configs/b.1/f4
		/bin/sh /etc/init.d/adbd-init start
}

autotest()
{
	echo none > /sys/kernel/config/usb_gadget/g1/UDC
	#echo "Starting autotest mode"
        mount -t configfs none /sys/kernel/config
        mkdir -p /sys/kernel/config/usb_gadget/g1
        chmod 755 /sys/kernel/config/usb_gadget/g1
        echo 0x1782 > /sys/kernel/config/usb_gadget/g1/idVendor
        echo 0x4d00 > /sys/kernel/config/usb_gadget/g1/idProduct
        echo 0x0404 > /sys/kernel/config/usb_gadget/g1/bcdDevice
        echo 0 > /sys/kernel/config/usb_gadget/g1/bDeviceClass

        mkdir -p /sys/kernel/config/usb_gadget/g1/strings/0x409
        chmod 775 /sys/kernel/config/usb_gadget/g1/strings/0x409
        echo $sn > /sys/kernel/config/usb_gadget/g1/strings/0x409/serialnumber
        echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/manufacturer
        echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/product

        mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1
        chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1
        mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
        chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
        echo "autotest" > /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration
        echo 500 > /sys/kernel/config/usb_gadget/g1/configs/b.1/MaxPower
        echo 0xc0 > /sys/kernel/config/usb_gadget/g1/configs/b.1/bmAttributes

	rmlink
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
	ln -s /sys/kernel/config/usb_gadget/g1/functions/vser.gs7 /sys/kernel/config/usb_gadget/g1/configs/b.1/f1

	echo 25100000.dwc3 > /sys/kernel/config/usb_gadget/g1/UDC
}

cali()
{
	echo none > /sys/kernel/config/usb_gadget/g1/UDC
	#echo "Starting cali mode"
        mount -t configfs none /sys/kernel/config
        mkdir -p /sys/kernel/config/usb_gadget/g1
        chmod 755 /sys/kernel/config/usb_gadget/g1
        echo 0x1782 > /sys/kernel/config/usb_gadget/g1/idVendor
        echo 0x4d00 > /sys/kernel/config/usb_gadget/g1/idProduct
        echo 0x0404 > /sys/kernel/config/usb_gadget/g1/bcdDevice
        echo 0 > /sys/kernel/config/usb_gadget/g1/bDeviceClass

        mkdir -p /sys/kernel/config/usb_gadget/g1/strings/0x409
        chmod 775 /sys/kernel/config/usb_gadget/g1/strings/0x409
        echo $sn > /sys/kernel/config/usb_gadget/g1/strings/0x409/serialnumber
        echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/manufacturer
        echo "Unisoc Phone" > /sys/kernel/config/usb_gadget/g1/strings/0x409/product

        mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1
        chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1
        mkdir -p /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
        chmod 755 /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409
        echo "cali" > /sys/kernel/config/usb_gadget/g1/configs/b.1/strings/0x409/configuration
        echo 500 > /sys/kernel/config/usb_gadget/g1/configs/b.1/MaxPower
        echo 0xc0 > /sys/kernel/config/usb_gadget/g1/configs/b.1/bmAttributes

	rmlink
	mkdir -p /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
	chmod 755 /sys/kernel/config/usb_gadget/g1/functions/vser.gs7
	ln -s /sys/kernel/config/usb_gadget/g1/functions/vser.gs7 /sys/kernel/config/usb_gadget/g1/configs/b.1/f1
	echo 25100000.dwc3 > /sys/kernel/config/usb_gadget/g1/UDC
}


usbmode()
{
	vcn=$(/usr/bin/rwini.sh $flag_ini property virtualcn)
	diag=$(/usr/bin/rwini.sh $flag_ini property diag)
	log=$(/usr/bin/rwini.sh $flag_ini property log)
	debug=$(/usr/bin/rwini.sh $flag_ini property debug)
	if [ "$diag"x != "0"x ]&&[ "$diag"x != "1"x ];then
        	echo "usbenum.ini error, sprd-diag should be 0 or 1"
	        exit 1
	fi
	if [ "$log"x != "0"x ]&&[ "$log"x != "1"x ];then
        	echo "usbenum.ini error, sprd-log should be 0 or 1"
	        exit 1
	fi
	if [ "$debug"x != "0"x ]&&[ "$debug"x != "1"x ];then
        	echo "usbenum.ini error, sprd-debug should be 0 or 1"
	        exit 1
	fi
	calcresult=$(($diag*4 + $log*2 + $debug))
	if [ "$usbch"x == "cali"x ]||[ "$usbch"x == "autotest"x ];then
	        exit 0;
	fi
	if [ "$vcn"x == "0"x ];then
        	case $calcresult in
                	7)
                        usbch="mode1"
                        ;;
	                6)
                        usbch="mode4"
                        ;;
                	5)
                        usbch="mode5"
                        ;;
	                4)
                        usbch="mode3"
                        ;;
			2)
			usbch="mode6"
                        ;;
        	        0)
                        usbch="mode2"
                        ;;
                	*)
                        ;;
	        esac
	fi
	if [ "$vcn"x == "1"x ];then
        	case $calcresult in
                	7)
                        usbch="mode11"
                        ;;
	                6)
                        usbch="mode15"
                        ;;
        	        3)
                        usbch="mode16"
                        ;;
                	2)
                        usbch="mode14"
                        ;;
	                1)
                        usbch="mode13"
                        ;;
        	        0)
                        usbch="mode12"
                        ;;
                	*)
                        ;;
	        esac
	fi
	if [ "$vcn"x == "2"x ];then
        	case $calcresult in
                	7)
                        usbch="mode21"
                        ;;
	                6)
                        usbch="mode25"
                        ;;
        	        3)
                        usbch="mode26"
                        ;;
                	2)
                        usbch="mode24"
                        ;;
	                1)
                        usbch="mode23"
                        ;;
        	        0)
                        usbch="mode22"
                        ;;
                	*)
                        ;;
	        esac
	fi
	if [ "$vcn"x == "3"x ];then
        	case $calcresult in
                	7)
                        usbch="mode31"
                        ;;
	                6)
                        usbch="mode35"
                        ;;
        	        3)
                        usbch="mode36"
                        ;;
                	2)
                        usbch="mode34"
                        ;;
	                1)
                        usbch="mode33"
                        ;;
        	        0)
                        usbch="mode32"
                        ;;
                	*)
                        ;;
	        esac
	fi
	if [ "$vcn"x == "4"x ];then
        	case $calcresult in
                	7)
                        usbch="mode41"
                        ;;
	                6)
                        usbch="mode45"
                        ;;
        	        3)
                        usbch="mode46"
                        ;;
                	2)
                        usbch="mode44"
                        ;;
	                1)
                        usbch="mode43"
                        ;;
        	        0)
                        usbch="mode42"
                        ;;
                	*)
                        ;;
	        esac
	fi
	if [ "$vcn"x == "ums"x ];then
                usbch="ums"
        fi
	usbch
}

setini()
{
        /usr/bin/rwini.sh $flag_ini property $second $third
}

enable()
{
        /usr/bin/rwini.sh $flag_ini property $second 1
}

disable()
{
        /usr/bin/rwini.sh $flag_ini property $second 0
}

usbch()
{
	echo "usbenum $usbch"
	if [ $usbch ];then
		idVendor=$(/usr/bin/rwini.sh $flag_ini $usbch idVendor)
		if [ $idVendor ];then
			DownUSB
			$(/usr/bin/rwini.sh $flag_ini property curr-usbch $usbch)
		else
			echo "no such USB mode"
			exit 1
		fi
		idProduct=$(/usr/bin/rwini.sh $flag_ini $usbch idProduct)
		configuration=$(/usr/bin/rwini.sh $flag_ini $usbch configuration)
		setid $idVendor $idProduct $configuration
		bcdDevice=$(/usr/bin/rwini.sh $flag_ini $usbch bcdDevice)
		bDeviceClass=$(/usr/bin/rwini.sh $flag_ini $usbch bDeviceClass)
		setdevice $bcdDevice $bDeviceClass
		manufacturer=$(/usr/bin/rwini.sh $flag_ini $usbch manufacturer)
		product=$(/usr/bin/rwini.sh $flag_ini $usbch product)
		setname $manufacturer $product
		MaxPower=$(/usr/bin/rwini.sh $flag_ini $usbch MaxPower)
		bmAttributes=$(/usr/bin/rwini.sh $flag_ini $usbch bmAttributes)
		setpower $MaxPower $bmAttributes
		rmlink
		VNIC=$(/usr/bin/rwini.sh $flag_ini $usbch VNIC)
		setVNIC $VNIC
		$(/usr/bin/rwini.sh $flag_ini property virtualcn $VNIC)
		for i in f1 f2 f3 f4 f5 f6 f7 f8 f9 f10
		do
			usbdir=$(/usr/bin/rwini.sh $flag_ini $usbch $i)
			setln $usbdir $i
		done
		if [ "$VNIC"x == "ums"x ];then
                        sleep 1
                        sdfile=$(/usr/bin/rwini.sh $flag_ini $usbch sdfile)
                        if [ -b $sdfile ];then
                                echo 1 > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0/removable
                                echo $sdfile > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0/file
                        else
                                echo 0 > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0/removable
                                echo "" > /sys/kernel/config/usb_gadget/g1/functions/mass_storage.gs0/lun.0/file
                        fi
                fi
		UpUSB
	else
		default
		#echo "usbch not exist, use default"
	fi
}

case "$1" in
	debug)
		debug
		rm -f /mnt/data/usbenum.lock
		exit 0
		;;
	autotest)
		autotest
		rm -f /mnt/data/usbenum.lock
		exit 0
		;;
	cali)
		cali
		rm -f /mnt/data/usbenum.lock
		exit 0
		;;
	usbmode)
		if [ "$2"x == "init"x ];then
			setdir
		fi
		usbmode
		rm -f /mnt/data/usbenum.lock
		exit 0
		;;
	usbch)
		if [ "$2"x == "init"x ];then
			setdir
		fi
		usbch
		rm -f /mnt/data/usbenum.lock
		exit 0
		;;
        enable)
                enable
		rm -f /mnt/data/usbenum.lock
		exit 0
                ;;
        disable)
                disable
		rm -f /mnt/data/usbenum.lock
		exit 0
                ;;
	set)
		setini
		rm -f /mnt/data/usbenum.lock
		exit 0
		;;
	mode*)
		usbch="$1"
		usbch
		rm -f /mnt/data/usbenum.lock
		exit 0
		;;
	ums)
                usbch="$1"
                usbch
                rm -f /mnt/data/usbenum.lock
                exit 0
                ;;
	*)
		UpUSB
		rm -f /mnt/data/usbenum.lock
		exit 0
		#echo "Usage: /usr/bin/usbenum.sh {default|usbmode|usbch|cali|autotest|debug}"
		;;
esac
rm -f /mnt/data/usbenum.lock
exit 0

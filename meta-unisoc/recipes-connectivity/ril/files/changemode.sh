# !/bin/sh
startmbim=$(/usr/bin/rwini.sh /mnt/data/mbimmode.ini property startmbim)

case "$1" in
  setmbim)
	echo "Starting setmbim"
	ismbim=$(ps | grep "mbim-daemon" | grep -v grep | wc -l)
	if [ $ismbim -eq 0 ];then
		/etc/init.d/sprdrild-init stop
		sleep 1
		/usr/bin/mbim-daemon&
	fi
	;;
  setrild)
	echo "Starting setrild"
	isrild=$(ps | grep "sprdrild" | grep -v grep | wc -l)
	if [ $isrild -eq 0 ];then
		/etc/init.d/sprdzmbim-init stop
		sleep 1
		/usr/bin/sprdrild&
	fi
	;;
  setSavembimmode)
	echo "setmbim"
	$(/usr/bin/rwini.sh /mnt/data/mbimmode.ini property startmbim 1)
	;;
  setSaverildmode)
	echo "setrild"
	$(/usr/bin/rwini.sh /mnt/data/mbimmode.ini property startmbim 0)
	;;
  *)
	echo "without input, init"
	exit 1
	;;
esac

exit 0


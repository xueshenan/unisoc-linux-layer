
#!/bin/sh
#
#start adb usb
#

case "$1" in
  start)
    echo "Starting adbd..."
    mount -t configfs none /sys/kernel/config
    mkdir -p /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
    mkdir -p /dev/usb-ffs/adb
    /bin/mount -t functionfs adb /dev/usb-ffs/adb
    start-stop-daemon --start --exec /usr/bin/adbd
    ;;
  stop)
    echo "Stop adbd..."
    start-stop-daemon --stop  --name adbd
    ;;
  reload|force-reload)
    ;;
  restart)
    ;;
  *)
    echo "Usage: /etc/init.d/adbd {start|stop|reload|restart|force-reload}"
    exit 1
esac

exit 0



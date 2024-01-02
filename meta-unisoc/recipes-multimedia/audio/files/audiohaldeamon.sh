#!/bin/sh
#
#start adb usb
#
#! /bin/sh

DESC="audiohalserver"

do_start() {
	mkdir -p /var/audio
        chmod 777 /var/audio
	chmod 666 /dev/spipe_lte4
	chmod 666 /dev/spipe_lte6
        /usr/bin/audiohal &
}

do_stop() {
	start-stop-daemon --stop --name audiohal --quiet
}

case "$1" in
  start)
        echo "Starting $DESC"
        do_start
        ;;
  stop)
        echo "Stopping $DESC"
        do_stop
        ;;
  restart|force-reload)
        echo "Restarting $DESC"
        do_stop
        sleep 1
        do_start
        ;;
  *)
        echo "Usage: $0 {start|stop|restart|force-reload}" >&2
        exit 1
        ;;
esac

exit 0

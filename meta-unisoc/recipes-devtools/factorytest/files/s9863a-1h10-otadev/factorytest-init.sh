#! /bin/sh

DESC="factorytest-init"

do_start() {
	cp -rf /etc/factorytest/factorytest.ini /mnt/data/factorytest.ini
	/usr/bin/factorytest &
}

do_stop() {
	start-stop-daemon --stop --name factorytest --quiet
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

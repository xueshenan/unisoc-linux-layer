#! /bin/sh

DESC="nativemmi-init"

do_start() {
	/usr/bin/nativemmi &
}

do_stop() {
	start-stop-daemon --stop --name factory-test --quiet
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

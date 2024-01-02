#! /bin/sh

DESC="reboot-service"
set -e

do_start() {
	/usr/sbin/reboot_test-init.sh &
}

case "$1" in
  start)
	echo "Starting $DESC"
	do_start
	;;
  *)
	echo "Usage: $0 {start}" >&2
	exit 1
	;;
esac

exit 0

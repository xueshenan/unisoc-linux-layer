#! /bin/sh

DESC="test_entry"


do_start() {
	/usr/bin/test_entry.sh &
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

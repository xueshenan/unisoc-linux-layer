#! /bin/sh

set -e
case "$1" in
    start)
	/usr/bin/minidumpd &
	echo "done"
	;;
    *)
esac
exit 0

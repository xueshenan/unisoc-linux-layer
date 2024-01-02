#! /bin/sh

set -e
case "$1" in
    start)
	/usr/bin/systemdebuggerd &
	echo "done"
	;;
    *)
esac
exit 0

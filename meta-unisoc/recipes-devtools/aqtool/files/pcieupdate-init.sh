#! /bin/sh

DESC="pcieupdate-service"
set -e

do_start() {
	/usr/bin/pcie_firmware_update.sh &
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

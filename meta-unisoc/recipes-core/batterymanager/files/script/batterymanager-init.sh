# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0

#! /bin/sh

DAEMON=/usr/bin/batterymanager
DESC="batterymanager-init"

do_start() {
	str1=$(cat /etc/inittab)
	result1=$(echo $str1 | grep "${DAEMON}")
	if [ "$result1" != "" ];then
	    echo "Starting $DESC from inittab"
	else
	    echo "Starting $DESC"
	    /usr/bin/batterymanager &
	fi
}

do_stop() {
	start-stop-daemon --stop --name batterymanager --quiet
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

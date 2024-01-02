#!/bin/sh
#
#start adb usb
#
#! /bin/sh

DESC="pulseaudio"

do_start() {
#        start-stop-daemon --start --background --exec /usr/bin/pulseaudio -- --log-level=4 --realtime=0 --system
	mkdir -p /var/audio
	mkdir -p /var/audio/audio_params
	mkdir /mnt/data/.config
	chmod 777 /mnt/data/.config
	chown pulse:pulse  /var/audio
	chown pulse:pulse  /var/audio/audio_params
	chmod 666 /dev/spipe_lte4
	chmod 666 /dev/spipe_lte6
	chmod 666 /dev/input/*
	chmod 666 /dev/audio_wake_lock
	chmod 666 /dev/audio_pipe_voice
	/usr/bin/pulseaudio --realtime=0 --system &
	start-stop-daemon --start --background --exec /usr/bin/call_monitor
}

do_stop() {
	start-stop-daemon --stop --name call_monitor --quiet
	start-stop-daemon --stop --name pulseaudio --quiet
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


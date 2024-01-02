#! /bin/sh

DESC="orca-keyevent-init"

do_start() {
  str1=$(cat /proc/cmdline)
  str2="calibration="
  str3="androidboot.mode=autotest"

  result1=$(echo $str1 | grep "${str2}")
  result2=$(echo $str1 | grep "${str3}")

  if [[ "$result1" != "" || "$result2" != "" ]];then
    echo "enter the calibration or bbat"
    exit 0
  else
    echo "enter the normal"
    /usr/bin/keyevent-deamon &
    exit 0
  fi
}

do_stop(){
  start-stop-daemon --stop --name keyevent-deamon --quiet
}

case "$1" in
  start)
    echo "Starting $DESC"
    do_start
    ;;
  stop)
    echo "Stop $DESC"
    do_stop
    ;;
  *)
    echo "Usage: $0 {start}" >&2
    exit 1
    ;;
esac

exit 0

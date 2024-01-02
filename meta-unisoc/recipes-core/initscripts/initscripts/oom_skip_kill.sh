#! /bin/sh

OOM_SKIP_LIST="/etc/init.d/oom_skip.list"

skip_kill() {
	local  pid
	for  process in `cat $OOM_SKIP_LIST`
	do
		pid=$(ps | grep $process | grep -v grep | awk '{print $1}')
		if [[ -z $pid ]];then
			echo "Not found $process"
		else
			echo -17 > /proc/$pid/oom_adj
		fi
	done
}

main() {
	skip_kill
}

main "@"

#!/bin/sh

BPID=$BASHPID
ps -efww|grep -w 'wcn-log.sh'|grep -v $BPID|grep -v grep|awk '{print $2}'|xargs kill -9
ps -efww|grep -w 'wcn-dump.sh'|grep -v grep|awk '{print $2}'|xargs kill -9
ps -efww|grep -w 'slog_wcn0'|grep -v grep|awk '{print $2}'|xargs kill -9

cat /dev/slog_wcn0 >> $1

echo wcnlog end

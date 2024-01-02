# !/bin/bash
INIFILE=$1
SECTION=$2
ITEM=$3
NEWVAL=$4

function ReadINIfile()
{
  ReadINI=`awk -F '=' "/\[$SECTION\]/{a=1}a==1" $INIFILE|sed -e '1d' -e '/^$/d' -e '/^\[.*\]/,$d' -e "/^$ITEM=.*/!d" -e "s/^$ITEM=//"`
  echo $ReadINI
  sync
}

function WriteINIfile()
{
  WriteINI=`sed -i "/^\[$SECTION\]/,/^\[/ {/^\[$SECTION\]/b;/^\[/b;s#^$ITEM*=.*#$ITEM=$NEWVAL#g;}" $INIFILE`
  echo $WriteINI
  sync
}

if [ "$4" = "" ] ;then
  ReadINIfile $1 $2 $3
else
  WriteINIfile $1 $2 $3 $4
fi

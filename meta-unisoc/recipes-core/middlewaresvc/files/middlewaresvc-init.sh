#!/bin/sh
#
#start qfactorytest
#
export XDG_RUNTIME_DIR=/run/user/0
/usr/bin/middlewaresvc -platform wayland &
echo "start middlewaresvc"

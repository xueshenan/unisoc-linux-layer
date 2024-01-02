#!/bin/sh
#
#start demo test
#
export XDG_RUNTIME_DIR=/run/user/0
/usr/bin/demotest -platform wayland &
echo "start demo test"

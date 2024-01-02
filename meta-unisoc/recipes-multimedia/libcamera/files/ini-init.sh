cp /etc/cam_ini/camera_property.ini /home/user
if ! [ -e /home/user/dump ]; then
    mkdir /home/user/dump
fi
if ! [ -e /home/user/cameraserver ]; then
    mkdir /home/user/cameraserver
fi

#cat /etc/profile.d/pulse_env.sh
#set pulseaudio shell env

if test -z "$PULSE_RUNTIME_PATH" -o -z "$PULSE_STATE_PATH"; then
    export PULSE_RUNTIME_PATH=/mnt/data/.config/pulse
    export PULSE_STATE_PATH=/mnt/data/.config/pulse
fi


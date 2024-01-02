require ofono.inc

SRC_URI  = "\
  file://ofono \
  file://use-python3.patch \
  file://ofono.service \
"
SRC_URI[md5sum] = "31450cabdd8dbbf3f808ea2f2f066863"
SRC_URI[sha256sum] = "eb011fcd3080e93f3a56f96be60350b6595a8b5f36b61646312ba41b0bcb0d75"
PROVIDES = "ofono"

export N6P_5G_SET_CFLAGS = "${N6P_5G_SET}"
# modem save apn or ap save apn flag, control in machine conf file
export MODEM_SAVE_APN_FLAGS = "${@bb.utils.contains('MODEM_SAVE_APN_SET','true','-DMODEM_SAVE_APN_SUPPORT','',d)}"
# dusl sim support flags
export MULTI_SIM_CFLAGS = "${MULTI_SIM_SET}"

# not auto online flag
export NOT_AUTO_ONLINE_FLAGS  = "${@bb.utils.contains('NOT_AUTO_ONLINE_SET','true','-DNOT_AUTO_ONLINE','',d)}"

# tele function flag all
export TELE_FUNCTION_CFLAGS = "${TELE_CFLAGS}"
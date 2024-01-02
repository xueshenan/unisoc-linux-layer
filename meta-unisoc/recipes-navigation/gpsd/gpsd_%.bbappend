# SPDX-FileCopyrightText: 2021 Unisoc (Shanghai) Technologies Co., Ltd.
#
# SPDX-License-Identifier: LicenseRef-Unisoc-General-1.0


do_install_append(){
    sed -i -e 's#DEVICES=""#DEVICES="/dev/gnsspty0"#g' ${D}/etc/default/gpsd.default
    sed -i -e 's#GPSD_OPTIONS=""#GPSD_OPTIONS="-b -n"#g' ${D}/etc/default/gpsd.default
    sed -i "/. \/lib\/init\/vars.sh/i\if [ -x \"/lib/init/vars.sh\" ]; then" ${D}/etc/init.d/gpsd
    sed -i "/. \/lib\/init\/vars.sh/a\fi" ${D}/etc/init.d/gpsd
    sed -i "/. \/lib\/lsb\/init-functions/i\if [ -x \"/lib/lsb/init-functions\" ]; then" ${D}/etc/init.d/gpsd
    sed -i "/. \/lib\/lsb\/init-functions/a\fi" ${D}/etc/init.d/gpsd
    sed -i "/start-stop-daemon --start --quiet --pidfile \$PIDFILE --exec \$DAEMON --test/,+1d" ${D}/etc/init.d/gpsd
    sed -i -e "s#--start --quiet#--start --background --quiet#" ${D}/etc/init.d/gpsd
}

INITSCRIPT_PARAMS = "start 99 3 4 5 . stop 99 0 6 ."

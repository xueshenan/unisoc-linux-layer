FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-new-gobex-for-g_convert.patch \
    file://0002-auto_connect.patch \
    file://0003-sprd_vnd_play.patch \
    file://0004-TemporaryTimeout-180.patch \
    file://0005-bug2053714-A2DP_SRC_AS_BI_01_I.patch \
    file://0006-bug2057949-opp_transfer_error.patch \
	file://0007-bug2085244-spp-connect-fail.patch \
"
deltask do_rm_work

EXTRA_OECONF += "\
    --enable-obex \
    --enable-tools \
"

do_install_append(){
    sed -i -e 's#NOPLUGIN_OPTION=""#NOPLUGIN_OPTION="--compat=true -n -d"#g' ${D}${INIT_D_DIR}/bluetooth

    if [ -f ${S}/src/main.conf ]; then
        install -m 0644 ${S}/src/main.conf ${D}/${sysconfdir}/bluetooth/
    fi
}

INITSCRIPT_PARAMS = "start 20 3 4 5 . stop 20 0 6 ."

PACKAGES = "${PN}-dbg ${PN}-staticdev ${PN}-dev ${PN}-doc ${PN}-locale  ${PN} ${PN}-testtools ${PN}-obex ${PN}-noinst-tools"

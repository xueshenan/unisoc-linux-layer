LICENSE = "CLOSED"

inherit get_release_mode

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PPP_SRC_DIR = "${OEROOT}/source/unisoc/perflink/ppp/"
PPP_INSTALL_DIR = "${DEPLOY_OUT_DIR}/ppp/"
PPP_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/ppp"

export PPP_SRC_MODE = "${@get_release_mode(d, "${PPP_SRC_DIR}")}"

EXTERNALSRC = "${@bb.utils.contains("PPP_SRC_MODE", "customer", "${PPP_RELEASE_DIR}", "${PPP_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"



do_configure() {
    if [ ${PPP_SRC_MODE} != "customer" ]; then
        oe_runconf
    fi
    echo "configure"
}

do_compile () {
    if [ ${PPP_SRC_MODE} != "customer" ]; then
        oe_runmake
    fi
}

do_install_prepend() {
    if [ ${PPP_SRC_MODE} != "customer" ]; then
        rm -rf ${PPP_INSTALL_DIR}/pppd
        install -d ${PPP_INSTALL_DIR}/pppd
        install -d ${PPP_INSTALL_DIR}/etc.ppp
        install -m 0777 ${B}/pppd/pppd ${PPP_INSTALL_DIR}/pppd
	install -m 0777 ${S}/Makefile ${PPP_INSTALL_DIR}/
	install -m 0644 ${S}/etc.ppp/options ${PPP_INSTALL_DIR}/etc.ppp/
	install -m 0600 ${S}/etc.ppp/pap-secrets ${PPP_INSTALL_DIR}/etc.ppp/
	install -m 0600 ${S}/etc.ppp/chap-secrets ${PPP_INSTALL_DIR}/etc.ppp/
    fi
}

SRC_URI+=" \
        file://ppp_route.sh \
"

do_install(){
    if [ ${PPP_SRC_MODE} != "customer" ]; then
	oe_runmake 'DESTDIR=${D}' install
	# Info dir listing isn't interesting at this point so remove it if it exists.
	if [ -e "${D}${infodir}/dir" ]; then
		rm -f ${D}${infodir}/dir
	fi
    fi
    echo "do_install"
    [ -e ${D}${sbindir}/pppd ] || install -d ${D}${sbindir}/
    [ -e ${D}${sbindir}/pppd ] || install -m 0777 ${B}/pppd/pppd ${D}${sbindir}/

}


do_install_append(){
        install -m 0755 ${WORKDIR}/ppp_route.sh  ${D}${bindir}/ppp_route.sh
}

deltask patch

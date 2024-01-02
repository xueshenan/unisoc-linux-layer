SUMMARY = "Init environmnet variables of sysvinit daemon"
LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://initscript\
"
do_install() {
    install -d ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/initscript ${D}${sysconfdir}/initscript
}

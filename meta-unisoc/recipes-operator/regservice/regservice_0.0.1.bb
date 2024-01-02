DESCRIPTION = "reg service"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "close"
SECTION = "bins"
PROVIDES = "regservice"
DEPENDS = "libxml2 xmlparser json-c dbus glib-2.0 curl"
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"

EXTERNALSRC = "${OEROOT}/source/unisoc/operator_dm/autoReg_service"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/operator_dm/autoReg_service"

do_compile () {
    make clean
    make WORKDIR=${WORKDIR}
}
do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/autoRegService ${D}${bindir}/

    install -d ${D}${sysconfdir}/
    install -m 0755 ${THISDIR}/files/http_post_name.conf ${D}${sysconfdir}/http_post_name.conf
}

FILES_${PN} += " \
    ${bindir}/autoRegService \
    ${sysconfdir}/http_post_name.conf \
"

TARGET_CC_ARCH += "${LDFLAGS}"

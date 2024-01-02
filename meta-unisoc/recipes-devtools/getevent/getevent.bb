DESCRIPTION = "debug tools - getevent"
LICENSE = "CLOSED"
PV = "0.1"
PR = "r0"
#LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit closed-source

MODULE_SRC_DIR = "${OEROOT}/source/devtools/getevent"
MODULE_INSTALL_DIR = "${DEPLOY_OUT_DIR}/getevent"
MODULE_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/getevent"

EXTERNALSRC = "${@get_external_src(d)}"
EXTERNALSRC_BUILD = "${@get_external_src_build(d)}"

MODULE_SRC_MODE = "${@get_src_mode(d, "${MODULE_SRC_DIR}")}"


do_self_compile () {
    oe_runmake  -C ${S}  clean
    oe_runmake  -C ${S}
}

do_install () {
    if [ ${MODULE_SRC_MODE} != "customer" ]; then
        install -d ${D}${bindir}/
        install -m 0755 ${S}/getevent ${D}${bindir}/
    else
        install -d ${D}${sysconfdir}/getevent/
        install -m 0666 ${THISDIR}/files/TODO ${D}${sysconfdir}/getevent/TODO
   fi
}

# FILES_${PN} += "${bindir}/getevent \
#               "
TARGET_CC_ARCH += "${LDFLAGS}"

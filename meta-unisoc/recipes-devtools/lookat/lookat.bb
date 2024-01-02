DESCRIPTION = "debug tools - lookat"
LICENSE = "GPLv2"
PV = "0.1"
PR = "r0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ff4ab8363658031055b4759464f22c31"

do_compile () {
    oe_runmake  -C ${S}  O=${B}  clean
    oe_runmake  -C ${S}  O=${B}
}

do_install () {
	install -d ${D}${bindir}/
	install -m 0755 ${S}/lookat ${D}${bindir}/

}

FILES_${PN} += "${bindir}/lookat \
               "
TARGET_CC_ARCH += "${LDFLAGS}"

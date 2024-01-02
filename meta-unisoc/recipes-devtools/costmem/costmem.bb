DESCRIPTION = "Unisoc costmem"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ff4ab8363658031055b4759464f22c31"
PV = "0.1"
PR = "r0"

do_compile () {
    make  -C ${S}  O=${B}  clean
    make  -C ${S}  O=${B} all
}

do_install () {
	install -d ${D}${bindir}/
	install -m 0755 ${S}/costmem ${D}${bindir}/

}

FILES_${PN} += "${bindir}/costmem \
               "
TARGET_CC_ARCH += "${LDFLAGS}"

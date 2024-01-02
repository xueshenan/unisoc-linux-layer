DESCRIPTION = "debug tools - minidump"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "GPLv2"
SECTION = "bin"
PV = "0.1"
PR = "r0"
inherit update-rc.d
LIC_FILES_CHKSUM = "file://COPYING;md5=22fc3fcbf7254fe8756507444cabcd78"


SRC_URI = " \
            file://minidumpinit.sh \
	  "

INITSCRIPT_NAME = "minidumpinit"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 ."

do_compile () {
    oe_runmake  -C ${S}  O=${B}  clean
    oe_runmake  -C ${S}  O=${B}
}

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/minidumpinit.sh ${D}/${sysconfdir}/init.d/minidumpinit
	install -d ${D}${bindir}/
	install -m 0755 ${S}/minidump ${D}${bindir}/minidumpd
}

FILES_${PN} += "${bindir}/minidumpd \
               "
TARGET_CC_ARCH += "${LDFLAGS}"

SUMMARY = "script to boot audio daemon"
LICENSE = "CLOSED"

DEPENDS += ""
RDEPENDS_${PN} = ""

PROVIDES = "audiodaemon"

PV = "0.1"
PR = "r0"

inherit update-rc.d


EXTERNALSRC = "${THISDIR}/audiodaemon"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"
EXTERNALSRC_SYMLINKS = ""

S = "${WORKDIR}"

do_compile () {
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${S}/audiodaemon.sh ${D}${sysconfdir}/init.d/
}

INITSCRIPT_NAME = "audiodaemon.sh"
INITSCRIPT_PARAMS = "start 96 3 4 5 . stop 96 0 6 ."


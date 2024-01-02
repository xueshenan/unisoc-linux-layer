# Recipe created by krashid@testo.de
# This is the basis to integrate uMTP Responder code in Yocto Build

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/viveris/uMTP-Responder.git;protocol=https \
	   file://umtprd-sd.conf \
	   file://umtprd-rmsd.conf \
	   file://umtprd-0.0.1.patch \
	   file://umtprd-0.0.2.patch \
	   file://umtprd-0.0.3.patch \
	   file://hotplug-sd-mtp.sh \
	   file://hotplug-rmsd-mtp.sh \
	"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "7fb3c9158b7b308fe107569306cd65bedc9b8d45"

S = "${WORKDIR}/git"

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# You will almost certainly need to add additional arguments here
	oe_runmake
}

do_install () {
	install -d ${D}${sysconfdir}/umtprd
	install -m 0755 ${WORKDIR}/umtprd-sd.conf ${D}${sysconfdir}/umtprd/umtprd-sd.conf
	install -m 0755 ${WORKDIR}/umtprd-rmsd.conf ${D}${sysconfdir}/umtprd/umtprd-rmsd.conf
	install -d ${D}${bindir}
        install -m 0755 ${S}/umtprd ${D}${bindir}/umtprd
        if [ ${MACHINE} = "sl8541e-smartpen32" ] || [ ${MACHINE} = "sl8541e-smartpen32-wifionly" ]; then
            install -d ${D}${sysconfdir}
            install -m 0755 ${THISDIR}/files/hotplug-sd-mtp.sh ${D}${sysconfdir}
            install -m 0755 ${THISDIR}/files/hotplug-rmsd-mtp.sh ${D}${sysconfdir}
        fi
}

FILES_${PN} += " \
	${bindir}/umtprd \
"

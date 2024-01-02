DESCRIPTION = "Unisoc wcn module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = ""
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
PV = "0.1"
PR = "r0"
PROVIDES = "wcnini"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/wcn;protocol=ssh;branch=unc_linux_trunk_2.0"

do_install () {
	install -d ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${THISDIR}/marlin2/${MACHINE}/connectivity_calibration.ini ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${THISDIR}/marlin2/${MACHINE}/connectivity_configure.ini ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${THISDIR}/marlin2/${MACHINE}/connectivity_calibration.22nm.ini ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${THISDIR}/marlin2/${MACHINE}/connectivity_configure.22nm.ini ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${THISDIR}/regulatory/regulatory.db ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${THISDIR}/regulatory/regulatory.db.p7s ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${OEROOT}/prebuilts/unisoc_bin/marlin2_18a/9863_integration_cm4_v2_builddir/9863_integration_cm4_v2.bin ${D}${nonarch_base_libdir}/firmware/wcnmodem.bin
	install -m 0644 ${OEROOT}/prebuilts/unisoc_bin/gnss_20a/greeneye2_cm4_bds_builddir/gnssbdmodem.bin ${D}${nonarch_base_libdir}/firmware/gpsbd.bin
	install -m 0644 ${OEROOT}/prebuilts/unisoc_bin/gnss_20a/greeneye2_cm4_glonass_builddir/gnssmodem.bin ${D}${nonarch_base_libdir}/firmware/gpsgl.bin
}

FILES_${PN} += "/lib/firmware/*"
FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

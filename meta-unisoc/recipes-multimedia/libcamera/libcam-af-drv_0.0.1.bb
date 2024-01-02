DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamsensor libcamcommon"
RDEPENDS_${PN} = "libcamsensor libcamcommon"
PV = "0.1"
PR = "r0"
PROVIDES = "libcam-af-drv"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/dw9714p/*.so ${D}${libdir}/
    install -m 0777 ${S}/lc898213/*.so ${D}${libdir}/
    install -m 0777 ${S}/dw9768v/*.so ${D}${libdir}/
    install -m 0777 ${S}/dw9800/*.so ${D}${libdir}/
    install -m 0777 ${S}/dw9714/*.so ${D}${libdir}/
    install -m 0777 ${S}/zc524/*.so ${D}${libdir}/
    install -m 0777 ${S}/lc898229/*.so ${D}${libdir}/
	install -m 0777 ${S}/dw9763/*.so ${D}${libdir}/

}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc libispalg module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
#DEPENDS = "libcam-ini-parser libcamsensor libcampm libcamcommon camxmlparser libxml2 camlib"
#RDEPENDS_${PN} = "libcam-ini-parser libcamsensor libcampm libcamcommon camxmlparser libxml2 camlib"
DEPENDS = "libcamcommon  libcampm-isp2.7"
RDEPENDS_${PN} = "libcamcommon  libcampm-isp2.7"
PV = "0.1"
PR = "r0"
PROVIDES = "libispalg"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"
export board="${MACHINE}"
export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libispalg.so ${D}${libdir}/

    if [ ${CAMERA_BOARD} = "sc9863a-1h10" ];then
        install -d ${D}${libdir}/
        install -m 0777 ${S}/libs/ae/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/awb/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/lsc/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/afl/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/af/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/pdaf/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/atm/sprd/lib64/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "ums9620-2h10" ];then
        install -d ${D}${libdir}/
        install -m 0777 ${S}/libs/ae/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/awb/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/lsc/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/afl/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/af/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/pdaf/sprd/lib64/*.so ${D}${libdir}/
        install -m 0777 ${S}/libs/atm/sprd/lib64/*.so ${D}${libdir}/
    fi
}
FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

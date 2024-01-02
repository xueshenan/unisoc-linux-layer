DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamsensor libcamcommon"
RDEPENDS_${PN} = "libcamsensor libcamcommon"
PV = "0.1"
PR = "r0"
PROVIDES = "libcam-sensor-drv"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    if [ ${CAMERA_BOARD} = "sl8541-base" ] ; then
    install -m 0777 ${S}/classic/OmniVision/ov8856_shine/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov13855/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov5675/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov5675_dual/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Galaxycore/gc2145/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Galaxycore/gc2053/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Galaxycore/gc2053_dual/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "sl8541-smartpen32" ] ; then
    install -m 0777 ${S}/classic/OmniVision/ov8856_shine/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov13855/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov5675/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov5675_dual/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Galaxycore/gc2145/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov7251_sunny_dual/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "sl8521e-base" ] ; then
    install -m 0777 ${S}/classic/Galaxycore/gc030a/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "ud710-96board" ] ; then
    install -m 0777 ${S}/classic/Sony/imx363/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov5675_dual/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "t710-smartcoreboard" ] ; then
    install -m 0777 ${S}/classic/Samsung/s5kgm1st_xl/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov8856_xl_front/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov8856_xl/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Galaxycore/gc02m1b_xl/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "sl8581e-3in1" ] ; then
    install -m 0777 ${S}/classic/Sony/imx351/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov8856_shine_front/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov8856_shine/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "sc9863a-1h10" ] ; then
    install -m 0777 ${S}/classic/Sony/imx351/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov8856_shine_front/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov8856_shine/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov7251/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov7251_dual/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov7251_sunny_triple/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/OmniVision/ov7251_sunny_dual/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Galaxycore/gc13033/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Superpix/sp2506/*.so ${D}${libdir}/
    fi
    if [ ${CAMERA_BOARD} = "sc9863a-smartcoreboard" ] ; then
    install -m 0777 ${S}/classic/OmniVision/ov8856_ts/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Cista/sc031gs/*.so ${D}${libdir}/
    install -m 0777 ${S}/classic/Cista/sc031gs_dual/*.so ${D}${libdir}/
    fi
	if [ ${CAMERA_BOARD} = "ums9620-2h10" ] ; then
	install -m 0777 ${S}/classic/Sony/imx586/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/Sony/imx616/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/Samsung/s5kgw1sp03/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov08a10/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov08a10_back/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov08a10_back_sunny/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov8856_back/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov02a10/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov13855/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov13855_dual/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov8856_front/*.so ${D}${libdir}/
	install -m 0777 ${S}/classic/OmniVision/ov8856_xl_front/*.so ${D}${libdir}/
	fi

}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"


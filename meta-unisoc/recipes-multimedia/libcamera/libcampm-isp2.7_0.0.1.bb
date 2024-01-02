DESCRIPTION = "Unisoc libcamera module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "LGPL-3.0"
SECTION = "lib"
DEPENDS = "libcamcommon libcaminiparser"
RDEPENDS_${PN} = "libcamcommon libcaminiparser"
PV = "0.1"
PR = "r0"
PROVIDES = "libcampm-isp2.7"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

export BOARD="${CAMERA_BOARD}"
export ISP_VER="${TARGET_BOARD_CAMERA_ISP_VERSION}"
export DRV_DIR="${CAMERA_DRIVER_DIR}"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}/
    install -m 0777 ${S}/libcampm_isp2.7.so ${D}${libdir}/
    install -d ${D}${includedir}/libcamera/
    install -m 0777 ${S}/*.h ${D}${includedir}/libcamera/

    if [ ${CAMERA_BOARD} = "sc9863a-1h10" ] ; then
    install -d ${D}/bin/firmware/imx351/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/cfg.* ${D}/bin/firmware/imx351/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/com/* ${D}/bin/firmware/imx351/com/
    install -d ${D}/bin/firmware/imx351/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/cap0/* ${D}/bin/firmware/imx351/cap0/
    install -d ${D}/bin/firmware/imx351/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/cap1/* ${D}/bin/firmware/imx351/cap1/
    install -d ${D}/bin/firmware/imx351/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/prv0/* ${D}/bin/firmware/imx351/prv0/
    install -d ${D}/bin/firmware/imx351/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/prv1/* ${D}/bin/firmware/imx351/prv1/
    install -d ${D}/bin/firmware/imx351/prv2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/prv2/* ${D}/bin/firmware/imx351/prv2/
    install -d ${D}/bin/firmware/imx351/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/video0/* ${D}/bin/firmware/imx351/video0/
    install -d ${D}/bin/firmware/imx351/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/video1/* ${D}/bin/firmware/imx351/video1/
    install -d ${D}/bin/firmware/imx351/video2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/video2/* ${D}/bin/firmware/imx351/video2/
    install -d ${D}/bin/firmware/imx351/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Sony/imx351/other/* ${D}/bin/firmware/imx351/other/

    install -d ${D}/bin/firmware/s5k3p9sx04/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/cfg.* ${D}/bin/firmware/s5k3p9sx04/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/com/* ${D}/bin/firmware/s5k3p9sx04/com/
    install -d ${D}/bin/firmware/s5k3p9sx04/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/cap0/* ${D}/bin/firmware/s5k3p9sx04/cap0/
    install -d ${D}/bin/firmware/s5k3p9sx04/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/cap1/* ${D}/bin/firmware/s5k3p9sx04/cap1/
    install -d ${D}/bin/firmware/s5k3p9sx04/cap2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/cap2/* ${D}/bin/firmware/s5k3p9sx04/cap2/
    install -d ${D}/bin/firmware/s5k3p9sx04/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/prv0/* ${D}/bin/firmware/s5k3p9sx04/prv0/
    install -d ${D}/bin/firmware/s5k3p9sx04/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/prv1/* ${D}/bin/firmware/s5k3p9sx04/prv1/
    install -d ${D}/bin/firmware/s5k3p9sx04/prv2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/prv2/* ${D}/bin/firmware/s5k3p9sx04/prv2/
    install -d ${D}/bin/firmware/s5k3p9sx04/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/video0/* ${D}/bin/firmware/s5k3p9sx04/video0/
    install -d ${D}/bin/firmware/s5k3p9sx04/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/video1/* ${D}/bin/firmware/s5k3p9sx04/video1/
    install -d ${D}/bin/firmware/s5k3p9sx04/video2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/video2/* ${D}/bin/firmware/s5k3p9sx04/video2/
    install -d ${D}/bin/firmware/s5k3p9sx04/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04/other/* ${D}/bin/firmware/s5k3p9sx04/other/

    install -d ${D}/bin/firmware/s5k3p9sx04_ts/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/cfg.* ${D}/bin/firmware/s5k3p9sx04_ts/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/com/* ${D}/bin/firmware/s5k3p9sx04_ts/com/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/cap0/* ${D}/bin/firmware/s5k3p9sx04_ts/cap0/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/cap1/* ${D}/bin/firmware/s5k3p9sx04_ts/cap1/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/prv0/* ${D}/bin/firmware/s5k3p9sx04_ts/prv0/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/prv1/* ${D}/bin/firmware/s5k3p9sx04_ts/prv1/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/video0/* ${D}/bin/firmware/s5k3p9sx04_ts/video0/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/video1/* ${D}/bin/firmware/s5k3p9sx04_ts/video1/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/video2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/video2/* ${D}/bin/firmware/s5k3p9sx04_ts/video2/
    install -d ${D}/bin/firmware/s5k3p9sx04_ts/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Samsung/s5k3p9sx04_ts/other/* ${D}/bin/firmware/s5k3p9sx04_ts/other/

    install -d ${D}/bin/firmware/ov5675/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/cfg.* ${D}/bin/firmware/ov5675/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/com/* ${D}/bin/firmware/ov5675/com/
    install -d ${D}/bin/firmware/ov5675/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/cap0/* ${D}/bin/firmware/ov5675/cap0/
    install -d ${D}/bin/firmware/ov5675/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/cap1/* ${D}/bin/firmware/ov5675/cap1/
    install -d ${D}/bin/firmware/ov5675/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/prv0/* ${D}/bin/firmware/ov5675/prv0/
    install -d ${D}/bin/firmware/ov5675/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/prv1/* ${D}/bin/firmware/ov5675/prv1/
    install -d ${D}/bin/firmware/ov5675/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/video0/* ${D}/bin/firmware/ov5675/video0/
    install -d ${D}/bin/firmware/ov5675/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/video1/* ${D}/bin/firmware/ov5675/video1/
    install -d ${D}/bin/firmware/ov5675/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675/other/* ${D}/bin/firmware/ov5675/other/

    install -d ${D}/bin/firmware/ov5675_dual/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/cfg.* ${D}/bin/firmware/ov5675_dual/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/com/* ${D}/bin/firmware/ov5675_dual/com/
    install -d ${D}/bin/firmware/ov5675_dual/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/cap0/* ${D}/bin/firmware/ov5675_dual/cap0/
    install -d ${D}/bin/firmware/ov5675_dual/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/cap1/* ${D}/bin/firmware/ov5675_dual/cap1/
    install -d ${D}/bin/firmware/ov5675_dual/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/prv0/* ${D}/bin/firmware/ov5675_dual/prv0/
    install -d ${D}/bin/firmware/ov5675_dual/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/prv1/* ${D}/bin/firmware/ov5675_dual/prv1/
    install -d ${D}/bin/firmware/ov5675_dual/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/video0/* ${D}/bin/firmware/ov5675_dual/video0/
    install -d ${D}/bin/firmware/ov5675_dual/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/video1/* ${D}/bin/firmware/ov5675_dual/video1/
    install -d ${D}/bin/firmware/ov5675_dual/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov5675_dual/other/* ${D}/bin/firmware/ov5675_dual/other/

    install -d ${D}/bin/firmware/ov8856_back_ts/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/cfg.* ${D}/bin/firmware/ov8856_back_ts/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/com/* ${D}/bin/firmware/ov8856_back_ts/com/
    install -d ${D}/bin/firmware/ov8856_back_ts/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/cap0/* ${D}/bin/firmware/ov8856_back_ts/cap0/
    install -d ${D}/bin/firmware/ov8856_back_ts/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/cap1/* ${D}/bin/firmware/ov8856_back_ts/cap1/
    install -d ${D}/bin/firmware/ov8856_back_ts/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/prv0/* ${D}/bin/firmware/ov8856_back_ts/prv0/
    install -d ${D}/bin/firmware/ov8856_back_ts/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/prv1/* ${D}/bin/firmware/ov8856_back_ts/prv1/
    install -d ${D}/bin/firmware/ov8856_back_ts/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/video0/* ${D}/bin/firmware/ov8856_back_ts/video0/
    install -d ${D}/bin/firmware/ov8856_back_ts/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/video1/* ${D}/bin/firmware/ov8856_back_ts/video1/
    install -d ${D}/bin/firmware/ov8856_back_ts/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_back_ts/other/* ${D}/bin/firmware/ov8856_back_ts/other/

    install -d ${D}/bin/firmware/ov8856_front_ts/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/cfg.* ${D}/bin/firmware/ov8856_front_ts/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/com/* ${D}/bin/firmware/ov8856_front_ts/com/
    install -d ${D}/bin/firmware/ov8856_front_ts/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/cap0/* ${D}/bin/firmware/ov8856_front_ts/cap0/
    install -d ${D}/bin/firmware/ov8856_front_ts/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/cap1/* ${D}/bin/firmware/ov8856_front_ts/cap1/
    install -d ${D}/bin/firmware/ov8856_front_ts/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/prv0/* ${D}/bin/firmware/ov8856_front_ts/prv0/
    install -d ${D}/bin/firmware/ov8856_front_ts/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/prv1/* ${D}/bin/firmware/ov8856_front_ts/prv1/
    install -d ${D}/bin/firmware/ov8856_front_ts/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/video0/* ${D}/bin/firmware/ov8856_front_ts/video0/
    install -d ${D}/bin/firmware/ov8856_front_ts/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/video1/* ${D}/bin/firmware/ov8856_front_ts/video1/
    install -d ${D}/bin/firmware/ov8856_front_ts/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_front_ts/other/* ${D}/bin/firmware/ov8856_front_ts/other/

    install -d ${D}/bin/firmware/ov8856_shine_back/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/cfg.* ${D}/bin/firmware/ov8856_shine_back/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/com/* ${D}/bin/firmware/ov8856_shine_back/com/
    install -d ${D}/bin/firmware/ov8856_shine_back/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/cap0/* ${D}/bin/firmware/ov8856_shine_back/cap0/
    install -d ${D}/bin/firmware/ov8856_shine_back/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/cap1/* ${D}/bin/firmware/ov8856_shine_back/cap1/
    install -d ${D}/bin/firmware/ov8856_shine_back/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/prv0/* ${D}/bin/firmware/ov8856_shine_back/prv0/
    install -d ${D}/bin/firmware/ov8856_shine_back/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/prv1/* ${D}/bin/firmware/ov8856_shine_back/prv1/
    install -d ${D}/bin/firmware/ov8856_shine_back/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/video0/* ${D}/bin/firmware/ov8856_shine_back/video0/
    install -d ${D}/bin/firmware/ov8856_shine_back/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/video1/* ${D}/bin/firmware/ov8856_shine_back/video1/
    install -d ${D}/bin/firmware/ov8856_shine_back/video2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/video2/* ${D}/bin/firmware/ov8856_shine_back/video2/
    install -d ${D}/bin/firmware/ov8856_shine_back/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_back/other/* ${D}/bin/firmware/ov8856_shine_back/other/

    install -d ${D}/bin/firmware/ov8856_shine_front/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/cfg.* ${D}/bin/firmware/ov8856_shine_front/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/com/* ${D}/bin/firmware/ov8856_shine_front/com/
    install -d ${D}/bin/firmware/ov8856_shine_front/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/cap0/* ${D}/bin/firmware/ov8856_shine_front/cap0/
    install -d ${D}/bin/firmware/ov8856_shine_front/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/cap1/* ${D}/bin/firmware/ov8856_shine_front/cap1/
    install -d ${D}/bin/firmware/ov8856_shine_front/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/prv0/* ${D}/bin/firmware/ov8856_shine_front/prv0/
    install -d ${D}/bin/firmware/ov8856_shine_front/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/prv1/* ${D}/bin/firmware/ov8856_shine_front/prv1/
    install -d ${D}/bin/firmware/ov8856_shine_front/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/video0/* ${D}/bin/firmware/ov8856_shine_front/video0/
    install -d ${D}/bin/firmware/ov8856_shine_front/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/video1/* ${D}/bin/firmware/ov8856_shine_front/video1/
    install -d ${D}/bin/firmware/ov8856_shine_front/video2/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/video2/* ${D}/bin/firmware/ov8856_shine_front/video2/
    install -d ${D}/bin/firmware/ov8856_shine_front/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/OmniVision/ov8856_shine_front/other/* ${D}/bin/firmware/ov8856_shine_front/other/

    install -d ${D}/bin/firmware/gc08a3/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/cfg.* ${D}/bin/firmware/gc08a3/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/com/* ${D}/bin/firmware/gc08a3/com/
    install -d ${D}/bin/firmware/gc08a3/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/cap0/* ${D}/bin/firmware/gc08a3/cap0/
    install -d ${D}/bin/firmware/gc08a3/cap1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/cap1/* ${D}/bin/firmware/gc08a3/cap1/
    install -d ${D}/bin/firmware/gc08a3/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/prv0/* ${D}/bin/firmware/gc08a3/prv0/
    install -d ${D}/bin/firmware/gc08a3/prv1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/prv1/* ${D}/bin/firmware/gc08a3/prv1/
    install -d ${D}/bin/firmware/gc08a3/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/video0/* ${D}/bin/firmware/gc08a3/video0/
    install -d ${D}/bin/firmware/gc08a3/video1/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/video1/* ${D}/bin/firmware/gc08a3/video1/
    install -d ${D}/bin/firmware/gc08a3/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc08a3/other/* ${D}/bin/firmware/gc08a3/other/

    install -d ${D}/bin/firmware/gc5035/com/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc5035/cfg.* ${D}/bin/firmware/gc5035/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc5035/com/* ${D}/bin/firmware/gc5035/com/
    install -d ${D}/bin/firmware/gc5035/cap0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc5035/cap0/* ${D}/bin/firmware/gc5035/cap0/
    install -d ${D}/bin/firmware/gc5035/prv0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc5035/prv0/* ${D}/bin/firmware/gc5035/prv0/
    install -d ${D}/bin/firmware/gc5035/video0/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc5035/video0/* ${D}/bin/firmware/gc5035/video0/
    install -d ${D}/bin/firmware/gc5035/other/
    install -m 0777 ${THISDIR}/files/its_param/sharkl3/Galaxycore/gc5035/other/* ${D}/bin/firmware/gc5035/other/
    fi

    if [ ${CAMERA_BOARD} = "ums9620-2h10" ] ; then
    install -d ${D}/bin/firmware/ov08a10/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/cfg.* ${D}/bin/firmware/ov08a10/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/com/* ${D}/bin/firmware/ov08a10/com/
    install -d ${D}/bin/firmware/ov08a10/cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/cap0/* ${D}/bin/firmware/ov08a10/cap0/
    install -d ${D}/bin/firmware/ov08a10/cap0_flash/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/cap0_flash/* ${D}/bin/firmware/ov08a10/cap0_flash/
    install -d ${D}/bin/firmware/ov08a10/cap0_hdr/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/cap0_hdr/* ${D}/bin/firmware/ov08a10/cap0_hdr/
    install -d ${D}/bin/firmware/ov08a10/cap0_zoom/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/cap0_zoom/* ${D}/bin/firmware/ov08a10/cap0_zoom/
    install -d ${D}/bin/firmware/ov08a10/cap1/
    install -d ${D}/bin/firmware/ov08a10/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/prv0/* ${D}/bin/firmware/ov08a10/prv0/
    install -d ${D}/bin/firmware/ov08a10/prv1/
    install -d ${D}/bin/firmware/ov08a10/qq_cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/qq_cap0/* ${D}/bin/firmware/ov08a10/qq_cap0/
    install -d ${D}/bin/firmware/ov08a10/video0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/video0/* ${D}/bin/firmware/ov08a10/video0/
    install -d ${D}/bin/firmware/ov08a10/video1/
    install -d ${D}/bin/firmware/ov08a10/video2/
    install -d ${D}/bin/firmware/ov08a10/wechat_cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/wechat_cap0/* ${D}/bin/firmware/ov08a10/wechat_cap0/
    install -d ${D}/bin/firmware/ov08a10/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10/other/* ${D}/bin/firmware/ov08a10/other/

    install -d ${D}/bin/firmware/ov08a10_back_sunny/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_sunny/cfg.* ${D}/bin/firmware/ov08a10_back_sunny/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_sunny/com/* ${D}/bin/firmware/ov08a10_back_sunny/com/
    install -d ${D}/bin/firmware/ov08a10_back_sunny/cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_sunny/cap0/* ${D}/bin/firmware/ov08a10_back_sunny/cap0/
    install -d ${D}/bin/firmware/ov08a10_back_sunny/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_sunny/prv0/* ${D}/bin/firmware/ov08a10_back_sunny/prv0/
    install -d ${D}/bin/firmware/ov08a10_back_sunny/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_sunny/other/* ${D}/bin/firmware/ov08a10_back_sunny/other/
    install -d ${D}/bin/firmware/ov08a10_back_sunny/video0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_sunny/video0/* ${D}/bin/firmware/ov08a10_back_sunny/video0/

    install -d ${D}/bin/firmware/ov08a10_back_tele/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_tele/cfg.* ${D}/bin/firmware/ov08a10_back_tele/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_tele/com/* ${D}/bin/firmware/ov08a10_back_tele/com/
    install -d ${D}/bin/firmware/ov08a10_back_tele/cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_tele/cap0/* ${D}/bin/firmware/ov08a10_back_tele/cap0/
    install -d ${D}/bin/firmware/ov08a10_back_tele/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_tele/prv0/* ${D}/bin/firmware/ov08a10_back_tele/prv0/
    install -d ${D}/bin/firmware/ov08a10_back_tele/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_tele/other/* ${D}/bin/firmware/ov08a10_back_tele/other/
    install -d ${D}/bin/firmware/ov08a10_back_tele/video0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov08a10_back_tele/video0/* ${D}/bin/firmware/ov08a10_back_tele/video0/

    install -d ${D}/bin/firmware/ov8856_back_ultrawide/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/cfg.* ${D}/bin/firmware/ov8856_back_ultrawide/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/com/* ${D}/bin/firmware/ov8856_back_ultrawide/com/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/cap0/* ${D}/bin/firmware/ov8856_back_ultrawide/cap0/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/cap0_flash/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/cap0_flash/* ${D}/bin/firmware/ov8856_back_ultrawide/cap0_flash/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/cap0_hdr/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/cap0_hdr/* ${D}/bin/firmware/ov8856_back_ultrawide/cap0_hdr/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/cap1/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/flash/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/flash/* ${D}/bin/firmware/ov8856_back_ultrawide/flash/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/hdr/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/hdr/* ${D}/bin/firmware/ov8856_back_ultrawide/hdr/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/other/* ${D}/bin/firmware/ov8856_back_ultrawide/other/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/prv0/* ${D}/bin/firmware/ov8856_back_ultrawide/prv0/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/prv1/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/qq_cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/qq_cap0/* ${D}/bin/firmware/ov8856_back_ultrawide/qq_cap0/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/qq_video0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/qq_video0/* ${D}/bin/firmware/ov8856_back_ultrawide/qq_video0/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/video0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/video0/* ${D}/bin/firmware/ov8856_back_ultrawide/video0/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/video1/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/video2/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/wechat_cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/wechat_cap0/* ${D}/bin/firmware/ov8856_back_ultrawide/wechat_cap0/
    install -d ${D}/bin/firmware/ov8856_back_ultrawide/wechat_video0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_back_ultrawide/wechat_video0/* ${D}/bin/firmware/ov8856_back_ultrawide/wechat_video0/

	install -d ${D}/bin/firmware/ov8856_front_dual/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_front_dual/cfg.* ${D}/bin/firmware/ov8856_front_dual/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_front_dual/com/* ${D}/bin/firmware/ov8856_front_dual/com/
	install -d ${D}/bin/firmware/ov8856_front_dual/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov8856_front_dual/other/* ${D}/bin/firmware/ov8856_front_dual/other/
	
	install -d ${D}/bin/firmware/ov13855_back_main/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_main/cfg.* ${D}/bin/firmware/ov13855_back_main/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_main/com/* ${D}/bin/firmware/ov13855_back_main/com/
	install -d ${D}/bin/firmware/ov13855_back_main/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_main/other/* ${D}/bin/firmware/ov13855_back_main/other/
	install -d ${D}/bin/firmware/ov13855_back_main/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_main/prv0/* ${D}/bin/firmware/ov13855_back_main/prv0/

	install -d ${D}/bin/firmware/ov13855_back_slave/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_slave/cfg.* ${D}/bin/firmware/ov13855_back_slave/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_slave/com/* ${D}/bin/firmware/ov13855_back_slave/com/
	install -d ${D}/bin/firmware/ov13855_back_slave/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_slave/other/* ${D}/bin/firmware/ov13855_back_slave/other/
	install -d ${D}/bin/firmware/ov13855_back_slave/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/OmniVision/ov13855_back_slave/prv0/* ${D}/bin/firmware/ov13855_back_slave/prv0/

    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/cfg.* ${D}/bin/firmware/s5kgw1sp03_back_main/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/com/* ${D}/bin/firmware/s5kgw1sp03_back_main/com/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/cap0/* ${D}/bin/firmware/s5kgw1sp03_back_main/cap0/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/cap1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/cap1/* ${D}/bin/firmware/s5kgw1sp03_back_main/cap1/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/cap1_flash/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/cap1_flash/* ${D}/bin/firmware/s5kgw1sp03_back_main/cap1_flash/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/other/* ${D}/bin/firmware/s5kgw1sp03_back_main/other/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/prv0/* ${D}/bin/firmware/s5kgw1sp03_back_main/prv0/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/prv1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/prv1/* ${D}/bin/firmware/s5kgw1sp03_back_main/prv1/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/video1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/video1/* ${D}/bin/firmware/s5kgw1sp03_back_main/video1/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/video2/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/video2/* ${D}/bin/firmware/s5kgw1sp03_back_main/video2/
    install -d ${D}/bin/firmware/s5kgw1sp03_back_main/video3/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Samsung/s5kgw1sp03_back_main/video3/* ${D}/bin/firmware/s5kgw1sp03_back_main/video3/

    install -d ${D}/bin/firmware/imx586/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/cfg.* ${D}/bin/firmware/imx586/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/com/* ${D}/bin/firmware/imx586/com/
    install -d ${D}/bin/firmware/imx586/cap0/
    install -d ${D}/bin/firmware/imx586/cap1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/cap1/* ${D}/bin/firmware/imx586/cap1/
    install -d ${D}/bin/firmware/imx586/cap1_flash/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/cap1_flash/* ${D}/bin/firmware/imx586/cap1_flash/
    install -d ${D}/bin/firmware/imx586/cap1_hdr/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/cap1_hdr/* ${D}/bin/firmware/imx586/cap1_hdr/
    install -d ${D}/bin/firmware/imx586/cap1_zoom/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/cap1_zoom/* ${D}/bin/firmware/imx586/cap1_zoom/
    install -d ${D}/bin/firmware/imx586/prv0/
    install -d ${D}/bin/firmware/imx586/prv1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/prv1/* ${D}/bin/firmware/imx586/prv1/
    install -d ${D}/bin/firmware/imx586/prv1_zoom/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/prv1_zoom/* ${D}/bin/firmware/imx586/prv1_zoom/
    install -d ${D}/bin/firmware/imx586/qq/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/qq/* ${D}/bin/firmware/imx586/qq/
    install -d ${D}/bin/firmware/imx586/video0/
    install -d ${D}/bin/firmware/imx586/video1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/video1/* ${D}/bin/firmware/imx586/video1/
    install -d ${D}/bin/firmware/imx586/video1_zoom/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/video1_zoom/* ${D}/bin/firmware/imx586/video1_zoom/
    install -d ${D}/bin/firmware/imx586/video2/
    install -d ${D}/bin/firmware/imx586/wechat/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/wechat/* ${D}/bin/firmware/imx586/wechat/
    install -d ${D}/bin/firmware/imx586/zoom/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/zoom/* ${D}/bin/firmware/imx586/zoom/
    install -d ${D}/bin/firmware/imx586/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx586/other/* ${D}/bin/firmware/imx586/other/

    install -d ${D}/bin/firmware/imx616/com/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/cfg.* ${D}/bin/firmware/imx616/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/com/* ${D}/bin/firmware/imx616/com/
    install -d ${D}/bin/firmware/imx616/cap0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/cap0/* ${D}/bin/firmware/imx616/cap0/
    install -d ${D}/bin/firmware/imx616/cap1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/cap1/* ${D}/bin/firmware/imx616/cap1/
    install -d ${D}/bin/firmware/imx616/flash/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/flash/* ${D}/bin/firmware/imx616/flash/
    install -d ${D}/bin/firmware/imx616/hdr/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/hdr/* ${D}/bin/firmware/imx616/hdr/
    install -d ${D}/bin/firmware/imx616/prv0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/prv0/* ${D}/bin/firmware/imx616/prv0/
    install -d ${D}/bin/firmware/imx616/prv1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/prv1/* ${D}/bin/firmware/imx616/prv1/
    install -d ${D}/bin/firmware/imx616/qq/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/qq/* ${D}/bin/firmware/imx616/qq/
    install -d ${D}/bin/firmware/imx616/qq_face/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/qq_face/* ${D}/bin/firmware/imx616/qq_face/
    install -d ${D}/bin/firmware/imx616/video0/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/video0/* ${D}/bin/firmware/imx616/video0/
    install -d ${D}/bin/firmware/imx616/video1/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/video1/* ${D}/bin/firmware/imx616/video1/
    install -d ${D}/bin/firmware/imx616/video2/
    install -d ${D}/bin/firmware/imx616/wechat/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/wechat/* ${D}/bin/firmware/imx616/wechat/
    install -d ${D}/bin/firmware/imx616/wechat_face/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/wechat_face/* ${D}/bin/firmware/imx616/wechat_face/
    install -d ${D}/bin/firmware/imx616/other/
    install -m 0777 ${THISDIR}/files/its_param/qogirn6pro/Sony/imx616/other/* ${D}/bin/firmware/imx616/other/
    fi
}

FILES_${PN} += "${libdir}/libcampm_isp2.7.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

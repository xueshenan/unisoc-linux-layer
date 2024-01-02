INHERIT += "unisoc_externalsrc"
OEROOT := "${@os.path.abspath(os.path.dirname(d.getVar('FILE', True)))}/../../../../.."

EXTERNALSRC_pn-linux-unisoc-4.4 = "${OEROOT}/source/kernel/kernel4.4/"
EXTERNALSRC_pn-linux-unisoc-4.14 = "${OEROOT}/source/kernel/kernel4.14/"
EXTERNALSRC_pn-linux-unisoc-5.4 = "${OEROOT}/source/kernel/kernel5.4/"
EXTERNALSRC_pn-linux-unisoc-5.15 = "${OEROOT}/source/kernel/kernel5.15/"

EXTERNALSRC_pn-u-boot15 = "${OEROOT}/source/bsp/u-boot15"
EXTERNALSRC_pn-u-boot22 = "${OEROOT}/source/bsp/u-boot22"

EXTERNALSRC_pn-linux-unisoc-3.10 = "${OEROOT}/source/kernel/kernel3.10/"
EXTERNALSRC_pn-u-boot64 = "${OEROOT}/source/bsp/u-boot64/"
EXTERNALSRC_pn-u-boot-2010.12 = "${OEROOT}/source/bsp/u-boot_shark_mp/"

EXTERNALSRC_pn-linux-unisoc-4.14 = "${OEROOT}/source/kernel/kernel4.14/"

EXTERNALSRC_pn-dtbtool = "${OEROOT}/source/devtools/dtbtool/"
EXTERNALSRC_pn-dtbtool-native = "${OEROOT}/source/devtools/dtbtool/"
EXTERNALSRC_pn-mkbootimg = "${OEROOT}/source/devtools/mkbootimg/"
EXTERNALSRC_pn-mkbootimg-native = "${OEROOT}/source/devtools/mkbootimg/"
EXTERNALSRC_pn-pm = "${OEROOT}/source/unisoc/powermanager/pm"
EXTERNALSRC_pn-pm-demo = "${OEROOT}/source/unisoc/powermanager/pm"
EXTERNALSRC_pn-suspendblocker = "${OEROOT}/source/unisoc/powermanager/powerd/suspendblocker"
EXTERNALSRC_pn-powerd = "${OEROOT}/source/unisoc/powermanager/powerd"
EXTERNALSRC_pn-lookat = "${OEROOT}/source/devtools/lookat"
EXTERNALSRC_pn-memtester = "${OEROOT}/source/devtools/memtester"
EXTERNALSRC_pn-costmem = "${OEROOT}/source/devtools/costmem"
EXTERNALSRC_pn-mcp-gen-native = "${OEROOT}/source/devtools/mcp_gen"
EXTERNALSRC_pn-chipram = "${OEROOT}/source/bsp/chipram"
EXTERNALSRC_pn-ofono = "${OEROOT}/source/unisoc/connectivity/telephony/ofono/ofono-1.25"
EXTERNALSRC_pn-connman = "${OEROOT}/source/unisoc/connman"
EXTERNALSRC_pn-perflink = "${OEROOT}/source/unisoc/perflink"
EXTERNALSRC_pn-thermald = "${OEROOT}/source/unisoc/modules/thermal"
EXTERNALSRC_pn-charge = "${OEROOT}/source/unisoc/modules/charge"
EXTERNALSRC_pn-atrouter = "${OEROOT}/source/unisoc/connectivity/telephony/atrouter"
EXTERNALSRC_pn-libatcommon = "${OEROOT}/source/unisoc/connectivity/telephony/atrouter/lib/atcommonlib"
EXTERNALSRC_pn-libcptransport = "${OEROOT}/source/unisoc/connectivity/telephony/atrouter/lib/cptransportlib"
EXTERNALSRC_pn-libyoctominiap = "${OEROOT}/source/unisoc/connectivity/telephony/atrouter/lib/miniapservicelib"
EXTERNALSRC_pn-liborcaled = "${OEROOT}/source/unisoc/rgbled/orca_led/led-lib"
EXTERNALSRC_pn-led-deamon = "${OEROOT}/source/unisoc/rgbled/orca_led/led-deamon"
#EXTERNALSRC_pn-getevent = "${OEROOT}/source/devtools/getevent"
EXTERNALSRC_pn-modem-utils = "${OEROOT}/source/unisoc/connectivity/modem/modem-utils"
EXTERNALSRC_pn-libtrusty = "${OEROOT}/source/unisoc/trusty/libtrusty"
EXTERNALSRC_pn-libteec = "${OEROOT}/source/unisoc/trusty/libteec/unclinux"
EXTERNALSRC_pn-service-plugins = "${OEROOT}/source/unisoc/modules/service-plugins"
EXTERNALSRC_pn-rgbled = "${OEROOT}/source/unisoc/modules/rgbled"
EXTERNALSRC_pn-bt-tool = "${OEROOT}/source/unisoc/demo/bt-tool"
EXTERNALSRC_pn-rgbled-demo = "${OEROOT}/source/unisoc/modules/rgbled"
EXTERNALSRC_pn-keyevent = "${OEROOT}/source/unisoc/modules/keyevent"
EXTERNALSRC_pn-keyevent-deamon = "${OEROOT}/source/unisoc/modules/keyevent/orca-keyevent"
EXTERNALSRC_pn-libdynamicproduction = "${OEROOT}/source/unisoc/trusty/production/libproduction_cmd"
EXTERNALSRC_pn-libteeproduction = "${OEROOT}/source/unisoc/trusty/production/production"
EXTERNALSRC_pn-libcheckx = "${OEROOT}/source/unisoc/trusty/production/factory/checkKeybox"
EXTERNALSRC_pn-libgetuid = "${OEROOT}/source/unisoc/trusty/production/factory/getuid"
EXTERNALSRC_pn-libkernelbootcp = "${OEROOT}/source/unisoc/trusty/kernelbootcp_ca/"
EXTERNALSRC_pn-sprdstorage = "${OEROOT}/source/unisoc/trusty/storage/proxy"
EXTERNALSRC_pn-libsprdtrustystorage = "${OEROOT}/source/unisoc/trusty/storage/lib"
EXTERNALSRC_pn-tsupplicant = "${OEROOT}/source/unisoc/trusty/tsupplicant"
EXTERNALSRC_pn-fsckmsdos = "${OEROOT}/source/unisoc/modules/fs_tools/fsckmsdos"
EXTERNALSRC_pn-imgtec= "${OEROOT}/source/unisoc/ai/npu/imgtec"
EXTERNALSRC_pn-wlan-combo= "${OEROOT}/source/unisoc/connectivity/wcn/wlan/wlan_combo"
EXTERNALSRC_pn-wlan-sc2355= "${OEROOT}/source/unisoc/connectivity/wlan/sc2355"
EXTERNALSRC_pn-iwnpi= "${OEROOT}/source/unisoc/connectivity/wlan/iwnpi"
EXTERNALSRC_pn-libwifieut= "${OEROOT}/source/unisoc/connectivity/wlan/libwifieut"
EXTERNALSRC_pn-libautotestwifi = "${OEROOT}/source/unisoc/connectivity/wcn/libautotestwifi"

# FOR NPI
EXTERNALSRC_pn-engpc = "${OEROOT}/source/unisoc/modules/engpc"
EXTERNALSRC_pn-engpc-adapt = "${OEROOT}/source/unisoc/modules/engpc/server/adapt"
EXTERNALSRC_pn-engpc-ctl = "${OEROOT}/source/unisoc/modules/engpc/server/ctlr"
EXTERNALSRC_pn-libapcomm = "${OEROOT}/source/unisoc/modules/engpc/modules/libapcomm"
EXTERNALSRC_pn-libmiscdata = "${OEROOT}/source/unisoc/modules/engpc/modules/libmiscdata"
EXTERNALSRC_pn-librebootcmd = "${OEROOT}/source/unisoc/modules/engpc/modules/librebootcmd"
EXTERNALSRC_pn-libftmode = "${OEROOT}/source/unisoc/modules/engpc/modules/libftmode"

EXTERNALSRC_pn-libpartinfo = "${OEROOT}/source/unisoc/modules/npi_lib/libpartinfo"
EXTERNALSRC_pn-libtcard = "${OEROOT}/source/unisoc/modules/npi_lib/libtcard"
EXTERNALSRC_pn-librtc = "${OEROOT}/source/unisoc/modules/npi_lib/librtc"
EXTERNALSRC_pn-libgpio = "${OEROOT}/source/unisoc/modules/npi_lib/libgpio"
EXTERNALSRC_pn-libkpd = "${OEROOT}/source/unisoc/modules/npi_lib/libkpd"
EXTERNALSRC_pn-liblkv = "${OEROOT}/source/unisoc/modules/npi_lib/liblkv"
EXTERNALSRC_pn-libapopt = "${OEROOT}/source/unisoc/modules/npi_lib/libapopt"
EXTERNALSRC_pn-libfactoryreset = "${OEROOT}/source/unisoc/modules/npi_lib/libfactoryreset"
EXTERNALSRC_pn-libapdeepsleep = "${OEROOT}/source/unisoc/modules/npi_lib/libapdeepsleep"
EXTERNALSRC_pn-libregulator = "${OEROOT}/source/unisoc/modules/npi_lib/libregulator"
EXTERNALSRC_pn-libchipid = "${OEROOT}/source/unisoc/modules/npi_lib/libchipid"
EXTERNALSRC_pn-libsocid = "${OEROOT}/source/unisoc/modules/npi_lib/libsocid"
EXTERNALSRC_pn-libcharge = "${OEROOT}/source/unisoc/modules/npi_lib/libcharge"
EXTERNALSRC_pn-libtsensor = "${OEROOT}/source/unisoc/modules/npi_lib/libtsensor"
EXTERNALSRC_pn-libnefuse = "${OEROOT}/source/unisoc/modules/npi_lib/libnefuse"
EXTERNALSRC_pn-libtsxdata = "${OEROOT}/source/unisoc/modules/npi_lib/libtsxdata"
EXTERNALSRC_pn-liborca = "${OEROOT}/source/unisoc/modules/npi_lib/liborca"
EXTERNALSRC_pn-libtp = "${OEROOT}/source/unisoc/modules/npi_lib/libtp"
EXTERNALSRC_pn-libadc = "${OEROOT}/source/unisoc/modules/npi_lib/libadc"
EXTERNALSRC_pn-libetb = "${OEROOT}/source/unisoc/modules/npi_lib/libetb"
EXTERNALSRC_pn-libusbtypec = "${OEROOT}/source/unisoc/modules/npi_lib/libusbtypec"
EXTERNALSRC_pn-liblcdnpi = "${OEROOT}/source/unisoc/modules/npi_lib/liblcdnpi"

EXTERNALSRC_pn-nativemmi = "${OEROOT}/source/unisoc/modules/nativemmi"
# END

# FOR UNIRLOG
EXTERNALSRC_pn-unirlog-service = "${OEROOT}/source/unisoc/modules/unirlog/unirlog-service"
EXTERNALSRC_pn-unirlog-service-test = "${OEROOT}/source/unisoc/modules/unirlog/unirlog-service/test"
EXTERNALSRC_pn-libunirlogsocket = "${OEROOT}/source/unisoc/modules/unirlog/unirlog-socket"
EXTERNALSRC_pn-unirlog-ctl = "${OEROOT}/source/unisoc/modules/unirlog/unirlog-ctl"
EXTERNALSRC_pn-unirlog-dbus = "${OEROOT}/source/unisoc/modules/unirlog/unirlog-dbus"
EXTERNALSRC_pn-unirlog-manager = "${OEROOT}/source/unisoc/modules/unirlog/unirlog-manager"
# END

# FOR libcamera
EXTERNALSRC_pn-libcaminiparser= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/libcaminiparser"
EXTERNALSRC_pn-libcamcommon= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/common"
EXTERNALSRC_pn-libcamv4l2yuv2rgb= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/utils/yuv2rgb"
EXTERNALSRC_pn-libcamxmlparser= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/utils/camxmlparser"
EXTERNALSRC_pn-libcamoem= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/oem2v6"
EXTERNALSRC_pn-flashtest= "${OEROOT}/source/unisoc/test/flashtest"
EXTERNALSRC_pn-libcamv4l2adapter= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/tool/v4l2camera"
EXTERNALSRC_pn-v4l2camera = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/tool/v4l2camera"
EXTERNALSRC_pn-sprd-camsys-pw-domain = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/power"
EXTERNALSRC_pn-sprd-camera = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/core"
EXTERNALSRC_pn-sprd-sensor = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/sensor"
EXTERNALSRC_pn-sprd-cpp = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/cpp"
EXTERNALSRC_pn-sprd-flash-drv = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/flash/flash_drv"
EXTERNALSRC_pn-flash-ic-ocp8137 = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/flash/flash_ic"
EXTERNALSRC_pn-flash-ic-sc2721s = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/flash/flash_ic"
EXTERNALSRC_pn-flash-ic-sc2703s = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/kernel_driver/make/flash/flash_ic"
EXTERNALSRC_pn-libispalg= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/ispalg"
EXTERNALSRC_pn-libpss-isp2.7= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/pss/isp2.7"
EXTERNALSRC_pn-libcampmloader= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/pss/pmloader"
EXTERNALSRC_pn-libcampm-isp2.7= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/pss/isp2.7/param_manager"
EXTERNALSRC_pn-libcamps= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/pss/paramsdk"
EXTERNALSRC_pn-libcambr-isp2.7= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/pss/isp2.7/bridge"
EXTERNALSRC_pn-libcamsensor= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/sensor"
EXTERNALSRC_pn-libcam-af-drv= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/sensor/af_drv"
EXTERNALSRC_pn-libcam-otp-parser= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/sensor/otp_parser"
EXTERNALSRC_pn-libcam-otp-drv= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/sensor/otp_drv"
EXTERNALSRC_pn-libcam-sensor-drv= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/sensor/sensor_drv"
EXTERNALSRC_pn-libcppdrv= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/cpp"
EXTERNALSRC_pn-unittest= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/UT"
EXTERNALSRC_pn-libcamfactorytest= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/tool/mmi_test"
EXTERNALSRC_pn-libcamcalitest= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/tool/baseband_autotester_camera"
EXTERNALSRC_pn-isptuning= "${OEROOT}/source/unisoc/multimedia/camera/libcamera/tool/isp_tool"
# END
EXTERNALSRC_pn-libvdspservice= "${OEROOT}/source/unisoc/multimedia/vdsp/vendor/make/libvdspservice"
EXTERNALSRC_pn-dspclient= "${OEROOT}/source/unisoc/multimedia/vdsp/vendor/make/dspclient"
EXTERNALSRC_pn-sprd-vdsp= "${OEROOT}/source/unisoc/multimedia/vdsp/kernel_driver/make"

SRCTREECOVEREDTASKS ?= "do_unpack do_fetch"

# For recovery
EXTERNALSRC_pn-linux-unisoc-4.4-recovery = "${OEROOT}/source/kernel/kernel4.4/"
EXTERNALSRC_pn-linux-unisoc-3.10-recovery = "${OEROOT}/source/kernel/kernel3.10/"
EXTERNALSRC_pn-linux-unisoc-4.14-recovery = "${OEROOT}/source/kernel/kernel4.14/"
EXTERNALSRC_pn-linux-unisoc-5.4-recovery = "${OEROOT}/source/kernel/kernel5.4/"
EXTERNALSRC_pn-linux-unisoc-5.15-recovery = "${OEROOT}/source/kernel/kernel5.15/"
EXTERNALSRC_pn-nvmerge = "${OEROOT}/source/unisoc/update/ota/nvmerge"
EXTERNALSRC_pn-nvsave = "${OEROOT}/source/unisoc/update/ota/nvsave"
EXTERNALSRC_pn-nvstore = "${OEROOT}/source/unisoc/update/ota/nvstore"
EXTERNALSRC_pn-otaarith = "${OEROOT}/source/unisoc/update/ota/otaarith"
EXTERNALSRC_pn-swupdate-support = "${OEROOT}/source/unisoc/update/ota/swupdate_support"
EXTERNALSRC_pn-signature-key = "${OEROOT}/source/unisoc/update/ota/pack/signature-key"
EXTERNALSRC_pn-ota-adapter = "${OEROOT}/source/unisoc/update/ota_adapter"

EXTERNALSRC_pn-bsdiff = "${OEROOT}/source/unisoc/update/ota/bsdiff"
EXTERNALSRC_pn-bsdiff-native = "${OEROOT}/source/unisoc/update/ota/bsdiff"
EXTERNALSRC_pn-aprd = "${OEROOT}/source/unisoc/aprd"

# For video codecs
EXTERNALSRC_pn-libmemion = "${OEROOT}/source/unisoc/modules/libmemion/"
# OMX-Core
EXTERNALSRC_pn-libsprdomxcore = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx_core/"
# AVC
# EXTERNALSRC_pn-libomxavcdechwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/avc_h264_mix/dec/hw/"
# EXTERNALSRC_pn-libomxavcdecswsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/avc_h264_mix/dec/sw/"
# EXTERNALSRC_pn-libomxavcenchwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/avc_h264_mix/enc/hw/"
EXTERNALSRC_pn-libstagefrightsprdh264dec = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/avc/dec/"
EXTERNALSRC_pn-libstagefrightsprdh264enc = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/avc/enc/"
# HEVC
# EXTERNALSRC_pn-libomxhevcdechwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/hevc_h265_mix/dec/hw/"
# EXTERNALSRC_pn-libomxhevcenchwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/hevc_h265_mix/enc/hw/"
EXTERNALSRC_pn-libstagefrightsprdh265dec = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/hevc/dec/"
EXTERNALSRC_pn-libstagefrightsprdh265enc = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/hevc/enc/"
# MPEG4/H263
# EXTERNALSRC_pn-libomxm4vh263dechwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/m4v_h263_mix/dec/hw/"
# EXTERNALSRC_pn-libomxm4vh263decswsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/m4v_h263_mix/dec/sw/"
# EXTERNALSRC_pn-libomxm4vh263enchwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/m4v_h263_mix/enc/hw/"
# EXTERNALSRC_pn-libomxm4vh263encswsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/m4v_h263_mix/enc/sw/"
EXTERNALSRC_pn-libstagefrightsprdmpeg4dec = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/m4v_h263/dec/"
EXTERNALSRC_pn-libstagefrightsprdmpeg4enc = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/m4v_h263/enc/"
# VPX
# EXTERNALSRC_pn-libomxvpxdechwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/vpx_mix/dec/hw/"
# EXTERNALSRC_pn-libomxvpxenchwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/vpx_mix/enc/hw/"
EXTERNALSRC_pn-libstagefrightsprdvpxdec = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/vpx/dec/"
# VP9
# EXTERNALSRC_pn-libomxvp9dechwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/vp9_mix/dec/hw/"
# EXTERNALSRC_pn-libomxvp9enchwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/vp9_mix/enc/hw/"
EXTERNALSRC_pn-libstagefrightsprdvp9dec = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/vp9/dec/"
EXTERNALSRC_pn-libstagefrightsprdvp9enc = "${OEROOT}/source/unisoc/multimedia/video/v2/Yocto/omx-components/video/vp9/enc/"
# JPG
# EXTERNALSRC_pn-libjpegencswsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/jpeg/sw/enc/"
# EXTERNALSRC_pn-libjpeghwsprd = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/jpeg/hw/"
# bin test
# EXTERNALSRC_pn-utestjpegencsw = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/jpeg/sw/utest/"
EXTERNALSRC_pn-utestvspavch264dec = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/avc_h264/dec/"
EXTERNALSRC_pn-utestvspavch264enc = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/avc_h264/enc/"
EXTERNALSRC_pn-utestvsphevch265dec = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/hevc_h265/dec/"
EXTERNALSRC_pn-utestvsphevch265enc = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/hevc_h265/enc/"
EXTERNALSRC_pn-utestvspm4vh263dec = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/m4v_h263/dec/"
EXTERNALSRC_pn-utestvspm4vh263enc = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/m4v_h263/enc/"
EXTERNALSRC_pn-utestvspvpxdec = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/vpx/dec/"
EXTERNALSRC_pn-utestvspvp8enc = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/vp8/enc/"
EXTERNALSRC_pn-utestvspvp9dec = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/vp9/dec/"
EXTERNALSRC_pn-utestvspvp9enc = "${OEROOT}/source/unisoc/multimedia/sprd_codecs/utest_vsp/vp9/enc/"
EXTERNALSRC_pn-utestvideodec = "${OEROOT}/source/unisoc/multimedia/video/test/gtest/dec/"
EXTERNALSRC_pn-utestvideoenc = "${OEROOT}/source/unisoc/multimedia/video/test/gtest/enc/"

# For GPU
EXTERNALSRC_pn-midgard = "${OEROOT}/source/unisoc/gpu/midgard/kernel"
EXTERNALSRC_pn-libmidgard = "${OEROOT}/source/unisoc/gpu/midgard/user"
EXTERNALSRC_pn-natt = "${OEROOT}/source/unisoc/gpu/natt/kernel"
EXTERNALSRC_pn-libnatt = "${OEROOT}/source/unisoc/gpu/natt/user"
EXTERNALSRC_pn-rogue-km = "${OEROOT}/source/unisoc/gpu/rogue/km"
EXTERNALSRC_pn-rogue-um = "${OEROOT}/source/unisoc/gpu/rogue/um"
EXTERNALSRC_pn-rogue-vz-km = "${OEROOT}/source/unisoc/gpu/rogue-vz/km"
EXTERNALSRC_pn-rogue-vz-um = "${OEROOT}/source/unisoc/gpu/rogue-vz/um"

# bluetooth
EXTERNALSRC_pn-libbt = "${OEROOT}/source/unisoc/connectivity/wcn/libbt"
EXTERNALSRC_pn-hciattach = "${OEROOT}/source/unisoc/connectivity/wcn/hciattach"

# for xen, xen-tools
EXTERNALSRC_pn-xen = "${OEROOT}/source/unisoc/xen"
EXTERNALSRC_BUILD_pn-xen = "${OEROOT}/source/unisoc/xen"
EXTERNALSRC_pn-xen-tools = "${OEROOT}/source/unisoc/xen"
EXTERNALSRC_BUILD_pn-xen-tools = "${OEROOT}/source/unisoc/xen"

# for sleepwakeup-tool
EXTERNALSRC_pn-sleepwakeup = "${OEROOT}/source/unisoc/powermanager/powerd/sleepwakeup"

# for batterymanager
EXTERNALSRC_pn-batterymanager = "${OEROOT}/source/unisoc/powermanager/powerd/batterymanager"
EXTERNALSRC_pn-libuevent = "${OEROOT}/source/unisoc/modules/components/libuevent"
EXTERNALSRC_pn-libsessionqueue = "${OEROOT}/source/unisoc/modules/components/libsessionqueue"

# for powermanager
EXTERNALSRC_pn-powermanager = "${OEROOT}/source/unisoc/powerfw/powermanager"

#bt factory test
EXTERNALSRC_pn-btsuite = "${OEROOT}/source/unisoc/connectivity/wcn/marlin2/bttest/btsuite"
EXTERNALSRC_pn-libbteut = "${OEROOT}/source/unisoc/connectivity/wcn/marlin2/bttest/libbteut"
EXTERNALSRC_pn-libengbt = "${OEROOT}/source/unisoc/connectivity/wcn/marlin2/bttest/libengbt"

#wifi-sc2332(marlin2) factory test
EXTERNALSRC_pn-iwnpi-sc2332 = "${OEROOT}/source/unisoc/connectivity/wcn/iwnpi/sc2332"
EXTERNALSRC_pn-libwifieut-sc2332 = "${OEROOT}/source/unisoc/connectivity/wcn/iwnpi/sc2332/libwifieut"

#wifi-sc2355(marlin3) factory test
EXTERNALSRC_pn-iwnpi-sc2355 = "${OEROOT}/source/unisoc/connectivity/wcn/iwnpi/sc2355"
EXTERNALSRC_pn-libwifieut-sc2355 = "${OEROOT}/source/unisoc/connectivity/wcn/iwnpi/sc2355/libwifieut"

#wcn-ini
EXTERNALSRC_pn-wcnini-marlin2 = "${OEROOT}/source/unisoc/connectivity/wcn/wcnini"
EXTERNALSRC_pn-wcnini-marlin3lite = "${OEROOT}/source/unisoc/connectivity/wcn/wcnini"

# For audio test
EXTERNALSRC_pn-audionpi = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/npi/lib"
EXTERNALSRC_pn-audionpi-phoenix = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/npi/lib"
EXTERNALSRC_pn-audionpitest = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/npi"
EXTERNALSRC_pn-audionpitest-phoenix = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/npi"
EXTERNALSRC_pn-audiospeex = "${OEROOT}/source/unisoc/audiocmp/audiohal/external/speex"
EXTERNALSRC_pn-audioutil = "${OEROOT}/source/unisoc/audiocmp/audiohal/system/media/audio_utils/"
EXTERNALSRC_pn-audioutils = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audioutils"
EXTERNALSRC_pn-audiohaldemo = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/hal_init"
EXTERNALSRC_pn-audiohal-phoenix = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix"
EXTERNALSRC_pn-vbpga = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/whale/platform/normal/vb_pga"
EXTERNALSRC_pn-nvexchange = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/whale/platform/normal/nv_exchange"
EXTERNALSRC_pn-vbeffect = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/whale/platform/normal/vb_effect"
EXTERNALSRC_pn-audioparamteser = "${OEROOT}/source/unisoc/multimedia/audio/audiocmp/audiohal_phoenix/whale/platform/normal/AudioParamTester"

# For qtdemo test
EXTERNALSRC_pn-qtdemo = "${OEROOT}/source/unisoc/graphic/qtdemo"

# ==============for libgstcamera module=======================#
EXTERNALSRC_pn-libgstcamera = "${OEROOT}/source/unisoc/multimedia/camera/libcamera/libgstcamera/"
#for sign tools
EXTERNALSRC_pn-unisoc-sign = "${OEROOT}/prebuilts/scripts/packimage_scripts"
EXTERNALSRC_pn-unisoc-sign-native = "${OEROOT}/prebuilts/scripts/packimage_scripts"

# For inireader
EXTERNALSRC_pn-inireader = "${OEROOT}/source/unisoc/iniconfig/src"
EXTERNALSRC_pn-inireader-test = "${OEROOT}/source/unisoc/iniconfig/test"

EXTERNALSRC_pn-r8125 = "${OEROOT}/source/unisoc/connectivity/wcn/r8125"

# For sensorhub hal
EXTERNALSRC_pn-libsensorhub = "${OEROOT}/source/unisoc/sensorhub/libsensorhub"

# For sensorhub cm4

# For sysdump
EXTERNALSRC_pn-minidump = "${OEROOT}/source/unisoc/modules/sysdump/minidump"
EXTERNALSRC_pn-systemdebuggerd = "${OEROOT}/source/unisoc/modules/sysdump/systemdebuggerd"

# For engmodeapp
EXTERNALSRC_pn-engmodeapp = "${OEROOT}/source/unisoc/modules/engmodeapp"
# END
EXTERNALSRC_pn-focaltech-ats = "${OEROOT}/source/unisoc/modules/touchscreen/adaptive-ts"
EXTERNALSRC_BUILD_pn-focaltech-ats = "${OEROOT}/source/unisoc/modules/touchscreen/adaptive-ts"

EXTERNALSRC_pn-focaltech-ft8756-spi = "${OEROOT}/source/unisoc/modules/touchscreen/focaltech-ft8756-spi"
EXTERNALSRC_BUILD_pn-focaltech-ft8756-spi = "${OEROOT}/source/unisoc/modules/touchscreen/focaltech-ft8756-spi"

#QTDemo
EXTERNALSRC_pn-qtdialor = "${OEROOT}/source/unisoc/graphic/qtdialor"

# For st-module
EXTERNALSRC_pn-libsttest = "${OEROOT}/source/unisoc/modules/npi_lib/libsttest"
EXTERNALSRC_pn-libstcommon = "${OEROOT}/source/unisoc/modules/npi_lib/libsttest/lib/libstcommon"
EXTERNALSRC_pn-libstmultimedia = "${OEROOT}/source/unisoc/modules/npi_lib/libsttest/lib/libstmultimedia"
EXTERNALSRC_pn-libstsystem = "${OEROOT}/source/unisoc/modules/npi_lib/libsttest/lib/libstsystem"
EXTERNALSRC_pn-libstwcn = "${OEROOT}/source/unisoc/modules/npi_lib/libsttest/lib/libstwcn"
EXTERNALSRC_pn-libstmodem = "${OEROOT}/source/unisoc/modules/npi_lib/libsttest/lib/libstmodem"
EXTERNALSRC_pn-libstmodule-test = "${OEROOT}/source/unisoc/modules/npi_lib/libsttest/ut/"
EXTERNALSRC_pn-libstftmode = "${OEROOT}/source/unisoc/modules/engpc/modules/libstftmode"

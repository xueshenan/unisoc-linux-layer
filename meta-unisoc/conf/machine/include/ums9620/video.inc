###for video###
JPG_PLATFORM := "r8p0"
VSP_PLATFORM = "vpu"
VPP_PLATFORM = "sharkl3"
BOARD_PLATFORM = "sp9863a"
BIA_SUPPORT = "true"
AFBC = "false"
RGB_ENC_SUPPORT = "true"

# video omx avc
ADD_OMX_AVC_DEC_HW = "libomxavcdechwsprd"
ADD_OMX_AVC_DEC_SW = "libomxavcdecswsprd"
ADD_OMX_AVC_ENC_HW = "libomxavcenchwsprd"
ADD_UTEST_AVC_DEC = "utestvspavch264dec"
ADD_UTEST_AVC_ENC = "utestvspavch264enc"
ADD_STGFRT_AVC_DEC = "libstagefrightsprdh264dec"
ADD_STGFRT_AVC_ENC = "libstagefrightsprdh264enc"
# video omx m4v h263
ADD_OMX_M4V_H263_DEC_HW = "libomxm4vh263dechwsprd"
ADD_OMX_M4V_H263_DEC_SW = "libomxm4vh263decswsprd"
ADD_OMX_M4V_H263_ENC_SW = "libomxm4vh263encswsprd"
ADD_UTEST_M4V_H263_DEC = "utestvspm4vh263dec"
ADD_UTEST_M4V_H263_ENC = "utestvspm4vh263enc"
ADD_STGFRT_M4V_H263_DEC = "libstagefrightsprdmpeg4dec"
ADD_STGFRT_M4V_H263_ENC = "libstagefrightsprdmpeg4enc"
# video omx hevc
ADD_OMX_HEVC_DEC_HW = "libomxhevcdechwsprd"
ADD_OMX_HEVC_ENC_HW = "libomxhevcenchwsprd"
ADD_UTEST_HEVC_DEC = "utestvsphevch265dec"
ADD_UTEST_HEVC_ENC = "utestvsphevch265enc"
ADD_STGFRT_HEVC_DEC = "libstagefrightsprdh265dec"
ADD_STGFRT_HEVC_ENC = "libstagefrightsprdh265enc"
# video omx vpx
ADD_OMX_VP8_DEC_HW = "libomxvpxdechwsprd"
ADD_UTEST_VP8_DEC = "utestvspvpxdec"
ADD_STGFRT_VP8_DEC = "libstagefrightsprdvpxdec"
# video omx vp9
ADD_OMX_VP9_DEC_HW = "libomxvp9dechwsprd"
ADD_UTEST_VP9_DEC = "utestvspvp9dec"
ADD_STGFRT_VP9_DEC = "libstagefrightsprdvp9dec"
###end video###

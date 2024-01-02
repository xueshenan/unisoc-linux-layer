SUMMARY = "Organize packages to avoid duplication across all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
           packagegroup-unisoc-wayland \
           packagegroup-unisoc-base-qtconfig \
           packagegroup-unisoc-base-graphic \
           '

#PACKAGES = ' \
#           packagegroup-unisoc-wayland \
#           packagegroup-unisoc-base-qtconfig \
#           packagegroup-unisoc-base-graphic \
#           '
RDEPENDS_packagegroup-unisoc-wayland = " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali-midgard', 'packagegroup-unisoc-base-midgard', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali-natt', 'packagegroup-unisoc-base-natt', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pvr-rogue', 'packagegroup-unisoc-base-pvr-rogue', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qtconfig', 'packagegroup-unisoc-base-qtconfig', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qt3ds', 'ogl-runtime', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'graphic', 'packagegroup-unisoc-base-graphic', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qt5fontdir', 'qt5fontdir', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'glmark2', 'glmark2', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'lvgldemo', 'lvgldemo', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'lvgl', 'lvgl', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'lvdrv', 'lvdrv', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'lvinit', 'lvinit', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'lvglota', 'lvglota', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'powersave', 'packagegroup-unisoc-base-powersave', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'powermanager', 'packagegroup-unisoc-base-powermanager', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'fs_tools', 'packagegroup-unisoc-base-fs-debug-tools', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'alsa', 'packagegroup-unisoc-base-alsa', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pulseaudio', 'packagegroup-unisoc-base-pulseaudio', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gstreamer', 'packagegroup-unisoc-base-gstreamer', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'perflink', 'packagegroup-unisoc-base-perflink', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ltp', 'packagegroup-unisoc-base-ltp', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'stress-cicd', 'packagegroup-unisoc-base-stress-cicd', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'focaltech-ats', 'packagegroup-unisoc-base-focaltech-ats', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'focaltech-ft8756-spi', 'packagegroup-unisoc-base-focaltech-ft8756-spi', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wlan-combo', 'packagegroup-unisoc-base-wlan-combo', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aw9523b', 'packagegroup-unisoc-base-aw9523b', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'yt6801', 'packagegroup-unisoc-base-yt6801', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gt1x', 'packagegroup-unisoc-base-gt1x', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'source-hansans-cn-medium-otf', 'source-hansans-cn-medium-otf', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'imgtec', 'packagegroup-unisoc-base-imgtec', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'imgtec', 'packagegroup-unisoc-base-imgtec-lib', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'imgtec', 'packagegroup-unisoc-base-imgtec-cnntest', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aiactiver', 'packagegroup-unisoc-base-aiactiver-lib', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aiactiver', 'packagegroup-unisoc-base-aiactiver-testbench', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vdsp', 'packagegroup-unisoc-base-vdsp', '',d)} \
    "

# because yocto 3.1 meta-qt not have qtenginio recipes,remove qtenginio
SUMMARY_packagegroup-unisoc-base-qtconfig = "qt packages for gui"
RDEPENDS_packagegroup-unisoc-base-qtconfig = " \
    qtwayland \
    qtbase \
    qtmultimedia \
    qtmultimedia-plugins \
    qt3d \
    qt5-plugin-generic-vboxtouch \
    qtcharts \
    qtconnectivity \
    qtdatavis3d \
    qtdeclarative \
    qtgraphicaleffects \
    qtimageformats \
    qtquickcontrols \
    qtquickcontrols2 \
    qtsensors \
    qtserialport \
    qtsvg \
    qtsystems \
    qttools \
    qttranslations \
    qtvirtualkeyboard \
    qtdeclarative-qmlplugins \
    qtquickcontrols-qmlplugins \
"

SUMMARY_packagegroup-unisoc-base-graphic = "graphic packages for weston desktop"
RDEPENDS_packagegroup-unisoc-base-graphic = " \
    wayland \
    weston \
    westoninit \
"

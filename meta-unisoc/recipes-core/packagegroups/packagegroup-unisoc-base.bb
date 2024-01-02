SUMMARY = "Organize packages to avoid duplication across all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
# if you want to use a distro feature control a package ,you cam write as this example
# RDEPENDS_packagegroup-unisoc-console += "${@bb.utils.contains('DISTRO_FEATURES', 'distrofeaturename', 'packagename', '',d)}"
# if you want to use a machine feature control a package ,you can write like this
# RDEPENDS_packagegroup-unisoc-console += "${@bb.utils.contains('MACHINE_FEATURES', 'machinefeaturename', 'packagename', '',d)}"

# if you want to add a single package ,this is a example
# SUMMARY_packagegroup-base-wifi = "WiFi support"
# RDEPENDS_packagegroup-base-wifi = "\
# ${VIRTUAL-RUNTIME_wireless-tools} \
# wpa-supplicant"
#
# and then add thie package to PACKAGES arg and PROVIDES.

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-unisoc-base \
    packagegroup-unisoc-base-debug \
    ${@bb.utils.contains('DEBUG_TOOLS_FLAG','yes','','packagegroup-unisoc-base-debug-user',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'connman', 'packagegroup-unisoc-base-connman', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'perflink', 'packagegroup-unisoc-base-perflink', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'networkutils', 'packagegroup-unisoc-base-networkutils', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wpa-supplicant', 'packagegroup-unisoc-base-wpa-supplicant', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'modem', 'packagegroup-unisoc-base-modem', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'trusty','packagegroup-unisoc-base-trusty', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'radio', 'packagegroup-unisoc-base-radio', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'production','packagegroup-unisoc-base-production', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'sprdstorage','packagegroup-unisoc-base-sprdstorage', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tsupplicant','packagegroup-unisoc-base-tsupplicant', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libteec','packagegroup-unisoc-base-libteec', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'teerpc','packagegroup-unisoc-base-teerpc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aprd','packagegroup-unisoc-base-aprd', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rgbled','packagegroup-unisoc-base-rgbled', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'bt-tool', 'packagegroup-unisoc-base-bt-tool', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'engpc', 'packagegroup-unisoc-base-engpc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'npi-support', 'packagegroup-unisoc-base-npi-support', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'nativemmi', 'packagegroup-unisoc-base-nativemmi', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'charge', 'packagegroup-unisoc-base-charge', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pm', 'packagegroup-unisoc-base-pm', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'orca-deamon', 'packagegroup-unisoc-base-orca-deamon', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ecell-socket', 'packagegroup-unisoc-base-ecell-socket', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'service-plugins', 'packagegroup-unisoc-base-service-plugins', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'usbcontrol', 'packagegroup-unisoc-base-usbcontrol', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'lmbench', 'packagegroup-unisoc-base-lmbench', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aqtool', 'packagegroup-unisoc-base-aqtool', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gnss-ota', 'packagegroup-unisoc-base-gnss-ota', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gnss-adapter', 'packagegroup-unisoc-base-gnss-adapter', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'geoclue', 'packagegroup-unisoc-base-geoclue', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pppdial', 'packagegroup-unisoc-base-pppdial', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pppd', 'packagegroup-unisoc-base-pppd', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'netfuns', 'packagegroup-unisoc-base-netfuns', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'terminal', 'packagegroup-unisoc-base-terminal', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'alsa', 'packagegroup-unisoc-base-alsa', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pulseaudio', 'packagegroup-unisoc-base-pulseaudio', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gstreamer', 'packagegroup-unisoc-base-gstreamer', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'video', 'packagegroup-unisoc-base-video', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'jpeg', 'packagegroup-unisoc-base-jpeg', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'memion', 'packagegroup-unisoc-base-memion', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libbt', 'packagegroup-unisoc-base-libbt', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wcnini-marlin2', 'packagegroup-unisoc-base-wcnini-marlin2', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wcnini-marlin3lite', 'packagegroup-unisoc-base-wcnini-marlin3lite', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'hciattach', 'packagegroup-unisoc-base-hciattach', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gnss', 'packagegroup-unisoc-base-gnss', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libopdm', 'packagegroup-unisoc-base-libopdm', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'cmccdm', 'packagegroup-unisoc-base-cmccdm', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ctcc', 'packagegroup-unisoc-base-ctcc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libctcc', 'packagegroup-unisoc-base-libctcc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rfkill', 'packagegroup-unisoc-base-rfkill', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'benchmark', 'packagegroup-unisoc-base-benchmark', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'xen-tools', 'packagegroup-unisoc-base-xen-tools', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'sleepwakeup', 'packagegroup-unisoc-base-sleepwakeup-tool', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'unisoc-reboot', 'packagegroup-unisoc-base-unisoc-reboot', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aw9523b', 'packagegroup-unisoc-base-aw9523b', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'yt6801', 'packagegroup-unisoc-base-yt6801', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wlan-combo', 'packagegroup-unisoc-base-wlan-combo', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wifitest-sc2332', 'packagegroup-unisoc-base-wifitest-sc2332', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wifitest-sc2355', 'packagegroup-unisoc-base-wifitest-sc2355', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'upower', 'packagegroup-unisoc-base-upower', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'powersave', 'packagegroup-unisoc-base-powersave', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'powermanager', 'packagegroup-unisoc-base-powermanager', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'powerd', 'packagegroup-unisoc-base-powerd', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'powertop', 'packagegroup-unisoc-base-powertop', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali-midgard', 'packagegroup-unisoc-base-midgard', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali-natt', 'packagegroup-unisoc-base-natt', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'audio', 'packagegroup-unisoc-base-audio', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'audio-phoenix', 'packagegroup-unisoc-base-audio-phoenix', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pvr-rogue', 'packagegroup-unisoc-base-pvr-rogue', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rogue-vz', 'packagegroup-unisoc-base-rogue-vz', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'packagegroup-unisoc-base-bluetooth', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'localtime', 'packagegroup-unisoc-base-localtime', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ntpdaemon', 'packagegroup-unisoc-base-ntpdaemon', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ota-adapter', 'packagegroup-unisoc-base-ota-adapter', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'fs_tools', 'packagegroup-unisoc-base-fs-debug-tools', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'audiohal', 'packagegroup-unisoc-base-audiohal', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'audiohal-phoenix', 'packagegroup-unisoc-base-audiohal-phoenix', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'voicecall', 'packagegroup-unisoc-base-voicecall', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'userdata-resize2fs', 'packagegroup-unisoc-base-userdata-resize2fs', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libjsonc', 'packagegroup-unisoc-base-libjsonc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libbqbbt', 'packagegroup-unisoc-base-libbqbbt', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'inireader', 'packagegroup-unisoc-base-inireader', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vorbis-tools', 'packagegroup-unisoc-base-vorbis-tools', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'r8125', 'packagegroup-unisoc-base-r8125', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libgomp', 'packagegroup-unisoc-base-libgomp', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'sensorhub-cm4', 'packagegroup-unisoc-base-sensorhub-cm4', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'batterymanager', 'packagegroup-unisoc-base-batterymanager', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rsyslogsetup', 'packagegroup-unisoc-base-rsyslogsetup', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'unirlog', 'packagegroup-unisoc-base-unirlog', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ltp', 'packagegroup-unisoc-base-ltp', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'stress-cicd', 'packagegroup-unisoc-base-stress-cicd', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libcamera', 'packagegroup-unisoc-base-libcamera', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'sysdump', 'packagegroup-unisoc-base-sysdump', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'engmodeapp', 'packagegroup-unisoc-base-engmodeapp', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'suspendblocker', 'packagegroup-unisoc-base-suspendblocker', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'focaltech-ats', 'packagegroup-unisoc-base-focaltech-ats', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'focaltech-ft8756-spi', 'packagegroup-unisoc-base-focaltech-ft8756-spi', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'strongswan', 'packagegroup-unisoc-base-strongswan', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ota', 'packagegroup-unisoc-base-ota', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'imgtec', 'packagegroup-unisoc-base-imgtec', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'imgtec', 'packagegroup-unisoc-base-imgtec-lib', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'imgtec', 'packagegroup-unisoc-base-imgtec-cnntest', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aiactiver', 'packagegroup-unisoc-base-aiactiver-lib', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aiactiver', 'packagegroup-unisoc-base-aiactiver-testbench', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vdsp', 'packagegroup-unisoc-base-vdsp', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qtdemo', 'packagegroup-unisoc-base-qtdemo', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tracetool', 'packagegroup-unisoc-base-tracetool', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'st-module', 'packagegroup-unisoc-base-st-module', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qfactorytest', 'packagegroup-unisoc-base-qfactorytest', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qlauncher', 'packagegroup-unisoc-base-qlauncher', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gt1x', 'packagegroup-unisoc-base-gt1x', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'middlewaresvc', 'packagegroup-unisoc-base-middlewaresvc', '',d)} \
'

SUMMARY_packagegroup-unisoc-base-ltp = "LTP"
RDEPENDS_packagegroup-unisoc-base-ltp = " \
    ltp \
"
SUMMARY_packagegroup-unisoc-base-stress-cicd = "stress-cicd"
RDEPENDS_packagegroup-unisoc-base-stress-cicd = " \
    stress-cicd \
"


SUMMARY_packagegroup-unisoc-base-propertys = "aprd packages"
RDEPENDS_packagegroup-unisoc-base-aprd = " \
    aprd \
"

SUMMARY_packagegroup-unisoc-base-bt-tool = "bt-tool api for sl8563-base"
RDEPENDS_packagegroup-unisoc-base-bt-tool= "\
    bt-tool \
"

SUMMARY_packagegroup-unisoc-base-strongswan = "strongswan packages"
RDEPENDS_packagegroup-unisoc-base-strongswan= "\
    strongswan \
"

SUMMARY_packagegroup-unisoc-base-imgtec = "imgtec"
RDEPENDS_packagegroup-unisoc-base-imgtec = " \
    imgtec \
"

SUMMARY_packagegroup-unisoc-base-imgtec-lib = "imgtec"
RDEPENDS_packagegroup-unisoc-base-imgtec-lib = " \
    imgtec-lib \
"

SUMMARY_packagegroup-unisoc-base-imgtec-cnntest = "imgtec"
RDEPENDS_packagegroup-unisoc-base-imgtec-cnntest = " \
    imgtec-cnntest \
"


SUMMARY_packagegroup-unisoc-base-aiactiver-lib = "aiactiver"
RDEPENDS_packagegroup-unisoc-base-aiactiver-lib = " \
    aiactiver-lib \
"

SUMMARY_packagegroup-unisoc-base-aiactiver-testbench = "aiactiver"
RDEPENDS_packagegroup-unisoc-base-aiactiver-testbench = " \
    aiactiver-testbench \
"

# this package group not used
RDEPENDS_packagegroup-unisoc-base = "\
    ${@bb.utils.contains('DEBUG_TOOLS_FLAG','yes','','packagegroup-unisoc-base-debug-user',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'connman', 'packagegroup-unisoc-base-connman', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'perflink', 'packagegroup-unisoc-base-perflink', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'networkutils', 'packagegroup-unisoc-base-networkutils', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'modem', 'packagegroup-unisoc-base-modem', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'radio', 'packagegroup-unisoc-base-radio', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'engpc', 'packagegroup-unisoc-base-engpc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'usbcontrol', 'packagegroup-unisoc-base-usbcontrol', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'lmbench', 'packagegroup-unisoc-base-lmbench', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'service-plugins', 'packagegroup-unisoc-base-service-plugins', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'aqtool', 'packagegroup-unisoc-base-aqtool', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gnss-ota', 'packagegroup-unisoc-base-gnss-ota', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'orca-deamon', 'packagegroup-unisoc-base-orca-deamon', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ecell-socket', 'packagegroup-unisoc-base-ecell-socket', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gnss-adapter', 'packagegroup-unisoc-base-gnss-adapter', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'geoclue', 'packagegroup-unisoc-base-geoclue', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'cmccdm', 'packagegroup-unisoc-base-cmccdm', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ctcc', 'packagegroup-unisoc-base-ctcc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libctcc', 'packagegroup-unisoc-base-libctcc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rgbled','packagegroup-unisoc-base-rgbled', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pppdial', 'packagegroup-unisoc-base-pppdial', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pppd', 'packagegroup-unisoc-base-pppd', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'netfuns', 'packagegroup-unisoc-base-netfuns', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'benchmark', 'packagegroup-unisoc-base-benchmark', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'sleepwakeup', 'packagegroup-unisoc-base-sleepwakeup-tool', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ota-adapter', 'packagegroup-unisoc-base-ota-adapter', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'unisoc-reboot', 'packagegroup-unisoc-base-unisoc-reboot', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'libjsonc', 'packagegroup-unisoc-base-libjsonc', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'inireader', 'packagegroup-unisoc-base-inireader', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'batterymanager', 'packagegroup-unisoc-base-batterymanager', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rsyslogsetup', 'packagegroup-unisoc-base-rsyslogsetup', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'unirlog', 'packagegroup-unisoc-base-unirlog', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'engmodeapp', 'packagegroup-unisoc-base-engmodeapp', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'qtdemo', 'packagegroup-unisoc-base-qtdemo', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tracetool', 'packagegroup-unisoc-base-tracetool', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'st-module', 'packagegroup-unisoc-base-st-module', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qfactorytest', 'packagegroup-unisoc-base-qfactorytest', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'qlauncher', 'packagegroup-unisoc-base-qlauncher', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gt1x', 'packagegroup-unisoc-base-gt1x', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'middlewaresvc', 'packagegroup-unisoc-base-middlewaresvc', '',d)} \
"
# add unisoc debug function here, 'user' image not include
SUMMARY_packagegroup-unisoc-base-debug = "debug packages for debug function and image"
RDEPENDS_packagegroup-unisoc-base-debug = " \
    costmem \
    lookat \
    getevent \
    ${@bb.utils.contains('MACHINE_FEATURES', 'benchmark', 'packagegroup-unisoc-base-benchmark', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'sleepwakeup', 'packagegroup-unisoc-base-sleepwakeup-tool', '',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'sysv-initscript', '',d)} \
"

# add unisoc debug function here
SUMMARY_packagegroup-unisoc-base-debug-user = "debug packages, depend on MACHINE_FEATURES in user image"
RDEPENDS_packagegroup-unisoc-base-debug-user = " \
    smem \
"

SUMMARY_packagegroup-unisoc-base-connman = "Network connection management"
RDEPENDS_packagegroup-unisoc-base-connman= "\
    connman-client \
    connman \
    curl \
    libconnman \
    connmantest \
    smartlink-client \
    smartlink-client-test \
"

SUMMARY_packagegroup-unisoc-base-qtdemo = "qtdemo"
RDEPENDS_packagegroup-unisoc-base-qtdemo= "\
    qtdemo \
    qtdialor \
"

SUMMARY_packagegroup-unisoc-base-qfactorytest = "qfactorytest"
RDEPENDS_packagegroup-unisoc-base-qfactorytest= "\
    qfactorytest \
    libudbus \
    libnetcon \
    netctl \
    netcon \
    sthttpd \
    iptables \
	linuxptp \
    tcpdump \
    iputils \
    vlan \
    ethtool \
"

SUMMARY_packagegroup-unisoc-base-qlauncher = "qlauncher"
RDEPENDS_packagegroup-unisoc-base-qlauncher= "\
    qlauncher \
"

SUMMARY_packagegroup-unisoc-base-middlewaresvc = "middlewaresvc"
RDEPENDS_packagegroup-unisoc-base-middlewaresvc= "\
    libudbus \
    middlewaresvc \
"

SUMMARY_packagegroup-unisoc-base-ota = "OTA support"
RDEPENDS_packagegroup-unisoc-base-ota = "\
    swupdate-service \
    nvmerge \
"

SUMMARY_packagegroup-unisoc-base-perflink = "perflink"
RDEPENDS_packagegroup-unisoc-base-perflink= "\
    libudbus \
    libnetcon \
    netctl \
    netcon \
    sthttpd \
    iptables \
	linuxptp \
"

SUMMARY_packagegroup-unisoc-base-sysdump = "export minidump log and sysdump status change"
RDEPENDS_packagegroup-unisoc-base-sysdump = "\
    minidump \
    systemdebuggerd \
"

SUMMARY_packagegroup-unisoc-base-networkutils = "Network Utilities"
RDEPENDS_packagegroup-unisoc-base-networkutils= "\
    tcpdump \
    bridge-utils \
    ebtables \
    iputils \
    vlan \
    ethtool \
"

SUMMARY_packagegroup-unisoc-base-wnifi = "WiFi wpa-supplicant support"
RDEPENDS_packagegroup-unisoc-base-wpa-supplicant = "\
    wpa-supplicant \
"

SUMMARY = "modem service packages for 8563 and others which need this "
RDEPENDS_packagegroup-unisoc-base-modem = " \
    modem-utils \
    modem-control \
    nvitem \
    refnotify \
    slogmodem \
    modem-propctl \
    libmodeminictl \
"

SUMMARY = "terminal service packages for 8563 and others which need this "
RDEPENDS_packagegroup-unisoc-base-terminal = " \
    atrouter \
    iniparser \
    libyoctominiap \
    libcptransport \
    libatcommon \
    unisoc-reboot \
"

SUMMARY = "socket for udx710-ecell"
RDEPENDS_packagegroup-unisoc-base-ecell-socket = " \
    libsocketap \
"
SUMMARY = "ota adapter for udx710-ecell"
RDEPENDS_packagegroup-unisoc-base-ota-adapter = " \
    ota-adapter \
    unitest \
    libotaadaptersocket \
    libotaadapter \
"

SUMMARY = "orca deamon for udx710 700M "
RDEPENDS_packagegroup-unisoc-base-orca-deamon = " \
    led-deamon \
    liborcaled \
    test-entry \
    keyevent-deamon \
"

SUMMARY = "service-plugins for yocto common"
RDEPENDS_packagegroup-unisoc-base-service-plugins = " \
    service-plugins \
"

SUMMARY_packagegroup-unisoc-base-inireader = "ini file read, set and dump"
RDEPENDS_packagegroup-unisoc-base-inireader = " \
    inireader \
"

SUMMARY_packagegroup-unisoc-base-radio = "radio packages including teleservice appdemo ofono channelmanager"
RDEPENDS_packagegroup-unisoc-base-radio = " \
    channelmanager \
    chm-pty \
    chm-at \
    ofono \
    appdemo \
    teleservice \
"
#    rilutils \
#    rilsprd \
#    sprd-ril \
#    sprdrild \
#    rilmbim \
#    mbim-daemon \
#"

SUMMARY_packagegroup-unisoc-base-rgbled = "rgbled api for sl8563-cpe"
RDEPENDS_packagegroup-unisoc-base-rgbled= "\
    rgbled \
    ${@bb.utils.contains('USERDEBUG', 'userdebug', 'rgbled-demo', '',d)} \
"

SUMMARY_packagegroup-unisoc-base-engpc = "engpc for cali && factory test"
RDEPENDS_packagegroup-unisoc-base-engpc= "\
    libsocid \
    engpc \
    engpc-adapt \
    engpc-ctl \
    libapcomm \
    libftmode \
    librebootcmd \
    libmiscdata \
    libtsxdata \
    libfactoryreset \
    libsttest \
    libapdeepsleep \
"

SUMMARY_packagegroup-unisoc-base-npi-support = "npi lib to supprot cali/bbat"
RDEPENDS_packagegroup-unisoc-base-npi-support = "\
    libtcard \
    librtc \
    libgpio \
    libkpd \
    liblkv \
    libapopt \
    libcharge \
    libtsensor \
    libapdeepsleep \
    liborca \
    libtp \
    libregulator \
    libchipid \
    libsocid \
    libnefuse \
    libetb \
    libusbtypec \
    libadc \
    libautotestwifi \
    libpartinfo \
    libreadfixednv \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'liblcdnpi', '',d)} \
"

SUMMARY_packagegroup-unisoc-base-pm = "Power management"
RDEPENDS_packagegroup-unisoc-base-pm = "\
    pm \
"

SUMMARY_packagegroup-unisoc-base-nativemmi = "nativemmi test"
RDEPENDS_packagegroup-unisoc-base-nativemmi = "\
    nativemmi \
    factoryradiotest \
"

SUMMARY_packagegroup-unisoc-base-charge = "power off charge"
RDEPENDS_packagegroup-unisoc-base-charge = "\
    charge \
"

SUMMARY_packagegroup-unisoc-base-usbcontrol = "usbcontrol for monitor && enum"
RDEPENDS_packagegroup-unisoc-base-usbcontrol= "\
    usbcontrol \
"
#    umtp-responder \
#"

SUMMARY_packagegroup-unisoc-base-lmbench = "performance for lmbench bonnie++ perf"
RDEPENDS_packagegroup-unisoc-base-lmbench= "\
    ${@bb.utils.contains('USERDEBUG', 'userdebug', 'lmbench', '',d)} \
    ${@bb.utils.contains('USERDEBUG', 'userdebug', 'bonnie++', '',d)} \
    ${@bb.utils.contains('USERDEBUG', 'userdebug', 'perf', '',d)} \
    ${@bb.utils.contains('USERDEBUG', 'userdebug', 'latencytop', '',d)} \
    ${@bb.utils.contains('IPERF_VERSION', 'iperf2', 'iperf2', 'iperf3',d)} \
"

SUMMARY_packagegroup-unisoc-base-aqtool = "aqtool for orca"
RDEPENDS_packagegroup-unisoc-base-aqtool= "\
    pcieupdate \
"

# add unisoc trusty function here
SUMMARY_packagegroup-unisoc-base-trusty = "trusty packages"
RDEPENDS_packagegroup-unisoc-base-trusty = " \
    libtrusty \
"

# add unisoc trusty libteec function here
SUMMARY_packagegroup-unisoc-base-libteec = "libteec packages"
RDEPENDS_packagegroup-unisoc-base-libteec = " \
    libteec \
"

SUMMARY_packagegroup-unisoc-base-pppdial = "pppdial for sl8563"
RDEPENDS_packagegroup-unisoc-base-pppdial= "\
    pppdial \
"

SUMMARY_packagegroup-unisoc-base-pppd = "pppd packages"
RDEPENDS_packagegroup-unisoc-base-pppd= "\
    ppp \
    radvd \
"

SUMMARY_packagegroup-unisoc-base-netfuns = "netfuns packages"
RDEPENDS_packagegroup-unisoc-base-netfuns= "\
    ipv6drophop \
"

# add unisoc production function here
SUMMARY_packagegroup-unisoc-base-production = "production packages"
RDEPENDS_packagegroup-unisoc-base-production = " \
    libteeproduction \
    libdynamicproduction \
    libcheckx \
    libgetuid \
"

# add unisoc secure storage function here
SUMMARY_packagegroup-unisoc-base-storage = "secure storage packages"
RDEPENDS_packagegroup-unisoc-base-sprdstorage = " \
    sprdstorage \
"

# add unisoc tee_rpc function here
SUMMARY_packagegroup-unisoc-base-teerpc = "teerpc packages"
RDEPENDS_packagegroup-unisoc-base-teerpc = " \
    teerpc \
"

# add unisoc tsupplicant function here
SUMMARY_packagegroup-unisoc-base-tsupplicant = "tsupplicant packages"
RDEPENDS_packagegroup-unisoc-base-tsupplicant = " \
    tsupplicant \
"


#alsa lib group
SUMMARY_packagegroup-unisoc-base-alsa = "alsa packages"
RDEPENDS_packagegroup-unisoc-base-alsa = " \
    alsa-lib \
    alsa-utils \
    alsa-plugins \
"

#pulseaudio group
SUMMARY_packagegroup-unisoc-base-pulseaudio = "pulseaudio packages"
RDEPENDS_packagegroup-unisoc-base-pulseaudio = " \
    libpulsecore \
    pulseaudio-dev \
    pulseaudio-server \
    libpulse-simple \
    audiodaemon \
    paconfig \
"

#gstreamer group
SUMMARY_packagegroup-unisoc-base-gstreamer = "gstreamer packages"
RDEPENDS_packagegroup-unisoc-base-gstreamer = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-base \
    gstreamer1.0-meta-base \
    gstreamer1.0-plugins-ugly \
    gstreamer1.0-omx \
    gstreamer1.0-libav \
"
#jpg codes group
SUMMARY_packagegroup-unisoc-base-jpeg = "jpeg codecs packages"
RDEPENDS_packagegroup-unisoc-base-jpeg = " \
    libjpegencswsprd \
    libjpeghwsprd \
"
#video codes group
SUMMARY_packagegroup-unisoc-base-video = "video codecs packages"
RDEPENDS_packagegroup-unisoc-base-video = " \
    libsprdomxcore \
    ${ADD_OMX_AVC_DEC_HW} \
    ${ADD_OMX_AVC_DEC_SW} \
    ${ADD_OMX_AVC_ENC_HW} \
    ${ADD_OMX_HEVC_DEC_HW} \
    ${ADD_OMX_HEVC_ENC_HW} \
    ${ADD_OMX_M4V_H263_DEC_HW} \
    ${ADD_OMX_M4V_H263_DEC_SW} \
    ${ADD_OMX_M4V_H263_ENC_SW} \
    ${ADD_OMX_VP8_DEC_HW} \
    ${ADD_OMX_VP9_DEC_HW} \
    ${ADD_UTEST_AVC_DEC} \
    ${ADD_UTEST_AVC_ENC} \
    ${ADD_UTEST_HEVC_DEC} \
    ${ADD_UTEST_HEVC_ENC} \
    ${ADD_UTEST_M4V_H263_DEC} \
    ${ADD_UTEST_M4V_H263_ENC} \
    ${ADD_UTEST_VP8_DEC} \
    ${ADD_UTEST_VP9_DEC} \
    ${ADD_STGFRT_AVC_DEC} \
    ${ADD_STGFRT_AVC_ENC} \
    ${ADD_STGFRT_HEVC_DEC} \
    ${ADD_STGFRT_HEVC_ENC} \
    ${ADD_STGFRT_M4V_H263_DEC} \
    ${ADD_STGFRT_M4V_H263_ENC} \
    ${ADD_STGFRT_VP8_DEC} \
    ${ADD_STGFRT_VP9_DEC} \
"

#memion group
SUMMARY_packagegroup-unisoc-base-memion = "memion packages"
RDEPENDS_packagegroup-unisoc-base-memion = " \
    libmemion \
"

SUMMARY_packagegroup-unisoc-base-libbt = "vendor bluetooth module"
RDEPENDS_packagegroup-unisoc-base-libbt = "\
    libbt \
    btsuite \
    libbteut \
    libengbt \
"

SUMMARY_packagegroup-unisoc-base-wcnini-marlin2 = "vendor wcn marlin2 module"
RDEPENDS_packagegroup-unisoc-base-wcnini-marlin2 = "\
    wcnini-marlin2 \
"

SUMMARY_packagegroup-unisoc-base-wcnini-marlin3lite = "vendor wcn marlin3lite module"
RDEPENDS_packagegroup-unisoc-base-wcnini-marlin3lite = "\
    wcnini-marlin3lite \
"

SUMMARY_packagegroup-unisoc-base-hciattach = "bluez5 hciattach"
RDEPENDS_packagegroup-unisoc-base-hciattach= "\
    hciattach \
"

SUMMARY_packagegroup-unisoc-base-bluetooth = "bluetooth"
RDEPENDS_packagegroup-unisoc-base-bluetooth = "\
    bluez5 \
"

SUMMARY_packagegroup-unisoc-base-gnss = "gnss & agnss package"
RDEPENDS_packagegroup-unisoc-base-gnss = "\
    gnss \
"

#libopdm group
SUMMARY_packagegroup-unisoc-base-libopdm = "operator dm utility lib"
RDEPENDS_packagegroup-unisoc-base-libopdm = " \
    opdmutility \
"

#cmccdm group
SUMMARY_packagegroup-unisoc-base-cmccdm = "cmcc dm"
RDEPENDS_packagegroup-unisoc-base-cmccdm = " \
    cmccdm \
    opdmutility \
    cmccdmsdk \
"
#ctcc iotdm group
SUMMARY_packagegroup-unisoc-base-ctcc = "ctcc iotdm"
RDEPENDS_packagegroup-unisoc-base-ctcc = " \
    regservice \
    regdaemon \
"
#ctcc iotdm libgroup
SUMMARY_packagegroup-unisoc-base-libctcc = "ctcc iotdm lib"
RDEPENDS_packagegroup-unisoc-base-libctcc = " \
    json-c \
"

#rfkill group
SUMMARY_packagegroup-unisoc-base-rfkill = "rfkill module"
RDEPENDS_packagegroup-unisoc-base-rfkill = " \
    rfkill \
"

# add unisoc-gnss-ota
SUMMARY_packagegroup-unisoc-base-gnss-ota = "gnss-ota test, depend on MACHINE_FEATURES in user image"
RDEPENDS_packagegroup-unisoc-base-gnss-ota = " \
    gnss-ota \
"

# add gnss-adapter and gpsd function here
SUMMARY_packagegroup-unisoc-base-gnss-adapter = "gnss-adapter and gpsd packages, depend on MACHINE_FEATURES in user image"
RDEPENDS_packagegroup-unisoc-base-gnss-adapter = " \
    gnss-adapter \
    gpsd \
    test-gnss-adapter \
"

# add geoclue function here
SUMMARY_packagegroup-unisoc-base-geoclue = "geoclue packages, depend on MACHINE_FEATURES in user image"
RDEPENDS_packagegroup-unisoc-base-geoclue = " \
    geoclue \
"

# add benchmark function here
SUMMARY_packagegroup-unisoc-base-benchmark = "benchmark packages "
RDEPENDS_packagegroup-unisoc-base-benchmark = " \
    bonnie++ \
    dhrystone \
    fio \
    linpack \
    lmbench \
    sysbench \
    tinymembench \
    whetstone \
    ${@bb.utils.contains('IPERF_VERSION', 'iperf2', 'iperf2', 'iperf3',d)} \
    flashtest \
"

#audio group
SUMMARY_packagegroup-unisoc-base-audio = "audio packages"
RDEPENDS_packagegroup-unisoc-base-audio = " \
    audiospeex \
    audionpitest \
    audioutil \
    audionpi \
"

#audio-phoenix group
SUMMARY_packagegroup-unisoc-base-audio-phoenix = "audio-phoenix packages"
RDEPENDS_packagegroup-unisoc-base-audio-phoenix = " \
    audiospeex-phoenix \
    audionpitest-phoenix \
    audioutil-phoenix \
    audionpi-phoenix \
"

#audiohal group
SUMMARY_packagegroup-unisoc-base-audiohal = "audiohal packages"
RDEPENDS_packagegroup-unisoc-base-audiohal = " \
    audiohal \
    tinyxml \
    voice \
    audiominiap \
"

#audiohal-phoenix group
SUMMARY_packagegroup-unisoc-base-audiohal-phoenix = "audiohal-phoenix packages"
RDEPENDS_packagegroup-unisoc-base-audiohal-phoenix = " \
    audiohaltest \
    audiohal-phoenix \
    tinyxml-phoenix \
    audioparamteser \
"

#voicecall group
SUMMARY_packagegroup-unisoc-base-voicecall = "voicecall packages"
RDEPENDS_packagegroup-unisoc-base-voicecall = " \
    voicecall \
    voicecalltest \
    call-monitor \
"

# add xen-tools function here
SUMMARY_packagegroup-unisoc-base-xen-tools = "xen tools"
RDEPENDS_packagegroup-unisoc-base-xen-tools = "\
    xen-tools-console \
    xen-tools-dbg \
    xen-tools-dev \
    xen-tools-devd \
    xen-tools-flask-tools \
    xen-tools-fsimage \
    xen-tools-init-xenstore-dom \
    xen-tools-libfsimage \
    xen-tools-libfsimage-dev \
    xen-tools-libxencall \
    xen-tools-libxencall-dev \
    xen-tools-libxenctrl \
    xen-tools-libxenctrl-dev \
    xen-tools-libxendevicemodel \
    xen-tools-libxendevicemodel-dev \
    xen-tools-libxenevtchn \
    xen-tools-libxenevtchn-dev \
    xen-tools-libxenforeignmemory \
    xen-tools-libxenforeignmemory-dev \
    xen-tools-libxengnttab \
    xen-tools-libxengnttab-dev \
    xen-tools-libxenguest \
    xen-tools-libxenguest-dev \
    xen-tools-libxenlight \
    xen-tools-libxenlight-dev \
    xen-tools-libxenstat \
    xen-tools-libxenstat-dev \
    xen-tools-libxenstore \
    xen-tools-libxenstore-dev \
    xen-tools-libxentoolcore \
    xen-tools-libxentoolcore-dev \
    xen-tools-libxentoollog \
    xen-tools-libxentoollog-dev \
    xen-tools-libxenvchan \
    xen-tools-libxenvchan-dev \
    xen-tools-libxlutil \
    xen-tools-libxlutil-dev \
    xen-tools-livepatch \
    xen-tools-misc \
    xen-tools-remus \
    xen-tools-scripts-block \
    xen-tools-scripts-common \
    xen-tools-scripts-network \
    xen-tools-staticdev \
    xen-tools-volatiles \
    xen-tools-xencommons \
    xen-tools-xendomains \
    xen-tools-xenmon \
    xen-tools-xenpmd \
    xen-tools-xenstat \
    xen-tools-xenstore \
    xen-tools-xenstored \
    xen-tools-xentrace \
    xen-tools-xen-watchdog \
    xen-tools-xl \
    xen-tools-xl-examples \
    domu-guest \
"

# add sleepwakeup-tool here
SUMMARY_packagegroup-unisoc-base-sleepwakeup-tool = "sleepwakeup tools"
RDEPENDS_packagegroup-unisoc-base-sleepwakeup-tool = "\
    sleepwakeup \
"

SUMMARY_packagegroup-unisoc-base-aw9523b = "gpio ext module driver , aw9523b"
RDEPENDS_packagegroup-unisoc-base-aw9523b = "\
    aw9523b \
"

SUMMARY_packagegroup-unisoc-base-yt6801 = "network module driver , yt6801"
RDEPENDS_packagegroup-unisoc-base-yt6801 = "\
    yt6801 \
"

SUMMARY_packagegroup-unisoc-base-gt1x = "touchscreen module driver , gt1x"
RDEPENDS_packagegroup-unisoc-base-gt1x = "\
	gt1x \
"

#wifi module driver: sprd_wlan_combo
SUMMARY_packagegroup-unisoc-base-wlan-combo = "wifi module driver , wlan-combo"
RDEPENDS_packagegroup-unisoc-base-wlan-combo = "\
    wlan-combo \
"

#wifi factory test group: sc2332
SUMMARY_packagegroup-unisoc-base-wifitest-sc2332 = "wifi factory test group , sc2332"
RDEPENDS_packagegroup-unisoc-base-wifitest-sc2332 = "\
    iwnpi-sc2332 \
    libwifieut-sc2332 \
"

#wifi factory test group: sc2355
SUMMARY_packagegroup-unisoc-base-wifitest-sc2355 = "wifi factory test group , sc2355"
RDEPENDS_packagegroup-unisoc-base-wifitest-sc2355 = "\
    iwnpi-sc2355 \
    libwifieut-sc2355 \
"

# upower
SUMMARY_packagegroup-unisoc-base-upower = "upower"
RDEPENDS_packagegroup-unisoc-base-upower = "\
    upower \
"
# suspendblocker
SUMMARY_packagegroup-unisoc-base-suspendblocker = "suspendblocker"
RDEPENDS_packagegroup-unisoc-base-suspendblocker = "\
    suspendblocker \
"

# pm-powersave
SUMMARY_packagegroup-unisoc-base-powersave = "powersave"
RDEPENDS_packagegroup-unisoc-base-powersave = "\
    powersave \
"

# powermanager
SUMMARY_packagegroup-unisoc-base-powermanager = "powermanager"
RDEPENDS_packagegroup-unisoc-base-powermanager = "\
    powermanager \
"
# pm-powerd
SUMMARY_packagegroup-unisoc-base-powerd = "powerd"
RDEPENDS_packagegroup-unisoc-base-powerd = "\
    powerd \
"

# powertop
SUMMARY_packagegroup-unisoc-base-powerrop = "powertop"
RDEPENDS_packagegroup-unisoc-base-powertop = "\
    powertop \
"

# mali midgard gpu
SUMMARY_packagegroup-unisoc-base-midgard = "Mali Midgard GPU Kernel and User Space Drivers"
RDEPENDS_packagegroup-unisoc-base-midgard = "\
    midgard \
    libmidgard \
"

# mali natt gpu
SUMMARY_packagegroup-unisoc-base-natt = "Mali Natt GPU Kernel and User Space Drivers"
RDEPENDS_packagegroup-unisoc-base-natt = "\
    natt \
    libnatt \
"

# Imgtec PowerVR Rogue GPU
SUMMARY_packagegroup-unisoc-base-pvr-rogue = "IMG PowerVR Rogue GPU Kernel & User Space Drivers"
RDEPENDS_packagegroup-unisoc-base-pvr-rogue = "\
    rogue-km \
    rogue-um \
"
# IMG Rogue gpu VZ
SUMMARY_packagegroup-unisoc-base-rogue-vz = "IMG Rogue GPU VZ Drivers"
RDEPENDS_packagegroup-unisoc-base-rogue-vz = "\
    rogue-vz-km \
    rogue-vz-um \
"
# localtime
SUMMARY_packagegroup-unisoc-base-localtime = "add local time modules"
RDEPENDS_packagegroup-unisoc-base-localtime = "\
    tzdata \
    ntp    \
"
# ntp_daemon
SUMMARY_packagegroup-unisoc-base-ntpdaemon = "restart ntp when wifi connected"
RDEPENDS_packagegroup-unisoc-base-ntpdaemon = "\
    ntpdaemon \
"

# fs debug tools
SUMMARY_packagegroup-unisoc-base-fs-debug-tools = "add fs debug tools"
RDEPENDS_packagegroup-unisoc-base-fs-debug-tools = "\
    e2fsprogs\
    e2fsprogs-resize2fs \
"

SUMMARY_packagegroup-unisoc-base-userdata-resize2fs = "auto resize userdata space "
RDEPENDS_packagegroup-unisoc-base-userdata-resize2fs = " \
    resize2fs-script \
"

#libjson-c
SUMMARY_packagegroup-unisoc-base-libjsonc = "libjsonc"
RDEPENDS_packagegroup-unisoc-base-libjsonc = " \
    json-c \
"
#libbqbbt
SUMMARY_packagegroup-unisoc-base-libbqbbt = "libbqbbt"
RDEPENDS_packagegroup-unisoc-base-libbqbbt = " \
    libbqbbt \
"

SUMMARY_packagegroup-unisoc-base-vorbis-tools = "vorbis-tools packages"
RDEPENDS_packagegroup-unisoc-base-vorbis-tools = " \
    vorbis-tools \
    libao \
"

SUMMARY_packagegroup-unisoc-base-r8125 = "Unisoc r8125 drivers"
RDEPENDS_packagegroup-unisoc-base-r8125 = "\
    r8125 \
"

SUMMARY_packagegroup-unisoc-base-libgomp = "for Unisoc sl8541 smartpen"
RDEPENDS_packagegroup-unisoc-base-libgomp = "\
    libgomp \
"
# for sensorhub
SUMMARY_packagegroup-unisoc-base-libsensorhub = "libsensorhub"
RDEPENDS_packagegroup-unisoc-base-libsensorhub = "\
     libsensorhub \
"

# for sensorhub cm4
SUMMARY_packagegroup-unisoc-base-sensorhub-cm4 = "sensorhub-cm4"
RDEPENDS_packagegroup-unisoc-base-sensorhub-cm4 = "\
     sensorhub-cm4 \
"

# for batterymanager
SUMMARY_packagegroup-unisoc-base-batterymanager = "batterymanager"
RDEPENDS_packagegroup-unisoc-base-batterymanager = "\
     batterymanager \
"

#for rsyslog
SUMMARY_packagegroup-unisoc-base-rsyslogsetup = "rsyslogsetup"
RDEPENDS_packagegroup-unisoc-base-rsyslogsetup = "\
     rsyslogsetup \
     cronie \
"


#for unirlog
SUMMARY_packagegroup-unisoc-base-unirlog = "unirlog"
RDEPENDS_packagegroup-unisoc-base-unirlog = "\
     unirlog-service \
     unirlog-ctl \
     unirlog-dbus \
     ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'unirlog-manager', '',d)} \
"

SUMMARY_packagegroup-unisoc-base-tracetool = "tracetool"
RDEPENDS_packagegroup-unisoc-base-tracetool = "\
    lttng-ust \
    systemtap \
"
#for libcamera group
SUMMARY_packagegroup-unisoc-base-libcamera = "libcamera packages"
RDEPENDS_packagegroup-unisoc-base-libcamera = " \
    libcaminiparser \
    libcamcommon \
    libcamxmlparser \
    libcamv4l2yuv2rgb \
    libcamoem \
    sprd-camsys-pw-domain \
    sprd-cpp \
    sprd-sensor \
    sprd-flash-drv \
    flash-ic-ocp8137 \
    sprd-camera \
    libispalg \
    libpss-isp2.7 \
    libcambr-isp2.7 \
    libcampm-isp2.7 \
    libcamps \
    libcampmloader \
    libcamsensor \
    libcam-af-drv \
    libcam-otp-parser \
    libcam-otp-drv \
    libcam-sensor-drv \
    libcamv4l2adapter \
    v4l2camera \
    libcppdrv \
    unittest \
    libcamfactorytest \
    libgstcamera \
    libcamcalitest \
    isptuning \
"
#for vdsp group
SUMMARY_packagegroup-unisoc-base-vdsp = "vdsp packages"
RDEPENDS_packagegroup-unisoc-base-vdsp = " \
    libvdspservice \
    sprd-vdsp \
"
# for engmodeapp
SUMMARY_packagegroup-unisoc-base-engmodeapp = "engmodeapp"
RDEPENDS_packagegroup-unisoc-base-engmodeapp = "\
    engmodeapp \
"
# for st-module
SUMMARY_packagegroup-unisoc-base-st-module = "st-module"
RDEPENDS_packagegroup-unisoc-base-st-module = "\
    libstcommon \
    libstmultimedia \
    libstsystem \
    libstwcn \
    libstmodem \
    libstmodule-test \
    libstftmode \
"

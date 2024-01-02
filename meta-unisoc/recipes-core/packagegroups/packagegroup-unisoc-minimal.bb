SUMMARY = "Merge machine and distro options to create a basic machine task/package"
PR = "r1"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
# if you want to use a distro feature control a package ,you cam write as this example
# RDEPENDS_packagegroup-unisoc-minimal += "${@bb.utils.contains('DISTRO_FEATURES', 'distrofeaturename', 'packagename', '',d)}"
# if you want to use a machine feature control a package ,you can write like this
# RDEPENDS_packagegroup-unisoc-minimal += "${@bb.utils.contains('MACHINE_FEATURES', 'machinefeaturename', 'packagename', '',d)}"

# if you want to add a single package ,this is a example
#SUMMARY_packagegroup-base-wifi = "WiFi support"
#RDEPENDS_packagegroup-base-wifi = "\
#${VIRTUAL-RUNTIME_wireless-tools} \
#wpa-supplicant"
#
#and then add thie package to PACKAGES arg and PROVIDES.




PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
   packagegroup-unisoc-minimal \
'
#
# packagegroup-unisco-base contain distro feature and machine feature package control
#
RDEPENDS_packagegroup-unisoc-minimal = "\
    "

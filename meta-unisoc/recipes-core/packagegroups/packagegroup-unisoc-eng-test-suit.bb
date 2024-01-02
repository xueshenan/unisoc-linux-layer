SUMMARY = "Organize packages to avoid duplication across all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
# if you want to use a distro feature control a package ,you can write as this example
# RDEPENDS_packagegroup-unisoc-eng-test-suit += "${@bb.utils.contains('DISTRO_FEATURES', 'distrofeaturename', 'packagename', '',d)}"
# if you want to use a machine feature control a package ,you can write like this
# RDEPENDS_packagegroup-unisoc-eng-test-suit += "${@bb.utils.contains('MACHINE_FEATURES', 'machinefeaturename', 'packagename', '',d)}"

# if you want to add a single package ,this is a example
# SUMMARY_packagegroup-base-wifi = "WiFi support"
# RDEPENDS_packagegroup-base-wifi = "\
# ${VIRTUAL-RUNTIME_wireless-tools} \
# wpa-supplicant"
#
# and then add thie package to PACKAGES arg and PROVIDES.

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-unisoc-eng-test-suit \
'

SUMMARY_packagegroup-unisoc-eng-test-suit = "test cases for eng verison"
RDEPENDS_packagegroup-unisoc-eng-test-suit = " \
"

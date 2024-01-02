require unisoc-minimal-image.bb

SUMMARY = "Basic console image"

SUPPORT_SSHD ?= ""

FEATURE_PACKAGES_lrzsz = "lrzsz"

IMAGE_FEATURES += " \
    ${SUPPORT_SSHD} \
    package-management \
    ${@bb.utils.contains('USERDEBUG','userdebug',bb.utils.contains('DEBUG_TOOLS_FLAG','yes','','tools-debug',d),'',d)} \
    ${@bb.utils.contains('USERDEBUG','userdebug',bb.utils.contains('MACHINE_FEATURES','debug-dbg','dbg-pkgs','',d),'',d)} \
    lrzsz \
    ${@bb.utils.contains('ENG_IT','eng','eng-test-suit','',d)} \
"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-unisoc-console \
"

inherit extrausers

EXTRA_USERS_PARAMS = "\
    groupadd -g 1000 user; \
    useradd -p '' -u 1000 -g 1000 user; \
"
EXTRA_USERS_PARAMS_append = " \
    ${@bb.utils.contains('USERDEBUG','userdebug','usermod -p \'\' root;','usermod -p \'abu2Y6QjfZKeU\' root;',d)}\
"


# docker pulls runc/containerd, which in turn recommend lxc unecessarily
BAD_RECOMMENDATIONS_append = " lxc"

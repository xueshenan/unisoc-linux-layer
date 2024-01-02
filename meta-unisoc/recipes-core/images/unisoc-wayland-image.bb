require unisoc-console-image.bb

SUMMARY = "Basic wayland image"

LICENSE = "MIT"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-unisoc-wayland \
    ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', 'packagegroup-core-selinux', '', d)} \
"
inherit ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', 'selinux-image', '', d)}


inherit extrausers

EXTRA_USERS_PARAMS = "\
    groupadd -g 1000 user; \
    useradd -p '' -u 1000 -g 1000 user; \
"
EXTRA_USERS_PARAMS_append = " \
    ${@bb.utils.contains('USERDEBUG','userdebug','usermod -p \'\' root;','usermod -p \'abu2Y6QjfZKeU\' root;',d)}\
"


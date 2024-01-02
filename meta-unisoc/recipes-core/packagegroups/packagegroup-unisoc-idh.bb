SUMMARY = "idh pacakges only for custmer idh mode"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-unisoc-idh \
    ${@bb.utils.contains('MACHINE_FEATURES', 'radio', 'packagegroup-unisoc-idh-radio', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gnss', 'packagegroup-unisoc-idh-gnss', '',d)}  \
    ${@bb.utils.contains('MACHINE_FEATURES', 'modem', 'packagegroup-unisoc-idh-modem', '',d)} \
'

SUMMARY_packagegroup-unisoc-idh = "idh  packages collection"
RDEPENDS_packagegroup-unisoc-idh = " \
    xmlparser \
    ${@bb.utils.contains('MACHINE_FEATURES', 'radio', 'packagegroup-unisoc-idh-radio', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'engpc', 'atci', '',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'termiaml', 'at-router', '',d)}  \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pppdial', 'pppdial', '',d)}  \
    ${@bb.utils.contains('MACHINE_FEATURES', 'cmccdm', 'cmccdmsdk', '',d)}  \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gnss', 'packagegroup-unisoc-idh-gnss', '',d)}  \
    ${@bb.utils.contains('MACHINE_FEATURES', 'modem', 'packagegroup-unisoc-idh-modem', '',d)} \
"

SUMMARY_packagegroup-unisoc-idh-radio = "radio packages for idh collection"
RDEPENDS_packagegroup-unisoc-idh-radio = " \
    channelmanager \
    chm-pty \
    chm-at \
"
#    rilutils \
#    rilsprd \
#    sprd-ril \
#    sprdrild \
#"

SUMMARY_packagegroup-unisoc-idh-gnss = "gnss & agnss package"
RDEPENDS_packagegroup-unisoc-idh-gnss = "\
    gnss \
"

SUMMARY = "modem service packages for 8563 and others which need this "
RDEPENDS_packagegroup-unisoc-idh-modem = " \
    modem-control \
    slogmodem \
    nvitem \
    refnotify \
"
### idh packages contain:
#rilsprd_0.0.1.bb
#rilutils_0.0.1.bb
#sprdrild_0.0.1.bb
#sprd-ril_0.0.1.bb
#atci
#utils.bb rdepend on at-router
#at-router.bb
#pppdial_0.1.bb
#xmlparser.bb rdepend on ofono
#gnss gnss_0.0.1.bb
#cmccdmsdk_0.0.1.bb
#halo

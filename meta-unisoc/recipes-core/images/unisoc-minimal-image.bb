require unisoc-image-common.inc

SUMMARY = "Minimal image"


LICENSE = "MIT"

inherit core-image features_check extrausers


IMAGE_LINGUAS = " "

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-unisoc-minimal \
"


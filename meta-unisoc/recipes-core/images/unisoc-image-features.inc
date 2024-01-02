# define unisoc IMAGE FEATURE & FEATURE_PACKAGES here

IMAGE_FEATURES[validitems] += "autologin-root"
IMAGE_FEATURES[validitems] += "eng-test-suit"
IMAGE_FEATURES[validitems] += "use-overlayfs"
IMAGE_FEATURES[validitems] += "prc-localtime"
FEATURE_PACKAGES_eng-test-suit = "packagegroup-unisoc-eng-test-suit"

# if want autologin，add autologin-root to IMAGE_FEATURES
# IMAGE_FEATURES += "autologin-root"

# if want use-overlayfs，add use-overlayfs to IMAGE_FEATURES in conf file
# IMAGE_FEATURES += "use-overlayfs"
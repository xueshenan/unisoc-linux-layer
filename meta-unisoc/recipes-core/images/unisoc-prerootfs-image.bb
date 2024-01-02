SUMMARY = "image with linux OS"

LICENSE = "MIT"

IMAGE_INSTALL = ""
IMAGE_FEATURES = ""
CORE_IMAGE_BASE_INSTALL = ""
IMAGE_FSTYPES = ""
IMAGE_FSTYPES_append = ""

# KERNELDEPMODDEPEND ?= "virtual/kernel:do_packagedata"
do_build[depends] += "virtual/kernel:do_deploy"

UBOOT_VERSION ?= ""

DEPENDS = "${UBOOT_VERSION}"
DEPENDS += "chipram"
DEPENDS += "${@bb.utils.contains('MACHINE_FEATURES', 'imgtec_ko', 'imgtec', '', d)}"
DEPENDS += "${@bb.utils.contains('MACHINE_FEATURES', 'pvr-rogue', 'rogue-km', '', d)}"
DEPENDS += "${@bb.utils.contains('EXTRA_IMAGEDEPENDS', 'sml', 'sml', '', d)}"
DEPENDS += "${@bb.utils.contains('EXTRA_IMAGEDEPENDS', 'trusty', 'trusty', '', d)}"
DEPENDS += "${@bb.utils.contains('MACHINE_FEATURES', 'sensorhub-cm4', 'sensorhub-cm4', '', d)}"


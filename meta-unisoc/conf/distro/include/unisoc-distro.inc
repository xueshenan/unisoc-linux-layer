# this file is used for unisoc distro feature

# add for esdk
require conf/distro/include/yocto-uninative.inc
INHERIT += "uninative"

DISTRO_VERSION = "${MACHINE}+${DISTRO}-2.0+${DATE_BUILD}+${USERDEBUG}+native"
DISTRO_VERSION[vardepsexclude] = "DATE"
DISTRO_CODENAME = "dunfell"
SDK_NAME = "${DISTRO}-${TCLIBC}-${SDK_ARCH}-${IMAGE_BASENAME}-${TUNE_PKGARCH}"
SDK_VERSION := "${@'${DISTRO_VERSION}'.replace('snapshot-${DATE}','snapshot')}"
SDK_VERSION[vardepsexclude] = "DATE"
SDK_VENDOR = "-unisocsdk"
SDK_NAME_PREFIX = "${DISTRO}"
SDKPATH = "/opt/${DISTRO}/${SDK_VERSION}"

MAINTAINER = "zhangyong <yong.z@unisoc.com>"

TARGET_VENDOR = "-unisoc"

LOCALCONF_VERSION = "1"

IMAGE_LINGUAS ?= " "

ENABLE_BINARY_LOCALE_GENERATION ?= "0"

DISTRO_FEATURES_DEFAULT ?= " "

DISTRO_FEATURES_BACKFILL = " "

DISTRO_FEATURES_BACKFILL_CONSIDERED += "pulseaudio sysvinit bluez5 gobject-introspection-data ldconfig"

# QA check settings - a little stricter than the OE-Core defaults
WARN_TO_ERROR_QA = "already-stripped compile-host-path install-host-path \
                    installed-vs-shipped ldflags pn-overrides rpaths staticdev \
                    useless-rpaths"
WARN_QA_remove = "${WARN_TO_ERROR_QA}"
ERROR_QA_append = " ${WARN_TO_ERROR_QA}"

INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"

# GCCVERSION ?= "arm-9.2"

PACKAGE_CLASSES ?= "package_ipk"

USERDEBUG ?= "userdebug"
SECBOOT_ENABLE ?= "sec"
DATE_BUILD ?= "${DATE}"

# avoid BB_NO_NETWORK error
CONNECTIVITY_CHECK_URIS = " "

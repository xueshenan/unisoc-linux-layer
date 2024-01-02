CONFIG_SPRD_FIREWALL = "true"
TRUSTY_PRODUCTION_VERSION = "2"

SML_TARGET_DTB_PATH = "${OEROOT}/source/kernel/kernel5.15/arch/arm64/boot/dts/sprd/ums9620-base.dts"
SML_TARGET_DTBO_PATH = "${OEROOT}/layers/meta-unisoc/recipes-kernel/linux/linux-unisoc-5.15/ums9620/ums9620-2h10-overlay.dts"
TOS_TARGET_DTS = "${OEROOT}/source/kernel/kernel5.15/arch/arm64/boot/dts/sprd/ums9620-base.dts"

UFS_INLINE_CRYPTO = "true"

GLP_PROJECT = "true"

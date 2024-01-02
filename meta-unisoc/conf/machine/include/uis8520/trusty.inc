
BOARD_SECBOOT_CONFIG = "${@bb.utils.contains('SECBOOT_ENABLE','sec','true','false',d)}"
UNISOC_SIGN_ENABLE = "yes"
UNISOC_SIGN_PSS = "pss"
PROJECT_FLAG = "OTHER"
BSP_SPRD_SECUREBOOT_ALGORITHM = "rsa4096"
SECURE_WCN_GNSS_ENABLE = "${@bb.utils.contains('SECBOOT_ENABLE','sec','true','false',d)}"

### SECBOOT_ENABLE configuration which influence compile
### START secboot and vbootv2 configs for uboot
UBOOT_SECSET = "${@bb.utils.contains('SECBOOT_ENABLE','sec','-DCONFIG_SECBOOT -DCONFIG_SPRD_SECBOOT -DPKCS1_PSS_FLAG','',d)}"
UBOOT_SECSET += "${@bb.utils.contains('SECURE_WCN_GNSS_ENABLE','true','-DVERIFY_WCN_GNSS','',d)}"
### END secboot and vbootv2 configs for uboot

### START secboot and vbootv2 configs for chipram
CHIPRAM_SECSET = "${@bb.utils.contains('SECBOOT_ENABLE','sec','-DCONFIG_SECBOOT -DCONFIG_SPRD_SECBOOT -DPKCS1_PSS_FLAG','',d)}"
### END secboot and vbootv2 configs for chipram

### START secboot and vbootv2 configs for modem_ctrl
MODEM_SECSET = "${@bb.utils.contains('SECBOOT_ENABLE','sec','-DCONFIG_SECBOOT -DCONFIG_SPRD_SECBOOT','',d)}"
MODEM_LDKERNELBOOTCP = "${@bb.utils.contains('SECBOOT_ENABLE','sec','-lkernelbootcp','',d)}"
### END secboot and vbootv2 configs for modem_ctrl
### END BOARD_SECBOOT_CONFIG

### START SML/TEECFG/TOS configs ###
SML_VERSION = "1.4"
SML_CONFIG = "qogirn6pro@qogirn6pro"
TEE_CONFIG = "trusty"
SML_MEM_ADDR ?= "0xbd000000"
SML_MEM_SIZE ?= "0x00040000"

TEECFG_CUSTOM = "true"
TEECFG_MEM_ADDR ?= "0xbd040000"
TEECFG_MEM_SIZE ?= "0x20000"

TEE_64BIT = "true"
TRUSTY_DEFAULT_PROJECT = "sl9620"
TOS_MEM_ADDR ?= "0xbd060000"
TOS_MEM_SIZE ?= "0x01fa0000"


EXTRA_IMAGEDEPENDS += "sml"
EXTRA_IMAGEDEPENDS += "teecfg"
EXTRA_IMAGEDEPENDS += "trusty"
### END SML and TOS configs ###

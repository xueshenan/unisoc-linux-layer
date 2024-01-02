DESCRIPTION = "Use rootfs and kernel to generate recovery image"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

do_compile[depends] += "virtual/recovery-kernel:do_deploy"

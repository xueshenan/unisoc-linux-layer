DESCRIPTION = "Install signature key into rootfs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILE_PUBLIC_KEY := "${EXTERNALSRC}/public.pem"

inherit allarch

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_install() {
    install -d ${D}${sysconfdir}/swupdate_key
    install -m 0644 ${FILE_PUBLIC_KEY} ${D}${sysconfdir}/swupdate_key/
}

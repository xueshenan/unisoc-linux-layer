DESCRIPTION = "Unisoc teerpc module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "Apache-2.0"

PACKAGES =+ "${PN}-teerpc"
SECTION = "bins"
DEPENDS = "libtrusty"
DEPENDS += "libteec"
RDEPENDS_${PN} = "libtrusty ${PN}-teerpc"
RDEPENDS_${PN} += "libteec ${PN}-teerpc"
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/trusty/tee_rpc;protocol=ssh;branch=unc_linux_trunk"
PROVIDES = "teerpc"

EXTERNALSRC = "${OEROOT}/source/unisoc/trusty/tee_rpc/unclinux"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit systemd update-rc.d

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "teerpc.service"

INITSCRIPT_PACKAGES = "${PN}-teerpc"
INITSCRIPT_NAME_${PN}-teerpc = "teerpc-init"
INITSCRIPT_PARAMS_${PN}-teerpc = "start 59 3 4 5 . stop 59 0 6 ."

INSANE_SKIP_${PN} += "installed-vs-shipped"

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/teerpc ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${THISDIR}/files/teerpc-init ${D}/${sysconfdir}/init.d/teerpc-init

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${THISDIR}/files/teerpc.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = "${bindir}/teerpc \
                ${systemd_unitdir}/system/teerpc.service"
FILES_${PN}-teerpc = "${sysconfdir}/init.d/teerpc-init"

TARGET_CC_ARCH += "${LDFLAGS}"

DESCRIPTION = "Unisoc netfuns module"
HOMEPAGE = "http://www.unisoc.com/"
SECTION = "bins"
LICENSE = "Apache2.0"

DEPENDS = ""
LIC_FILES_CHKSUM = ""
PROVIDES = "ipv6drophop"
PV = "0.1"
PR = "r0"
#inherit systemd update-rc.d

EXTERNALSRC = "${OEROOT}/source/unisoc/netfuns/ipv6drophop"
EXTERNALSRC_BUILD = "${OEROOT}/source/unisoc/netfuns/ipv6drophop"

#SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/netfuns/ipv6drophop;protocol=ssh;branch=unc_linux_trunk"

#INITSCRIPT_NAME = "ipv6drophop-init "
#INITSCRIPT_PARAMS = "defaults 50"

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/ipv6drophop ${D}${bindir}/
}

TARGET_CC_ARCH += "${LDFLAGS}"
NATIVE_SYSTEMD_SUPPORT = "1"

DESCRIPTION = "assign the Qt5 font path"
SECTION = "qt5fontdir"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PV = "0.1"
PR = "r0"
inherit autotools
PACKAGES = "qt5fontdir \
"
FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI = " \
        file://fontdir-qt5.sh \
          "
PROVIDES = "qt5fontdir"
RDEPENDS_${PN} = "qt5fontdir \
                  liberation-fonts \
                 "

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}
do_install () {
    install -d ${D}${sysconfdir}/profile.d
    install -m 755 ${WORKDIR}/fontdir-qt5.sh ${D}${sysconfdir}/profile.d
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} = " \
    ${sysconfdir}/profile.d/fontdir-qt5.sh \
"

DESCRIPTION = "qtdemotest tools"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "bin"

DEPENDS ="dbus glib-2.0 gtest"
RDEPENDS_${PN} = "dbus glib-2.0 gtest"


PV = "0.2"
PR = "r1"
inherit autotools
PACKAGES = "qtdemotest \
"
#FILESEXTRAPATHS_prepend := "${THISDIR}:"
#SRC_URI = ""
PROVIDES = "qtdemotest"

RDEPENDS_${PN} = "\
                  qtbase \
                  qtquickcontrols \
                  qtquickcontrols2 \
                  qtdeclarative-qmlplugins \
                  qtquickcontrols-qmlplugins \
                 "
EXTERNALSRC = "${OEROOT}/layers/meta-unisoc-test/recipes-graphics/qtdemo/itest"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"


INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
    make clean
    make
}
do_install () {
    install -d ${D}${bindir}/utit/
    install -m 0755 ${OEROOT}/layers/meta-unisoc-test/recipes-graphics/qtdemo/itest/qtdemotest ${D}${bindir}/utit/
}


TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} += "${bindir}/utit/qtdemotest "
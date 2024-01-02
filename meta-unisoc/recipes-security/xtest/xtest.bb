DESCRIPTION = "Unisoc trusty xtest module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
DEPENDS = "\
     libteec \
"
DEPENDS += "openssl"
RDEPENDS_${PN} = "\
     libteec \
     libcrypto \
"
SECTION = "bins"

EXTERNALSRC = "${OEROOT}/source/unisoc/proprietories/security/optee_test/unclinux/"

inherit get_release_mode
XTEST_SRC_DIR = "${OEROOT}/source/unisoc/proprietories/security/optee_test/unclinux/"
XTEST_INSTALL_DIR = "${DEPLOY_OUT_DIR}/xtest"
XTEST_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/xtest"
export XTEST_SRC_MODE = "${@get_release_mode(d,"${XTEST_SRC_DIR}")}"
EXTERNALSRC = "${@bb.utils.contains("XTEST_SRC_MODE", "customer", "${XTEST_RELEASE_DIR}", "${XTEST_SRC_DIR}", d)}"
EXTERNALSRC_BUILD = "${@bb.utils.contains("XTEST_SRC_MODE", "customer", "${XTEST_RELEASE_DIR}", "${XTEST_SRC_DIR}", d)}"
LIC_FILES_CHKSUM = "file://COPYING;md5=dce8ff4bf27149d72c9ef7cdbf0c346a"
PROVIDES = "xtest"

do_compile () {
    if [ ${XTEST_SRC_MODE} != "customer" ]; then
           make clean
           make   
    fi
}

do_install () {
    if [ ${XTEST_SRC_MODE} != "customer" ]; then
          install -d ${D}${bindir}/
          install -m 0755 ${S}/xtest ${D}${bindir}/
    fi
}

FILES_${PN} += "${bindir}/xtest"
TARGET_CC_ARCH += "${LDFLAGS}"

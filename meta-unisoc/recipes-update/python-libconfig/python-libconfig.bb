DESCRIPTION = "Python bindings for libconfig"
SECTION = "devel/python"
LICENSE = "BSD"
DEPENDS = "boost libconfig"
RDEPENDS_${PN} = "boost libconfig"
SRCNAME = "pylibconfig"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

SRC_URI = "git://github.com/cnangel/python-libconfig.git;protocol=git \
           file://0001-rename-boost-python-library.patch \
"

S = "${WORKDIR}/git"

SRCREV = "2f0c9803c98871ff28144f6001442ddcb2762933"
PR ="r01"

inherit setuptools3 pkgconfig
BBCLASSEXTEND = "native"

#do_compile_prepend_class-target() {
#    ln -s ${STAGING_DIR_TARGET}//usr/lib/libboost_python38.so ${STAGING_DIR_TARGET}//usr/lib/libboost_python3.so
#}

#do_compile_prepend_class-native() {
#    ln -s ${STAGING_DIR_NATIVE}//usr/lib/libboost_python38.so ${STAGING_DIR_NATIVE}//usr/lib/libboost_python3.so
#}


do_install_append() {
    cd ${D}/${PYTHON_SITEPACKAGES_DIR}
    ln -s pylibconfig*.so pylibconfig.so
}

INSANE_SKIP_${PN} = "dev-so"

DESCRIPTION = "Unisoc LTP"
HOMEPAGE = "https://linux-test-project.github.io/"
SECTION = "console/utils"
LICENSE = "CLOSED"

DEPENDS = "attr libaio libcap acl openssl zip-native"
DEPENDS_append_libc-musl = " fts "
EXTRA_OEMAKE_append_libc-musl = " LIBC=musl "
EXTRA_OECONF_append_libc-musl = " LIBS=-lfts "
CFLAGS_append_x86-64 = " -fomit-frame-pointer"

CFLAGS_append_powerpc64 = " -D__SANE_USERSPACE_TYPES__"
CFLAGS_append_mipsarchn64 = " -D__SANE_USERSPACE_TYPES__"

SRC_URI = ""
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

inherit get_release_mode

LTP_SRC_DIR = "${OEROOT}/source/unisoc/test/ltp_1.21"
LTP_INSTALL_DIR = "${DEPLOY_OUT_DIR}"
LTP_RELEASE_DIR = "${PREBUILTS_OUT_DIR}/ltp"


LTP_SRC_MODE="${@get_release_mode(d, "${LTP_SRC_DIR}")}"

EXTERNALSRC_DIR = "${@bb.utils.contains("LTP_SRC_MODE", "customer", "${LTP_RELEASE_DIR}", "${LTP_SRC_DIR}", d)}"


EXTERNALSRC = "${EXTERNALSRC_DIR}"

EXTERNALSRC_BUILD = "${EXTERNALSRC_DIR}"


TARGET_CC_ARCH += "${LDFLAGS}"

export prefix = "/opt/${PN}"
export exec_prefix = "/opt/${PN}"

PACKAGECONFIG[numa] = "--with-numa, --without-numa, numactl,"
EXTRA_AUTORECONF += "-I ${S}/testcases/realtime/m4"
EXTRA_OECONF = " --with-power-management-testsuite --with-realtime-testsuite --with-open-posix-testsuite "
# ltp network/rpc test cases ftbfs when libtirpc is found
EXTRA_OECONF += " --without-tirpc "


do_configure () {
    if [ ${LTP_SRC_MODE} != "customer" ]; then
        autotools_do_configure
    fi
}

do_compile(){
  if [ ${LTP_SRC_MODE} != "customer" ]; then
    oe_runmake HOSTCC="${CC_FOR_BUILD}" HOST_CFLAGS="$CFLAGS_FOR_BUILD" HOST_LDFLAGS="${LDFLAGS_FOR_BUILD}"
  fi
}


do_install(){
  if [ ${LTP_SRC_MODE} != "customer" ]; then
    install -d ${D}${prefix}/
    oe_runmake DESTDIR=${D} SKIP_IDCHECK=1 install

    # fixup not deploy STPfailure_report.pl to avoid confusing about it fails to run
    # as it lacks dependency on some perl moudle such as LWP::Simple
    # And this script previously works as a tool for analyzing failures from LTP
    # runs on the OSDL's Scaleable Test Platform (STP) and it mainly accesses
    # http://khack.osdl.org to retrieve ltp test results run on
    # OSDL's Scaleable Test Platform, but now http://khack.osdl.org unaccessible
    rm -rf ${D}${prefix}/bin/STPfailure_report.pl

    # Copy POSIX test suite into ${D}${prefix}/testcases by manual
    cp -r testcases/open_posix_testsuite ${D}${prefix}/testcases

    # Makefile were configured in the build system
    find ${D}${prefix} -name Makefile | xargs -n 1 sed -i \
         -e 's@[^ ]*-fdebug-prefix-map=[^ "]*@@g' \
         -e 's@[^ ]*-fmacro-prefix-map=[^ "]*@@g' \
         -e 's@[^ ]*--sysroot=[^ "]*@@g'

    # The controllers memcg_stree test seems to cause us hangs and takes 900s
    # (maybe we expect more regular output?), anyhow, skip it
    sed -e '/^memcg_stress/d' -i ${D}${prefix}/runtest/controllers
  else
    install -d ${D}${sysconfdir}/ltp/
    install -m 0777 ${THISDIR}/files/TODO ${D}${sysconfdir}/ltp/TODO
  fi
}

RDEPENDS_${PN} = "\
    attr \
    bash \
    bc \
    coreutils \
    cpio \
    cronie \
    curl \
    e2fsprogs \
    e2fsprogs-mke2fs \
    expect \
    file \
    gawk \
    gdb \
    gzip \
    iproute2 \
    ldd \
    libaio \
    logrotate \
    perl \
    python3-core \
    procps \
    quota \
    unzip \
    util-linux \
    which \
    tar \
"

FILES_${PN} += "${prefix}/* ${prefix}/runtest/* ${prefix}/scenario_groups/* ${prefix}/testcases/bin/* ${prefix}/testcases/bin/*/bin/* ${prefix}/testscripts/* ${prefix}/testcases/open_posix_testsuite/* ${prefix}/testcases/open_posix_testsuite/conformance/* ${prefix}/testcases/open_posix_testsuite/Documentation/* ${prefix}/testcases/open_posix_testsuite/functional/* ${prefix}/testcases/open_posix_testsuite/include/* ${prefix}/testcases/open_posix_testsuite/scripts/* ${prefix}/testcases/open_posix_testsuite/stress/* ${prefix}/testcases/open_posix_testsuite/tools/* ${prefix}/testcases/data/nm01/lib.a ${prefix}/lib/libmem.a"

# Avoid stripping some generated binaries otherwise some of the ltp tests such as ldd01 & nm01 fail
INHIBIT_PACKAGE_STRIP_FILES = "${prefix}/testcases/bin/nm01 ${prefix}/testcases/bin/ldd01"
INSANE_SKIP_${PN} += "already-stripped staticdev"

# Avoid file dependency scans, as LTP checks for things that may or may not
# exist on the running system.  For instance it has specific checks for
# csh and ksh which are not typically part of OpenEmbedded systems (but
# can be added via additional layers.)
SKIP_FILEDEPS_${PN} = '1'
INSANE_SKIP_${PN} += "installed-vs-shipped"

DESCRIPTION = "Unisoc nvmerge for ota"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "CLOSED"
SECTION = "bin"
DEPENDS = ""
LIC_FILES_CHKSUM = ""
PV = "0.1"
PR = "r0"
PROVIDES = "nvmerge"

EXTERNALSRC_BUILD = "${EXTERNALSRC}"

do_compile_prepend (){
    SUPPORT_AB_PARTITIONS_FLAGS=""
    if [ ${AB_PARTITIONS} = "true" ]; then
        SUPPORT_AB_PARTITIONS_FLAGS="-DAB_PARTITIONS"
        export SUPPORT_AB_PARTITIONS_FLAGS
    fi
}

do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0777 ${S}/nvmerge ${D}${bindir}/
}
TARGET_CC_ARCH += "${LDFLAGS}"

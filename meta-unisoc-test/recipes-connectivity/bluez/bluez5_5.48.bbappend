DEPENDS += "gtest"

do_compile_append(){
    echo "bluez_itest"
    mkdir -p ${WORKDIR}/build/bluez_itest/
    cd ${OEROOT}/layers/meta-unisoc-test/recipes-connectivity/bluez/bluez_itest/
    make clean
    make WORKDIR=${WORKDIR}
}

do_install_append(){
    install -d ${D}${bindir}/utit/
    install -m 0755 ${OEROOT}/layers/meta-unisoc-test/recipes-connectivity/bluez/bluez_itest/bluez_itest ${D}${bindir}/utit/
}

TARGET_CC_ARCH += "${LDFLAGS}"
DEPENDS +="gtest"

do_compile_append(){
    cd ${OEROOT}/layers/meta-unisoc-test/recipes-connectivity/ATRouter/UTIT
    make clean 
    make BUILDDIR=${OEROOT}/source/unisoc/atrouter2.0
}

do_install_append(){
	install -d ${D}${bindir}/utit/
	install -m 0755 ${OEROOT}/layers/meta-unisoc-test/recipes-connectivity/ATRouter/UTIT/atroutergtest ${D}${bindir}/utit/
}

TARGET_CC_ARCH += "${LDFLAGS}"

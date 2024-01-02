DEPENDS += "gtest"

do_compile_append(){
	mkdir -p ${WORKDIR}/build/itest/
	cd ${OEROOT}/layers/meta-unisoc-test/recipes-connectivity/connman/itest/
	make clean
	make WORKDIR=${WORKDIR}
}

do_install_append(){
	install -d ${D}${bindir}/utit/
	install -m 0755 ${OEROOT}/layers/meta-unisoc-test/recipes-connectivity/connman/itest/connman_itest ${D}${bindir}/utit/
}

TARGET_CC_ARCH += "${LDFLAGS}"

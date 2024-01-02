DEPENDS +="gtest"

do_compile_append(){
	cd ${OEROOT}/layers/meta-unisoc-test/recipes-support/INIreader/UTIT
	make clean
	make BUILDDIR=${OEROOT}/source/unisoc/iniconfig/src
}

TARGET_CC_ARCH += "${LDFLAGS}"

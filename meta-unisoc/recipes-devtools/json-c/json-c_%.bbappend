FILES_${PN} += "${libdir}/libjson-c.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
INHIBIT_PACKAGE_DEBUG_SPLIT ="1"
INHIBIT_PACKAGE_STRIP ="1"
TARGET_CC_ARCH += "${LDFLAGS}"

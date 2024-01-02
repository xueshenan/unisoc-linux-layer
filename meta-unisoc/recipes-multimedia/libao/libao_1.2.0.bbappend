do_install_append() {
    install -d ${D}${libdir}/ao
    install -d ${D}${libdir}/ao/plugins-4
	install -m 0644 ${B}/src/plugins/alsa/.libs/libalsa.so ${D}${libdir}/ao/plugins-4/
}
FILES_${PN} += "${libdir}/ao/plugins-4/libalsa.so"

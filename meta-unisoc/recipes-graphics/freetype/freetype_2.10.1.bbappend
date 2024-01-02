do_install_append(){
    install -d ${D}${includedir}/
    install -m 0777 ${S}/include/ft2build.h ${D}${includedir}/
    install -d ${D}${includedir}/freetype
    install -m 0777 ${S}/include/freetype/*.h ${D}${includedir}/freetype
    install -d ${D}${includedir}/freetype/config
    install -m 0777 ${S}/include/freetype/config/*.h ${D}${includedir}/freetype/config
}

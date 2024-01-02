DESCRIPTION = "lvgl module"
HOMEPAGE = "http://www.unisoc.com/"
LICENSE = "MIT"
SECTION = "libs"
PROVIDES = "lvgl"
DEPENDS = "libpng libsdl2 freetype"

LIC_FILES_CHKSUM =  "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/lvgl/lvgl/tree/v${PV};downloadfilename=${BPN}-${PV}.tar.gz \
           file://0000-add-makefile.patch \
           file://0001-add-lvconf.patch \
           file://0002-modify-lvconf_internal.patch \
           file://0003-modify-lv_font_mk.patch \
           file://0004-add-lv_font_chinese_19.patch \
           file://0005-modify-lvgl_h.patch \
           file://0006-add-lv_multimedia_c.patch \
           file://0007-add-lv_multimedia_h.patch \
           file://0008-modify-lv_widgets_mk.patch \
           file://0009-append-lv_conf.patch \
           file://0010-add-lv_draw_blend_c.patch \
           file://0011-add-lv_obj_h.patch \
           file://0012-modify-lvconf_h.patch \
           file://0013-modify-lvconf_internal_h.patch \
           file://0014-modify-lvconf_h-for-add-lv_libs_freetype.patch \
           file://0015-modify-lvgl_h-for-add-lv_libs_freetype.patch \
           file://0016-modify-lvgl_mk-for-add-lv_libs_freetype.patch \
           file://0017-modify-lvconf_internal_h-for-add-lv_libs_freetype.patch \
           file://0018-add-lv_extra_c.patch \
           file://0019-add-lv_extra_h.patch \
           file://0020-add-extra_mk.patch \
           file://0021-add-lv_libs_h.patch \
           file://0022-add-lv_freetype_c.patch \
           file://0023-add-lv_freetype_h.patch \
           file://0024-modify-Makefile-for-freetype.patch \
           file://0025-add-LV_USE_USER_DATA.patch \
           file://0026-modify-lv_conf_internal_h-for-add-lv_use_user_data.patch \
          "
SRC_URI[md5sum] = "33a0efefd57db6288c3b7b8f45ccf387"
SRC_URI[sha256sum] = "17b8d321e0b86b23ed912f10622522864ed48a97e80e6fe28b11773d25c7b782"


do_compile () {
    make clean
    make
}

do_install () {
    install -d ${D}${libdir}
    install -m 0755 ${S}/liblvgl.so ${D}${libdir}/
    install -d ${D}${includedir}/
    install -m 0777 ${S}/*.h ${D}${includedir}/
    install -d ${D}${includedir}/src
    install -m 0777 ${S}/src/*.h ${D}${includedir}/src
    install -d ${D}${includedir}/src/lv_draw
    install -m 0777 ${S}/src/lv_draw/*.h ${D}${includedir}/src/lv_draw
    install -d ${D}${includedir}/src/lv_font
    install -m 0777 ${S}/src/lv_font/*.h ${D}${includedir}/src/lv_font
    install -d ${D}${includedir}/src/lv_misc
    install -m 0777 ${S}/src/lv_misc/*.h ${D}${includedir}/src/lv_misc
    install -d ${D}${includedir}/src/lv_core
    install -m 0777 ${S}/src/lv_core/*.h ${D}${includedir}/src/lv_core
    install -d ${D}${includedir}/src/lv_widgets
    install -m 0777 ${S}/src/lv_widgets/*.h ${D}${includedir}/src/lv_widgets
    install -d ${D}${includedir}/src/lv_hal
    install -m 0777 ${S}/src/lv_hal/*.h ${D}${includedir}/src/lv_hal
    install -d ${D}${includedir}/src/lv_gpu
    install -m 0777 ${S}/src/lv_gpu/*.h ${D}${includedir}/src/lv_gpu
    install -d ${D}${includedir}/src/lv_themes
    install -m 0777 ${S}/src/lv_themes/*.h ${D}${includedir}/src/lv_themes
    install -d ${D}${includedir}/src/extra
    install -m 0777 ${S}/src/extra/*.h ${D}${includedir}/src/extra
    install -d ${D}${includedir}/src/extra/libs
    install -m 0777 ${S}/src/extra/libs/*.h ${D}${includedir}/src/extra/libs
    install -d ${D}${includedir}/src/extra/libs/freetype
    install -m 0777 ${S}/src/extra/libs/freetype/*.h ${D}${includedir}/src/extra/libs/freetype
    install -d ${D}${includedir}/examples/porting
    install -m 0777 ${S}/examples/porting/*.h ${D}${includedir}/examples/porting
}

FILES_${PN} += "${libdir}/liblvgl.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
TARGET_CC_ARCH += "${LDFLAGS}"

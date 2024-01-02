SUMMARY = "ARM Mali-Midgard GPU User Mode Drivers"
LICENSE = "CLOSED"
PV = "r28"
PR = "p0"

# needed for qtbase compilation
DEPENDS += "wayland libdrm"

# provide aliases for libmidgard
PROVIDES = "virtual/egl \
            virtual/libgl \
            virtual/libgles1 \
            virtual/libgles2 \
            virtual/libgbm \
            virtual/mesa \
"

SRC_URI = "ssh://gitadmin@gitmirror.unisoc.com/yocto/unisoc/gpu;protocol=ssh;branch=unc_linux_trunk"

do_compile () {
}

do_install () {

    install -d ${D}${includedir}
    install -d ${D}${libdir}
    install -d ${D}${libdir}/pkgconfig

    cp ${S}/include/* ${D}${includedir} -r

    case "${TARGET_ARCH}" in
        "aarch64")
            install -m 0755 ${S}/lib64/*.so*  ${D}${libdir}/
            install -m 0644 ${S}/lib64/pkgconfig/*  ${D}${libdir}/pkgconfig/
            ;;
        "arm")
            install -m 0755 ${S}/lib32/*.so*  ${D}${libdir}/
            install -m 0644 ${S}/lib32/pkgconfig/*  ${D}${libdir}/pkgconfig/
            ;;
        *)
            ;;
    esac

    cd ${D}${libdir}

    ln -sf libEGL.so.1.4.0 libEGL.so.1
    ln -sf libEGL.so.1 libEGL.so

    ln -sf libgbm.so.1.0.0 libgbm.so.1
    ln -sf libgbm.so.1 libgbm.so

    ln -sf libGLESv1_CM.so.1.1.0 libGLESv1_CM.so.1
    ln -sf libGLESv1_CM.so.1 libGLESv1_CM.so

    ln -sf libGLESv2.so.2.1.0 libGLESv2.so.2
    ln -sf libGLESv2.so.2 libGLESv2.so

    ln -sf libmali.so.0.28.0 libmali.so.0
    ln -sf libmali.so.0 libmali.so

    ln -sf libwayland-egl.so.1.0.0 libwayland-egl.so.1
    ln -sf libwayland-egl.so.1 libwayland-egl.so
}

# auto package into rootfs
FILES_${PN} += "${libdir}/* ${includedir}/*"

# avoid ERROR for "libmidgard was already stripped".
INSANE_SKIP_${PN} = "ldflags already-stripped"

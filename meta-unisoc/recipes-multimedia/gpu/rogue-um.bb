SUMMARY = "Imagination PowerVR Rogue GPU User Mode Driver"
LICENSE = "CLOSED"
PV = "1.11RTM2"
PR = "5516664"

# Avoid WARNING for "QA Issue: rogue-um rdepends on xxx, but it isn't a build dependency"
DEPENDS += "wayland libdrm"

# provide aliases for rogue-um
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
    install -d ${D}${libdir}/dri
    install -d ${D}${libdir}/pkgconfig
    install -d ${D}/lib/firmware

    cp ${S}/include/* ${D}${includedir} -r
    install -m 0644 ${S}/${GPU_PLATFORM}/firmware/*  ${D}/lib/firmware/

    case "${TARGET_ARCH}" in
        "aarch64")
            install -m 0755 ${S}/${GPU_PLATFORM}/lib64/*.so*  ${D}${libdir}/
            install -m 0644 ${S}/${GPU_PLATFORM}/lib64/dri/*  ${D}${libdir}/dri/
            install -m 0644 ${S}/${GPU_PLATFORM}/lib64/pkgconfig/*  ${D}${libdir}/pkgconfig/
            ;;
        "arm")
            ;;
        *)
            ;;
    esac

    cd ${D}${libdir}

    # This libwayland-egl.so was just copied from mesa-18.1.3 built in yocto. After
    # mesa-18.2, this library was splited from mesa into wayland project. When the
    # PREFERRED_PROVIDER_virtual/mesa was set to "rogue-um" in the machine conf file,
    # the default mesa-18.1.3 will be replaced by mesa-19.0.1 from rogue ddk which
    # has no libwayland-egl.so. So add this library to avoid compilation errors for
    # qtbase which was depend on the libwayland-egl.so.

    ln -sf libwayland-egl.so.1.0.0 libwayland-egl.so.1
    ln -sf libwayland-egl.so.1 libwayland-egl.so

    # The following libraries were built on mesa-19.0.1 with imgetc pvr drivers.

    ln -sf libglapi.so.0.0.0 libglapi.so.0
    ln -sf libglapi.so.0 libglapi.so

    ln -sf libEGL.so.1.0.0 libEGL.so.1
    ln -sf libEGL.so.1 libEGL.so

    ln -sf libgbm.so.1.0.0 libgbm.so.1
    ln -sf libgbm.so.1 libgbm.so

    ln -sf libGLESv1_CM.so.1.0.0 libGLESv1_CM.so.1
    ln -sf libGLESv1_CM.so.1 libGLESv1_CM.so

    ln -sf libGLESv2.so.2.0.0 libGLESv2.so.2
    ln -sf libGLESv2.so.2 libGLESv2.so

    ln -sf libGLESv1_CM_PVR_MESA.so.1.11.5516664 libGLESv1_CM_PVR_MESA.so
    ln -sf libGLESv2_PVR_MESA.so.1.11.5516664 libGLESv2_PVR_MESA.so
    ln -sf libglslcompiler.so.1.11.5516664 libglslcompiler.so
    ln -sf libpvr_dri_support.so.1.11.5516664 libpvr_dri_support.so
    ln -sf libPVRScopeServices.so.1.11.5516664 libPVRScopeServices.so
    ln -sf libsrv_um.so.1.11.5516664 libsrv_um.so
    ln -sf libsutu_display.so.1.11.5516664 libsutu_display.so
    ln -sf libusc.so.1.11.5516664 libusc.so

    # OpenCL feature
    ln -sf libdnngraphgen.so.1.11.5516664 libdnngraphgen.so
    ln -sf libufwriter.so.1.11.5516664 libufwriter.so
    ln -sf libIMGDNN.so.1.11.5516664 libIMGDNN.so
    ln -sf libPVRCLDNN.so.1.11.5516664 libIMGDNN.so
    ln -sf libPVROCL.so.1.11.5516664 libPVROCL.so
    ln -sf libPVROCL.so libOpenCL.so
}

# auto package into rootfs
FILES_${PN} += "${libdir}/* ${includedir}/* /lib/firmware/*"

# avoid "ERROR: No GNU_HASH in the elf binary"
INSANE_SKIP_${PN} = "ldflags"

# avoid "ERROR: QA Issue: Architecture did not match (MIPS, expected AArch64)" for rgx firmware installation
INSANE_SKIP_${PN} += "arch"

# avoid "already stripped" error for libwayland-egl.so
INSANE_SKIP_${PN} += "already-stripped"

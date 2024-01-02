FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:${COREBASE}/meta/recipes-graphics/${BPN}/files:"

SRC_URI = "https://mesa.freedesktop.org/archive/mesa-${PV}.tar.xz \
           file://0001-meson.build-check-for-all-linux-host_os-combinations.patch \
           file://0002-meson.build-make-TLS-ELF-optional.patch \
           file://0003-Allow-enable-DRI-without-DRI-drivers.patch \
           file://0004-Revert-mesa-Enable-asm-unconditionally-now-that-gen_.patch \
           file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
           "

SRC_URI[sha256sum] = "cce001b685d23afb976b04138714906abcf7e7f996da6355e6a43e5ca486533d"

SRC_URI_remove = "file://0001-freedreno-clear-last_fence-after-resource-tracking.patch"
PLATFORMS_remove = "drm surfaceless"

require recipes-graphics/mesa/${BPN}.inc

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=9aa1bc48c9826ad9fdb16661f6930496"


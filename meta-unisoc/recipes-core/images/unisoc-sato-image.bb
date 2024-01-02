require unisoc-console-image.bb

SUMMARY = "Basic wayland image"

LICENSE = "MIT"

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"


TOOLCHAIN_HOST_TASK_append = " nativesdk-intltool nativesdk-glib-2.0"
TOOLCHAIN_HOST_TASK_remove_task-populate-sdk-ext = " nativesdk-intltool nativesdk-glib-2.0"


CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-unisoc-sato \
"

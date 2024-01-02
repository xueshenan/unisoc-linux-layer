
### For GPU ###
GPU_PLATFORM = ""
GPU_RGX_BVNC = ""

# use libnatt instead of mesa for EGL & GLES libraries
PREFERRED_PROVIDER_virtual/egl = "libnatt"
PREFERRED_PROVIDER_virtual/libgl = "libnatt"
PREFERRED_PROVIDER_virtual/libgles1 = "libnatt"
PREFERRED_PROVIDER_virtual/libgles2 = "libnatt"
PREFERRED_PROVIDER_virtual/libgbm = "libnatt"
PREFERRED_PROVIDER_virtual/mesa = "libnatt"

### End GPU ###


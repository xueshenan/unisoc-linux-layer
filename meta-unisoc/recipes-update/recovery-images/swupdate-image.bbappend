include swupdate-image-unisoc.inc
KERNELDEPMODDEPEND = "virtual/recovery-kernel:do_packagedata"
do_build[depends] = "virtual/recovery-kernel:do_deploy"

# the inc file store commone ext4 rootfs configuration for unisoc machine

### rootfs and partiton configuration
# Define the partition and file system
BASE_FS_TYPE = "ext4"
IMAGE_OVERHEAD_FACTOR = "1.2"

# Fastboot expects an ext4 image, which needs to be 4096 bytes aligned
IMAGE_FSTYPES_append = " ext4.gz"
IMAGE_ROOTFS_ALIGNMENT = "2048"
#EXTRA_IMAGECMD_ext4 += " -L rootf "
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_ROOTFS_SIZE = "0"
EXTRA_IMAGE_FEATURES += " read-only-rootfs "

# for mount, fs_tab
USERDATA_PARTITION ?= " /dev/userdata /mnt/userdata ext4 "
PRODNV_PARTITION = " /dev/mmcblk0p1 /etc/productinfo ext4 "
SDCARD_PARTITION = " /dev/mmcblk1p1 /mnt/media auto "
HOME_DIR = " /mnt/userdata/home /home none "
VAR_DIR = "${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs',' /mnt/userdata/var /var none ', '',d)}"
DATA_DIR = " /mnt/userdata/data /mnt/data none "
EXTRA_PARTITIONS ?= "${USERDATA_PARTITION}""${PRODNV_PARTITION}""${DATA_DIR}""${HOME_DIR}""${VAR_DIR}"

USERDATA_FSTYPES = "ext4"
USERDATA_SIZE = "81920"

PRODNV_FSTYPES = "ext4"
PRODNV_SIZE = "4096"

STORAGE_TYPE = "emmc"

EXTRA_IMAGECMD_ext4_pn-unisoc-wayland-image += " -O ^has_journal"



IMAGE_FSTYPES_append = " ubifs "
MKUBIFS_ARGS ?= "-F -m 4096 -e 253952 -c 4000"
UBINIZE_ARGS ?= "-m 4096 -p 256KiB -s 4096 -O 4096"
IMAGE_ROOTFS_ALIGNMENT = "4096"
EXTRA_IMAGE_FEATURES += " read-only-rootfs "

PRODNV_MKUBIFS_ARGS = ""
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_ROOTFS_SIZE = "0"
IMAGE_OVERHEAD_FACTOR = "1.1"

USERDATA_PARTITION = " /dev/ubi0_userdata /mnt/userdata ubifs "
PRODNV_PARTITION = " /dev/ubi0_prodnv /etc/productinfo ubifs "
SDCARD_PARTITION = " /dev/mmcblk0p1 /mnt/media auto "
HOME_DIR = " /mnt/userdata/home /home none "
VAR_DIR = "${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs',' /mnt/userdata/var /var none ', '',d)}"
DATA_DIR = " /mnt/userdata/data /mnt/data none "
EXTRA_PARTITIONS = "${USERDATA_PARTITION}""${PRODNV_PARTITION}""${DATA_DIR}""${HOME_DIR}""${VAR_DIR}"

USERDATA_MKUBIFS_ARGS = "-F -m 4096 -e 253952 -c 4000 -R 20MiB"
USERDATA_FSTYPES = "ubifs"
USERDATA_SIZE = "81920"

PRODNV_MKUBIFS_ARGS = "-F -m 4096 -e 253952 -c 42"
PRODNV_FSTYPES = "ubifs"
PRODNV_SIZE = "2980"

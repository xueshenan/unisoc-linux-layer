DESCRIPTION = "source-hansans-cn-medium-otf"
PROVIDES = "source-hansans-cn-medium-otf"

HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans/"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/OFL-1.1;md5=fac3a519e5e9eb96316656e0ca4f2b90"
SRC_URI = "https://github.com/adobe-fonts/source-han-sans/tree/release/SubsetOTF/CN/source-hansans-cn-medium-otf-2.004.tar.xz"

SECTION_${PN} = "base"

SRC_URI[md5sum] = "806d8bfce67ee5295ec12c0380c26a36"
SRC_URI[sha256sum] = "6a9a34f3ced42f22b4eed14055a032168c0681b68486cba435dc6ec708bd1d75"

do_install() {
    install -d ${D}/usr/share/fonts
    install -d ${D}/usr/lib/fonts
    install -m 0777 ${S}/SourceHanSansCN-Medium.otf ${D}/usr/share/fonts
    install -m 0777 ${S}/SourceHanSansCN-Medium.otf ${D}/usr/lib/fonts
}
FILES_${PN} = " \
    /usr/share/fonts/SourceHanSansCN-Medium.otf \
    /usr/lib/fonts/SourceHanSansCN-Medium.otf \
"
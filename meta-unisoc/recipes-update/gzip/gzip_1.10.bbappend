FILESEXTRAPATHS_append := "${THISDIR}/${PN}:${THISDIR}/${PN}/default:${THISDIR}/files"
do_install_append() {
    if [ ${PN} != "gzip-native" ]; then
       install -m 755 ${WORKDIR}/image/bin/gzip ${THISDIR}/
    fi
}


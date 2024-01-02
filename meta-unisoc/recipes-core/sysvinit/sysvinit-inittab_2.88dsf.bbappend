FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://start_getty"

do_install_append() {
          install -m 0755 ${WORKDIR}/start_getty ${D}${base_bindir}/start_getty
}

deltask rm_work

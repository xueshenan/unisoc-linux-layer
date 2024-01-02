SUMMARY = "mcp nand param generate / UNISOC Development Platform"
LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/MODULE_LICENSE_APACHE2;md5=4b1b460c415a9776a62235557a7642b4"

DEPENDS += ""
SRCREV = "ff1e0e5e1627de4ce8e8ef5763b8821fb7cc74a4"
PV = "1.0"

SRC_URI = "file://MODULE_LICENSE_APACHE2 \
"

S = "${WORKDIR}"

do_compile () {
}

do_install () {
        install -d ${D}${bindir}/
        install -m 0644 ${S}/mcp.xls ${D}${bindir}/
        install -m 0755 ${S}/*.pl ${D}${bindir}/
        install -m 0755 ${S}/*.sh ${D}${bindir}/
        cp -av ${S}/Spreadsheet ${D}${bindir}/Spreadsheet
}

BBCLASSEXTEND = "native"

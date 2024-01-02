SUMMARY = "Flexible and efficient upload handling for Flask"
DESCRIPTION = "Flask-Uploads provides flexible upload handling for Flask \
applications. It lets you divide your uploads into sets that the application \
user can publish separately."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=b712ac634b39469660c9bdfb8d03421c"

SRC_URI[md5sum] = "e5eee34aa92b64a4d22847672b3858a1"
SRC_URI[sha256sum] = "53ecbd6033667d50ae02b63adebbaa33c7fc56c09e5293025810cf9d841ecb02"

PYPI_PACKAGE = "Flask-Uploads"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-flask \
    "

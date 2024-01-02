PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)} ssl libidn proxy threaded-resolver verbose zlib"
PACKAGECONFIG[gnutls] = ""
EXTRA_OECONF += " \
    --without-nss \
    --with-ssl=/usr/include/openssl \
"

SUMMARY = "sign UNISOC Development Platform"
LICENSE = "CLOSED"

do_compile () {
}

do_install () {

    install -d ${D}${bindir}/
    install -d ${D}${bindir}/config/
    install -m 0777 ${S}/config/aeskey ${D}${bindir}/config/
    install -m 0777 ${S}/config/aeskey_128 ${D}${bindir}/config/
    install -m 0666 ${S}/config/dynamic_ta_privatekey.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/genkey.sh ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_0.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_0_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_1.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_1_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_2.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_2_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_devkey_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_vdsp.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa2048_vdsp_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/gps_2048_private.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/gps_2048_public.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/wcn_2048_private.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/wcn_2048_public.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_0.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_0_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_1.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_1_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_2.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_2_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_devkey_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_boot.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/rsa4096_boot_pub.bin ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_modem.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/rsa4096_modem_pub.bin ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_product.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/rsa4096_product_pub.bin ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_recovery.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/rsa4096_recovery_pub.bin ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_system.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/rsa4096_system_pub.bin ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_vbmeta.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/rsa4096_vbmeta_pub.bin ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_vendor.pem ${D}${bindir}/config/
    install -m 0777 ${S}/config/rsa4096_vendor_pub.bin ${D}${bindir}/config/
    install -m 0666 ${S}/config/vdsp_firmware_privatekey.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_vdsp.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/rsa4096_vdsp_pub.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/gps_4096_private.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/gps_4096_public.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/wcn_4096_private.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/wcn_4096_public.pem ${D}${bindir}/config/
    install -m 0666 ${S}/config/version.cfg ${D}${bindir}/config/

    install -d ${D}${bindir}/dynamicTA
    install -d ${D}${bindir}/dynamicTA/genkey
    install -m 0777 ${S}/dynamicTA/genkey/main_process.py ${D}${bindir}/dynamicTA/genkey/
    install -m 0777 ${S}/dynamicTA/signta.py ${D}${bindir}/dynamicTA/

    install -m 0777 ${S}/imgheaderinsert ${D}${bindir}

    install -d ${D}${bindir}/lib64
    install -m 0666 ${S}/lib64/libc++.so ${D}${bindir}/lib64/

    install -d ${D}${bindir}/mkdbimg
    install -d ${D}${bindir}/mkdbimg/bin
    install -d ${D}${bindir}/mkdbimg/bin/lib64/
    install -m 0666 ${S}/mkdbimg/bin/lib64/libc++.so ${D}${bindir}/mkdbimg/bin/lib64/
    install -m 0777 ${S}/mkdbimg/bin/mkdbimg ${D}${bindir}/mkdbimg/bin/
    install -m 0777 ${S}/mkdbimg/bin/primary_debug.cert ${D}${bindir}/mkdbimg/bin/

    install -d ${D}${bindir}/mkdbimg/config
    install -m 0777 ${S}/mkdbimg/config/createdevkey.sh ${D}${bindir}/mkdbimg/config/
    install -m 0777 ${S}/mkdbimg/config/rsa2048_devkey.pem ${D}${bindir}/mkdbimg/config/
    install -m 0777 ${S}/mkdbimg/config/rsa2048_devkey_pub.pem ${D}${bindir}/mkdbimg/config/

    install -d ${D}${bindir}/mkdbimg/script
    install -m 0777 ${S}/mkdbimg/script/sprd_mkdbimg.sh ${D}${bindir}/mkdbimg/script/

    install -d ${D}${bindir}/mkprimarycert
    install -d ${D}${bindir}/mkprimarycert/bin
    install -d ${D}${bindir}/mkprimarycert/bin/lib64
    install -m 0666 ${S}/mkprimarycert/bin/lib64/libc++.so ${D}${bindir}/mkprimarycert/bin/lib64
    install -m 0777 ${S}/mkprimarycert/bin/mkprimarycert ${D}${bindir}/mkprimarycert/bin/

    install -d ${D}${bindir}/mkprimarycert/script
    install -m 0777 ${S}/mkprimarycert/script/sprd_mkprimarycert.sh ${D}${bindir}/mkprimarycert/script/

    install -m 0777 ${S}/packimage.sh ${D}${bindir}/
    install -m 0777 ${S}/sign_cp.sh ${D}${bindir}/
    install -m 0777 ${S}/sign_roc1.sh ${D}${bindir}/
    install -m 0777 ${S}/splitimg ${D}${bindir}/
    install -m 0777 ${S}/sprd_sign ${D}${bindir}/
    install -m 0777 ${S}/sprd_sign_v2 ${D}${bindir}/
    install -m 0777 ${S}/sign_8581cp.sh ${D}${bindir}/
    install -m 0777 ${S}/sign_9863_no_modem_cp.sh ${D}${bindir}/


}

BBCLASSEXTEND = "native"
deltask rm_work


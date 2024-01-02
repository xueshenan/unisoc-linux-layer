FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = " \
	file://wpa_supplicant.conf-unisoc \
	file://0001-Bug-1994432-update-conf-path-for-wpa_supplicant.patch \
	file://0002-Bug-2008111-SoftAp-start-with-acs-failed-on-wpa_supplicant.patch \
	file://0003-Bug-1999602-SoftAp-reset-if-disturb-channel.patch \
	file://0004-Bug-2086334-SoftAp-with-non-bridge-mode-reset.patch \
	file://0005-Bug-2114131-Softap-acs-scan-fetch-wrong-scan-results.patch \
	file://0006-Bug-2114140-SoftAp-bandwidth-not-excepted.patch \
	"
do_configure_append(){
	# Enable syslog instead of stdout
	echo "CONFIG_DEBUG_SYSLOG=y" >> wpa_supplicant/.config
	# Enable EAP-SIM
	echo "CONFIG_EAP_SIM=y" >> wpa_supplicant/.config
	# Enable EAP-AKA/AKA'
	echo "CONFIG_EAP_AKA=y" >> wpa_supplicant/.config
	echo "CONFIG_EAP_AKA_PRIME=y" >> wpa_supplicant/.config
	# Enable 80211w(PMF) support
	echo "CONFIG_IEEE80211W=y" >> wpa_supplicant/.config
	# Enable P2P support
	echo "CONFIG_P2P=y" >> wpa_supplicant/.config
	# Enable ACS
	echo "CONFIG_ACS=y" >> wpa_supplicant/.config
	# Enable 11N
	echo "CONFIG_IEEE80211N=y" >> wpa_supplicant/.config
	# Enable 80211ac
	echo "CONFIG_IEEE80211AC=y" >> wpa_supplicant/.config
}

do_install_append () {
	install -d ${D}/var/lib/wifi/
	install -m 644 ${WORKDIR}/wpa_supplicant.conf-unisoc ${D}/var/lib/wifi/wpa_supplicant.conf
}

do_compile_append(){
	for s_file in `find . -name "*.service"`
	do
		sed -i "s/wpa_supplicant -u$/wpa_supplicant -u -s -dd/" $s_file
	done
}
# Enable this for further debug
# deltask do_rm_work

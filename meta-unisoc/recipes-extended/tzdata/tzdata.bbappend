FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEFAULT_TIMEZONE = "PRC"

pkg_postinst_${PN} () {
	etc_lt="$D${sysconfdir}/localtime"
	src="$D${sysconfdir}/timezone"

	if [ -e ${src} ] ; then
		tz=$(sed -e 's:#.*::' -e 's:[[:space:]]*::g' -e '/^$/d' "${src}")
	fi
	
	if [ -z "${tz}" ] ; then
		exit 0
	fi
	
	if [ ! -e "$D${datadir}/zoneinfo/${tz}" ] ; then
		echo "You have an invalid TIMEZONE setting in ${src}"
		echo "Your ${etc_lt} has been reset to PRC; enjoy!"
		tz="PRC"
		echo "Updating ${etc_lt} with $D${datadir}/zoneinfo/${tz}"
		if [ -L ${etc_lt} ] ; then
			rm -f "${etc_lt}"
		fi
		ln -s "${datadir}/zoneinfo/${tz}" "${etc_lt}"
	fi
}


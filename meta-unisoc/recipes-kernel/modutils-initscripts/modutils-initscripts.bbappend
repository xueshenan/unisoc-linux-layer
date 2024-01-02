FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

INITSCRIPT_NAME = "modutils.sh"
INITSCRIPT_PARAMS = "start 06 S ."
inherit update-rc.d

DEPENDS += "desktop-file-utils-native"

# Let xterm install .desktop files
inherit mime-xdg

do_install_append() {
    oe_runmake install-desktop DESTDIR="${D}" DESKTOP_FLAGS="--dir=${D}${DESKTOPDIR}"
}

RPROVIDES_${PN} = "virtual/x-terminal-emulator"
ALTERNATIVES_${PN} += "x-terminal-emulator"
LTERNATIVE_TARGET[x-terminal-emulator] = "${bindir}/xterm"
# rxvt-unicode defaults to priority 10. Let's be one point lower to let it override xterm.
ALTERNATIVE_PRIORITY[x-terminal-emulator] = "9"

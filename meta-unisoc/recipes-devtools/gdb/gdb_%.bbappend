PACKAGECONFIG ?= "${@bb.utils.contains('MACHINE_FEATURES', 'withqt', 'readline python', '',d)}"

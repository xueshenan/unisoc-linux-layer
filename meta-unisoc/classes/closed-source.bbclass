inherit get_release_mode
 
MODULE_SRC_MODE ?= "none"
MODULE_USE_CMAKE ?= "0"
MODULE_USE_AUTOTOOL ?= "0"

python () {
    if bb.data.inherits_class('autotools-brokensep', d):
        bb.note("inherit from autotools!!!!")
        d.setVar('MODULE_USE_AUTOTOOL', '1')

    if bb.data.inherits_class('cmake', d):
        bb.note("inherit from cmake!!!!")
        d.setVar('MODULE_USE_CMAKE', '1')


    module_src_dir = d.getVar('MODULE_SRC_DIR')
    module_install_dir = d.getVar('MODULE_INSTALL_DIR')
    module_release_dir = d.getVar('MODULE_RELEASE_DIR')
    module_src_mode = get_src_mode(d, module_src_dir)

    bb.note("module_src_mode:%s" % module_src_mode)
    bb.note("module_src_dir:%s" % module_src_dir)
    bb.note("module_install_dir:%s" % module_install_dir)
    bb.note("module_release_dir:%s" % module_release_dir)

    d.setVar('MODULE_SRC_MODE', module_src_mode)
}


def get_external_src(d):
    module_src_dir = d.getVar('MODULE_SRC_DIR')
    module_install_dir = d.getVar('MODULE_INSTALL_DIR')
    module_release_dir = d.getVar('MODULE_RELEASE_DIR')
    module_src_mode = get_src_mode(d, module_src_dir)

    bb.note("module_src_mode:%s" % module_src_mode)
    bb.note("module_src_dir:%s" % module_src_dir)
    bb.note("module_install_dir:%s" % module_install_dir)
    bb.note("module_release_dir:%s" % module_release_dir)

    if "customer" in module_src_mode:
        return module_release_dir
    else:
        return module_src_dir


def get_external_src_build(d):
    module_src_dir = d.getVar('MODULE_SRC_DIR')
    module_install_dir = d.getVar('MODULE_INSTALL_DIR')
    module_release_dir = d.getVar('MODULE_RELEASE_DIR')
    module_src_mode = get_src_mode(d, module_src_dir)

    bb.note("module_src_mode:%s" % module_src_mode)
    bb.note("module_src_dir:%s" % module_src_dir)
    bb.note("module_install_dir:%s" % module_install_dir)
    bb.note("module_release_dir:%s" % module_release_dir)

    if "customer" in module_src_mode:
        return module_release_dir
    else:
        return module_src_dir

do_configure () {
    if [ ${MODULE_SRC_MODE} != "customer" ]; then

        if [ ${MODULE_USE_CMAKE} != "0" ]; then
            cmake_do_configure
        fi

        if [ ${MODULE_USE_AUTOTOOL} != "0" ]; then
            autotools_do_configure
        fi
    fi
}

do_compile () {
    if [ ${MODULE_SRC_MODE} != "customer" ]; then
        if [ ${MODULE_USE_CMAKE} != "0" ]; then
            cmake_do_compile
        else
            do_self_compile
        fi 
    fi
}

do_self_compile() {
}


def get_src_mode(d, src_dir):
    import os
    dir = d.expand(src_dir)
    bb.note("dir: %s" % dir)
    if not os.path.exists(dir):
        return "customer"
    else:
        return "none"


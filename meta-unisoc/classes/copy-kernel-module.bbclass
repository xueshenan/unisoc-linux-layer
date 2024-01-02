
inherit deploy

do_copy_kernel_module(){
    mkdir -p ${DEPLOY_DIR_IMAGE}/kernel_module
    cp -rf   ${WORKDIR}/image/* ${DEPLOY_DIR_IMAGE}/kernel_module
}


python __anonymous() {
    #addtask(task, before, after, d):
    bb.build.addtask('do_copy_kernel_module', 'do_package', 'do_install', d)
}

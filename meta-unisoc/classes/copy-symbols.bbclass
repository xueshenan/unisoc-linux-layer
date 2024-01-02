
inherit deploy
inherit base


do_copy_symbols(){
    mkdir -p ${DEPLOY_DIR_IMAGE}/app_symbols
    cp -rf   ${WORKDIR}/image/* ${DEPLOY_DIR_IMAGE}/app_symbols
}

addtask do_copy_symbols  after do_install  before do_package

EXPORT_FUNCTIONS do_copy_symbols

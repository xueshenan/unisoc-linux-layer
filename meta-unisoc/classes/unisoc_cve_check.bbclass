
# to avoid when do cve check task, fetch nvd_db
# because no network, if have network then remove unisoc_cve_check"

python (){
    if bb.data.inherits_class("cve-check", d):
        db_file = d.getVar("CVE_CHECK_DB_FILE")
        import os
        if os.path.isfile(db_file):
            # nvd_db exist, touch it
            os.system("touch %s" % db_file)
        else:
            bb.warn("nvd_db not exist,will fetch it!!!")
}


DESCRIPTION = "Unisoc Get Release Mode"

inherit base

def get_release_mode(d, src_dir):
    import os
    dir = d.expand(src_dir)
    if not os.path.exists(dir):
         return "customer"

export DEPLOY_OUT_DIR="${DEPLOY_DIR_IMAGE}/out"
export PREBUILTS_OUT_DIR="${OEROOT}/prebuilts/unisoc_bin/${MACHINE}/out"

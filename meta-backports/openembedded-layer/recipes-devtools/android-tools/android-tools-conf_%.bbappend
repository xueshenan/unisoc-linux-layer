python () {
    pn = d.getVar('PN')
    profprov = d.getVar("PREFERRED_PROVIDER_" + pn)
    if profprov and pn != profprov:
        raise bb.parse.SkipRecipe("PREFERRED_PROVIDER_%s set to %s, not %s" % (pn, profprov, pn))
}

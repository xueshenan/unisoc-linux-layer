# the inc file store commone configuration for unisoc machine
# and would be overwriten by <machine>.conf
# assign should use "?=" and in <machine>.conf use "=" to overwriten

### external src configuration
require conf/machine/include/unisoc-extern.inc

### bsp kernel configuration
require conf/machine/include/common/bsp-kernel.inc

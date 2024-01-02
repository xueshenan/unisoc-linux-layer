#!/bin/sh
#
#bind pcie epf
#
 echo "rcS:start bind pci epf"
 mkdir -p /sys/kernel/config/pci_ep/functions/pci_epf_sprd/func1
 echo 0x16c3 > /sys/kernel/config/pci_ep/functions/pci_epf_sprd/func1/vendorid
 echo 0xabcd > /sys/kernel/config/pci_ep/functions/pci_epf_sprd/func1/deviceid
 echo 16 > /sys/kernel/config/pci_ep/functions/pci_epf_sprd/func1/msi_interrupts
 ln -s /sys/kernel/config/pci_ep/functions/pci_epf_sprd/func1 /sys/kernel/config/pci_ep/controllers/2b100000.pcie1/
 echo "rcS:end bind pci epf"


import os, sys

def is_mounted(d):
    with open('/proc/mounts') as f:
        line = f.readline()
        while line:
            if line.startswith(d['dev']):
                return True
            line = f.readline()

    return False

def mount(d):
    if not os.path.exists(d['dir']):
        os.makedirs(d['dir'])

    if os.system('mount %s %s' % (d['dev'], d['dir'])) != 0:
        return False

    return True

def mount_all(disks):
    for d in disks:
        if is_mounted(disks[d]):
            print('%s mounted' % d)
            continue

        print('Mounting %s (%s => %s)' % (d, disks[d]['dev'], disks[d]['dir']))
        if not mount(disks[d]):
            return False

    return True

if __name__ == '__main__':
    test = {
        'emmc': {
            'dev': '/dev/mmcblk0p4',
            'dir': '/home',
        },
        'usb1': {
            'dev': '/dev/sda1',
            'dir': '/mnt/disk1',
        }
    }

    mount_all(test)

#!/usr/bin/env python3

import os, sys
import subprocess
import re
import time
import shutil
#import resource
import datetime

from runner import Runner, RunError
from disk import mount_all

# 8 hours
#MAX_DUR = 8 * 60 * 60
#MAX_DUR = 60
MAX_DUR = 60 * 60
LOG_DIR = "logs"
FILE_SIZE = 10 * 1024 * 1024   # 10MB
NFS_SERVER = '192.168.221.109:/home/demo/nfsroot'
NFS_MOUNT = '/tmp/nfs'
IPERF_SERVER = '192.168.221.109'
IPERF_BANDWIDTH = '800M'
VIDEO_SAMPLE = '/home/root/HDClub_H264_High@L5.1_3840x2160_29.970fps_15Mbsp_LC-AAC.mp4'

# The total memory used for memory stress test.
# Adjust the value to fit your environment.
MEM_SIZE = '700M'

# The ratio of available disk space used for testing.
# For large disk, smaller value can reduce time needed for test files
# initialization.
DISK_RATIO = 0.95

stressors = {
    'cpu': {
        'cmd': 'stress-ng --class cpu --seq 0 -t 1m',
    },
    'mem': {
        'cmd': 'stress-ng --vm 2 --vm-bytes %s' % MEM_SIZE,
    },
    'ethernet': {
        'cmd': 'iperf3 -c %s -u -b %s -t %d' % (IPERF_SERVER, IPERF_BANDWIDTH, MAX_DUR),
    },
#    'gpu': {
#        'cmd': 'glmark2-es2-wayland',
#    },
#    'apu': {
#        'cmd': 'python3 benchmark.py auto',
#        'cwd': '/usr/share/benchmark_dla',
#    },
    'video': {
        'cmd': 'gst-launch-1.0 -v filesrc location=%s ! parsebin ! v4l2h264dec ! v4l2convert output-io-mode=dmabuf-import capture-io-mode=dmabuf ! waylandsink' % VIDEO_SAMPLE,
    },
}
runners = []

setup_cmds = [
    'echo performance > /sys/devices/system/cpu/cpufreq/policy0/scaling_governor',
    'echo performance > /sys/devices/system/cpu/cpufreq/policy4/scaling_governor',
#    'echo performance > /sys/devices/platform/soc/13000000.mali/devfreq/13000000.mali/governor',
    'echo 0 > /sys/devices/virtual/thermal/thermal_zone1/thm_enable',
]

disks = {
    'emmc': {
        'dev': '/dev/mmcblk0p36',
        'dir': '/home',
    },
    #'usb1': {
    #    'dev': '/dev/sda1',
    #    'dir': '/mnt/disk1',
    #},
    #'usb2': {
    #    'dev': '/dev/sdb1',
    #    'dir': '/mnt/disk2',
    #},
    #'sd': {
    #    'dev': '/dev/mmcblk1p1',
    #    'dir': '/mnt/sd',
    #},
}

def log(msg):
    now = datetime.datetime.now()
    tmp = '[%s] %s' % (now.strftime('%m-%d %H:%M:%S'), msg)
    print(tmp)

def is_nfs_mounted():
    with open('/proc/mounts') as f:
        line = f.readline()
        while line:
            if line.startswith(NFS_SERVER):
                return True
            line = f.readline()

    return False

def mount_nfs():
    if is_nfs_mounted():
        log('NFS folder already mounted')
        return True

    if not os.path.exists(NFS_MOUNT):
        os.makedirs(NFS_MOUNT)

    log('Mounting NFS folder...')
    if os.system('mount -t nfs %s %s' % (NFS_SERVER, NFS_MOUNT)) != 0:
        return False

    return True

def setup_log_dir():
    #if not mount_nfs():
    #    return False

    try:
        global LOG_DIR
        LOG_DIR = os.path.join(NFS_MOUNT, LOG_DIR)
        os.makedirs(LOG_DIR)
    except FileExistsError:
        pass

    return True

def get_disk_caps(path):
    s = os.statvfs(path)
    return s.f_bsize * s.f_bavail

def add_disk_stress(disks):
    global stressors

    if len(disks) == 0:
        return True

    cmd = 'fio --time_based --runtime %d' % MAX_DUR
    job_nr = 1
    for k, d in disks.items():
        avail = get_disk_caps(d['dir'])
        # Only use part of available space
        test_cap = DISK_RATIO * avail
        if test_cap < FILE_SIZE:
            log('Available size for testing is too small (%d)' % test_cap)
            return False

        nfiles = test_cap / FILE_SIZE
        testdir = os.path.join(d['dir'], 'fio_test')
        cmd += ' --name job%d --directory %s --size %dMB --nrfiles %d' % (job_nr, testdir, test_cap / 1024 / 1024, nfiles)

        if not os.path.exists(testdir):
            os.makedirs(testdir)

        job_nr += 1

    tmp = {
        'cmd': cmd,
    }
    stressors['disk'] = tmp

    return True

def setup():
    if not setup_log_dir():
        return False

    if not mount_all(disks):
        return False

    if not add_disk_stress(disks):
        return False

    for s in setup_cmds:
        os.system(s)

    #if not os.path.exists(VIDEO_SAMPLE):
    #    log('No video sample found: %s' % VIDEO_SAMPLE)
    #    return False

    # Raise max number of open files limitation
    '''
    try:
        resource.setrlimit(resource.RLIMIT_NOFILE, (65535, 65535))
    except ValueError as e:
        log('Fail to setrlimit: %s' % str(e))
        return False
    '''

    return True

def launch_test():
    log('Test starting...')
    print(stressors)

    for k, v in stressors.items():
        cmd = v['cmd'].split(' ')
        cwd = None
        if 'cwd' in v:
            cwd = v['cwd']
        s = Runner(k, cmd, LOG_DIR, cwd=cwd)
        s.start()
        runners.append(s)

    return True

def launch_collector():
    log('Start collecting data...')
    timestamp = datetime.datetime.now().strftime('%y%m%d%H%M%S')
    cmd = ('sar -o sar_%s.data 5' % timestamp).split(' ')
    s = Runner('sar', cmd, LOG_DIR, cwd=LOG_DIR)
    s.start()
    runners.append(s)

    cmd = ('python3 read_temp.py %s/temp_%s.csv' % (LOG_DIR, timestamp)).split(' ')
    s = Runner('temp', cmd, LOG_DIR)
    s.start()
    runners.append(s)

    cmd = ['python3', 'read_oops.py', LOG_DIR]
    s = Runner('oops', cmd, LOG_DIR)
    s.start()
    runners.append(s)

def cleanup():
    for s in runners:
        s.stop()
        s.join()

    for k, v in disks.items():
        testdir = os.path.join(v['dir'], 'fio_test')
        if os.path.exists(testdir):
            shutil.rmtree(testdir, ignore_errors=True)

def read_temp():
    with open('/sys/class/thermal/thermal_zone0/temp') as f:
        s = f.read()
        s = s.rstrip('\n')
        return int(s)

    return -1


if __name__ == '__main__':

    if not setup():
        sys.exit(1)

    if not launch_test():
        sys.exit(1)

    launch_collector()

    start = time.perf_counter()
    try:
        while True:
            tmp = time.perf_counter()

            if tmp - start > MAX_DUR:
                break

            time.sleep(3)

            temp = read_temp()
            if temp < 0:
                log('Something wrong when reading temp')
                break
            if temp >= 110000:
                log('Temperature reaching %d. Force quitting...' % temp)
                break

            # We should keep memory usage within a maximum sustainable level
            # If OOM happends, then we should adjust MEM_SIZE and find out
            # a working value.
            p = subprocess.run('dmesg | grep -q -i oom', shell=True)
            if p.returncode == 0:
                dmesg_log = os.path.join(LOG_DIR, 'dmesg.log')
                log('OOM detected. Dump dmesg to %s and quit...' % dmesg_log)
                subprocess.run('dmesg > %s' % dmesg_log, shell=True)
                break
    except KeyboardInterrupt:
        log('Quit..')

    cleanup()


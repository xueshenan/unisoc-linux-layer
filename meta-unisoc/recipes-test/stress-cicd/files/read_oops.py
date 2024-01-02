#!/usr/bin/env python3

import os, sys
import subprocess
import datetime
import time
import re

kernel_errs = {}

def match_pattern(p):
    stdout = p.stdout
    line = stdout.readline()

    detected = False
    while line:
        line = line.decode('utf-8').rstrip('\r\n')
        m = re.match('\[\s*(\d+\.\d+)\] (.+)', line)
        if m:
            if 'Oops' in m.group(2) and m.group(1) not in kernel_errs:
                now = datetime.datetime.now(tz=datetime.timezone.utc)
                print('[%s] Kernel oops detected' % now.strftime('%m-%d %H:%M:%S'))
                print('  %s' % m.group(0))

                kernel_errs[m.group(1)] = m.group(2)
                detected = True

        line = stdout.readline()

    if detected:
        subprocess.run('dmesg > %s/oops_%s.dmesg.log' % (log_dir, now.strftime('%y%m%d%H%M%S')), shell=True)


if __name__ == '__main__':

    if len(sys.argv) < 2:
        print('Need log dir')
        sys.exit(1)

    log_dir = sys.argv[1]

    while True:
        p = subprocess.Popen('dmesg', shell=True, stdout=subprocess.PIPE)
        match_pattern(p)

        time.sleep(5)

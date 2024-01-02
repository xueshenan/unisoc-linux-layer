#!/usr/bin/env python3

import os, sys
import time
import datetime

def read_temp():
    with open('/sys/class/thermal/thermal_zone0/temp') as f:
        s = f.read()
        s = s.rstrip('\n')
        return float(s)

    return -1

def usage():
    print('Usage: read_temp.py <output>')

if __name__ == '__main__':

    if len(sys.argv) < 2:
        usage()
        sys.exit(1)

    try:
        with open(sys.argv[1], 'w') as f:
            count = 0
            print('"Seconds","Temp"', file=f, flush=True)
            while True:
                t = read_temp()
                if t > 0:
                    print('"%d","%.02f"' % (count, t / 1000), file=f, flush=True)

                time.sleep(5)
                count += 5
    except KeyboardInterrupt:
        print('Quit')

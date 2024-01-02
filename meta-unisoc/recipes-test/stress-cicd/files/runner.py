
import os, sys
import threading
import subprocess
import datetime
import time

class RunError(Exception):

    def __init__(self, msg):
        self.msg = msg

    def __str__(self):
        return self.msg


class Runner(threading.Thread):

    def __init__(self, name, cmd, logdir, **config):
        super().__init__()
        self.name = name
        self.cmd = cmd
        self.logdir = logdir
        self.logf = None
        self.is_stop = False
        self.cwd = None

        for k, v in config.items():
            if k == 'cwd':
                self.cwd = v

    def get_process(self):
        timestamp = datetime.datetime.now().strftime('%y%m%d%H%M%S')
        logname = '%s_%s.log' % (self.name, timestamp)
        logfile = os.path.join(self.logdir, logname)

        try:
            logf = open(logfile, 'w')
            self.logf = logf
        except IOError as e:
            raise RunError('Fail to open file: %s' % str(e))

        try:
            p = subprocess.Popen(self.cmd, stdout=logf, stderr=logf, cwd=self.cwd)
        except subprocess.SubprocessError as e:
            raise RunError('Fail to run: %s' % str(e))

        return p

    def log(self, msg):
        now = datetime.datetime.now()
        tmp = '[%s] %s' % (now.strftime('%m-%d %H:%M:%S'), msg)
        print(tmp)
        if self.logf:
            self.logf.write('%s\n' % tmp)

    def run(self):
        start = datetime.datetime.now()
        p = self.get_process()

        while not self.is_stop:
            ret = p.poll()
            if ret == None:
                time.sleep(3)
                continue
            else:
                end = datetime.datetime.now()
                diff = end - start
                self.log('[%s] self terminated (exit code %d, run time: %s). restart' % (self.name, ret, str(diff)))
                self.close_log()

                start = datetime.datetime.now()
                p = self.get_process()

        while p.poll() == None:
            p.kill()
            p.wait()

        end = datetime.datetime.now()
        diff = end - start
        self.log('[%s] Terminated by runner (run time: %s)' % (self.name, str(diff)))
        self.close_log()

        
    def close_log(self):
        if self.logf:
            self.logf.close()
            self.logf = None 

    def stop(self):
        self.is_stop = True

if __name__ == '__main__':

    try:
        os.mkdir('data')
    except FileExistsError:
        pass

    cmd = 'dd if=/dev/zero of=/dev/null bs=1 status=progress'.split(' ')
    s = Runner('dd', cmd, 'data')
    print('start')
    s.start()
    time.sleep(10)

    print('stop')
    s.stop()
    print('join')
    s.join()

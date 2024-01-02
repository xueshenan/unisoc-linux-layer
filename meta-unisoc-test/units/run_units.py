#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import sys
import os
import re
import time, datetime
import serial
import threading
import subprocess
import requests
from xml.etree import ElementTree as ET

'''
check python version 
'''

pyVersion = sys.version
if re.match('^3', pyVersion):
	pyVersion = 3
	print("Python version: " + str(pyVersion))
	import configparser as ConfigParse
	import queue as Queue
else:
	pyVersion = 2
	import ConfigParser as ConfigParser
	import Queue as Queue

'''
get arguments for CTS
'''

if len(sys.argv) >= 5:
	args = sys.argv
	caseName = sys.argv[0].split(".")[0]
	atPort = sys.argv[1]
	atRate = sys.argv[2]
	ctsVersion = sys.argv[3]
	deviceId = sys.argv[4]
	nodeUrl = sys.argv[5]
	scriptPath = os.path.abspath(__file__)
	print("atPort:" + atPort + " atRate:" + atRate + " ctsVersion:" + ctsVersion
			+ " deviceId:" + deviceId + " nodeUrl: " + nodeUrl + "\n")
else:
	print("INFO - lost arguments")
	print("INFO - Total test is fail")
	sys.exit()
	
rootDir = os.getcwd()

'''
if last result file exist,remove it
'''

def cleanRetXml(filePath):
	if os.path.exists(filePath):
		print("old itest.xml exists, remove it!")
		os.remove(filePath)
	else:
		pass
	
	return

'''
check adb is working
'''
def checkADB():
	cont = 1
	checkCmd = "adb devices"
	while cont <= 5:
		mPipe = subprocess.Popen(checkCmd, stdout=subprocess.PIPE, shell=True)
		out, err  = mPipe.communicate()
		status = mPipe.wait()
		devstatus = deviceId
		if devstatus in out.decode():
			return True
		else:
			time.sleep(5)
		cont += 1
	
	print("check ADB timeout!!")
	return False

'''
yocto it ofono test
'''

def ofonoItest():
	failRet = 0
	testRet = 5
	disableRet = 0
	passRet = 0
	
	exItestCmd = "adb -s " + deviceId + " shell app_demo test_ofono_init"
	mPipe = subprocess.Popen(exItestCmd, stdout=subprocess.PIPE, shell=True)
	out, err  = mPipe.communicate()
	status = mPipe.wait()
	print(out.decode())
	if "Get dbus connection success" in out.decode():
		pass
	else:
		failRet += 1
	
	exItestCmd = "adb -s " + deviceId + " shell app_demo test_get_modems"
	mPipe = subprocess.Popen(exItestCmd, stdout=subprocess.PIPE, shell=True)
	out, err  = mPipe.communicate()
	status = mPipe.wait()
	print(out.decode())
	if "ril_0" in out.decode():
		pass
	else:
		failRet += 1
		
	exItestCmd = "adb -s " + deviceId + " shell app_demo /ril_0 test_modem_get_powered"
	mPipe = subprocess.Popen(exItestCmd, stdout=subprocess.PIPE, shell=True)
	out, err  = mPipe.communicate()
	status = mPipe.wait()
	print(out.decode())
	if "Modem is powered on" in out.decode():
		pass
	else:
		failRet += 1
	
	exItestCmd = "adb -s " + deviceId + " shell app_demo /ril_0 test_modem_get_online"
	mPipe = subprocess.Popen(exItestCmd, stdout=subprocess.PIPE, shell=True)
	out, err  = mPipe.communicate()
	status = mPipe.wait()
	print(out.decode())
	if "Modem is online" in out.decode():
		pass
	else:
		failRet += 1
	
	exItestCmd = "adb -s " + deviceId + " shell app_demo /ril_0 test_modem_get_info"
	mPipe = subprocess.Popen(exItestCmd, stdout=subprocess.PIPE, shell=True)
	out, err  = mPipe.communicate()
	status = mPipe.wait()
	print(out.decode())
	if "Revision: FM_BASE_" in out.decode():
		pass
	else:
		failRet += 1
		
	passRet = testRet - failRet
	
	if 0 == failRet:
		print("INFO - Total test is pass")
		print("Results summary for test-tag 'OfonoItest': " + str(testRet)
			+ " Tests [" + str(passRet) + " Passed " + str(failRet)
			+ " Failed " + str(disableRet) + " Ignored]")
	else:
		print("INFO - Total test is fail")
		print("Results summary for test-tag 'OfonoItest': " + str(testRet)
			+ " Tests [" + str(passRet) + " Passed " + str(failRet)
			+ " Failed " + str(disableRet) + " Ignored]")
		
	return
	
'''
yocto it connman test
'''

def connmanItest():
	holdingTime = 5

	exItestCmd = "adb -s " + deviceId + " shell /usr/bin/utit/connman_itest \
			--gtest_output=xml:/var/log/connman_itest.xml"
	exGetXmlCmd = "adb -s " + deviceId + " pull /var/log/connman_itest.xml"
	xmlDir = os.path.join(rootDir, "connman_itest.xml")
	
	cleanRetXml(xmlDir)

	mPipe = subprocess.Popen(exItestCmd, shell=True)
	mPipe.wait()

	mPipe = subprocess.Popen(exGetXmlCmd, shell=True)
	mPipe.wait()

	time.sleep(holdingTime)
	
	if os.path.exists(xmlDir):
		pass
	else:
		print("result xml not exists!")
		print("Results summary for test-tag \
				'ConnmanItest': 1 Tests [0 Passed 1 Failed 0 Ignored]")
		return

	mTree = ET.parse(xmlDir)
	mRoot = mTree.getroot()

	failRet = mRoot.get("failures")
	testRet = mRoot.get("tests")
	disableRet = mRoot.get("disabled")
	errRet = mRoot.get("errors")
	passRet = int(testRet) - int(failRet)

	if "0" == failRet:
		print("INFO - Total test is pass")
		print("Results summary for test-tag 'ConnmanItest': " + testRet
			+ " Tests [" + str(passRet) + " Passed " + failRet
			+ " Failed " + disableRet + " Ignored]")
	else:
		print("INFO - Total test is fail")
		print("Results summary for test-tag 'ConnmanItest': " + testRet
			+ " Tests [" + str(passRet) + " Passed " + failRet
			+ " Failed " + disableRet + " Ignored]")
	
	return

'''
yocto it bluez testRet
'''

def bluezItest():
	holdingTime = 5

	exItestCmd = "adb -s " + deviceId + " shell /usr/bin/utit/bluez_itest \
		--gtest_output=xml:/var/log/bluez_itest.xml"
	exGetXmlCmd = "adb -s " + deviceId + " pull /var/log/bluez_itest.xml"
	xmlDir = os.path.join(rootDir, "bluez_itest.xml")
	
	cleanRetXml(xmlDir)

	mPipe = subprocess.Popen(exItestCmd, shell=True)
	mPipe.wait()

	mPipe = subprocess.Popen(exGetXmlCmd, shell=True)
	mPipe.wait()

	time.sleep(holdingTime)
	
	if os.path.exists(xmlDir):
		pass
	else:
		print("result xml not exists!")
		print("Results summary for test-tag 'BluezItest': 1 Tests [0 Passed 1 Failed 0 Ignored]")
		return

	mTree = ET.parse(xmlDir)
	mRoot = mTree.getroot()

	failRet = mRoot.get("failures")
	testRet = mRoot.get("tests")
	disableRet = mRoot.get("disabled")
	errRet = mRoot.get("errors")
	passRet = int(testRet) - int(failRet)

	if "0" == failRet:
		print("INFO - Total test is pass")
		print("Results summary for test-tag 'BluezItest': " + testRet
			+ " Tests [" + str(passRet) + " Passed " + failRet
			+ " Failed " + disableRet + " Ignored]")
	else:
		print("INFO - Total test is fail")
		print("Results summary for test-tag 'BluezItest': " + testRet
				+ " Tests [" + str(passRet) + " Passed " + failRet
				+ " Failed " + disableRet + " Ignored]")
		
	return

'''
yocto it atrouter test
'''

def atrouterItest():
	holdingTime = 5

	exItestCmd = "adb -s " + deviceId + " shell /usr/bin/utit/atroutergtest \
	--gtest_filter=MyATSession.DefaultConstructor --gtest_output=xml:/var/log/atrouter_itest.xml"
	exGetXmlCmd = "adb -s " + deviceId + " pull /var/log/atrouter_itest.xml"
	xmlDir = os.path.join(rootDir, "atrouter_itest.xml")
	
	cleanRetXml(xmlDir)

	mPipe = subprocess.Popen(exItestCmd, shell=True)
	mPipe.wait()

	mPipe = subprocess.Popen(exGetXmlCmd, shell=True)
	mPipe.wait()

	time.sleep(holdingTime)
	
	if os.path.exists(xmlDir):
		pass
	else:
		print("result xml not exists!")
		print("Results summary for test-tag 'AtrouterItest': 1 Tests [0 Passed 1 Failed 0 Ignored]")
		return

	mTree = ET.parse(xmlDir)
	mRoot = mTree.getroot()

	failRet = mRoot.get("failures")
	testRet = mRoot.get("tests")
	disableRet = mRoot.get("disabled")
	errRet = mRoot.get("errors")
	passRet = int(testRet) - int(failRet)

	if "0" == failRet:
		print("INFO - Total test is pass")
		print("Results summary for test-tag 'AtrouterItest': " + testRet
			+ " Tests [" + str(passRet) + " Passed " + failRet
			+ " Failed " + disableRet + " Ignored]")
	else:
		print("INFO - Total test is fail")
		print("Results summary for test-tag 'AtrouterItest': " + testRet
			+ " Tests [" + str(passRet) + " Passed " + failRet
			+ " Failed " + disableRet + " Ignored]")
		
	return

'''
yocto it camera test
'''
def cameraItest():
	failRet = 0
	testRet = 1
	disableRet = 0
	passRet = 0
	exItestCmd = "adb -s " + deviceId + " shell /usr/bin/V4l2UnitTest --set-width 480"

	mPipe = subprocess.Popen(exItestCmd, stdout=subprocess.PIPE, shell=True)
	out, err  = mPipe.communicate()
	status = mPipe.wait()
	print(out.decode())
	if "vtest test finish!" in out.decode():
		pass
	else:
		failRet += 1

	passRet = testRet - failRet

	if 0 == failRet:
		print("INFO - Total test is pass")
		print("Results summary for test-tag 'CameraItest': " + str(testRet)
			+ " Tests [" + str(passRet) + " Passed " + str(failRet)
			+ " Failed " + str(disableRet) + " Ignored]")
	else:
		print("INFO - Total test is fail")
		print("Results summary for test-tag 'CameraItest': " + str(testRet)
			+ " Tests [" + str(passRet) + " Passed " + str(failRet)
			+ " Failed " + str(disableRet) + " Ignored]")
		
	return

'''
start all test in main
'''

it_mapping = {
	'at_router':atrouterItest,
	'connman':connmanItest,
	'libbt':bluezItest,
	'libcamera':cameraItest,
	'radio':ofonoItest
}

caseList = []

buildInfoUrl = nodeUrl + "/buildinfo.log"
xReq = requests.get(buildInfoUrl)
urlContents = xReq.content.decode('utf-8')
for line in urlContents.split('\n'):
	if "CHANGES:" in line:
		print(line)
		for key in it_mapping:
			if key in line:
				caseList.append(it_mapping[key])

def runTest(case_list):
	if not checkADB():
		sys.exit()
	if case_list:
		for case in case_list:
			case()
	else:
		print("case list is empty!")
		connmanItest()
		bluezItest()
		atrouterItest()
		cameraItest()
		ofonoItest()
	return

if __name__ == "__main__":
	runTest(caseList)

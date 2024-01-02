#include "iniFileReaderMock.h"

std::string sFilePath = "/mnt/data/config.ini"; 

MOCKifstream* mIfstreamObj = new MOCKifstream(sFilePath); 
MockCommFunction* mCommFuncObj = new MockCommFunction();

extern "C" istream& __wrap_getline (istream& is, string& str)
{
	return mCommFuncObj->getline(is, str);
}

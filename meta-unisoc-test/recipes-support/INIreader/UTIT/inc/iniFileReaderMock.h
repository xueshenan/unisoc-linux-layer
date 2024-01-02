#include <gmock/gmock.h>
#include <iostream>
#include <fstream>

using namespace std;

class MOCKifstream : public ifstream {
public:
	MOCKifstream (string& filePath) 
	{}
	virtual ~MOCKifstream ()
	{}

	MOCK_METHOD0(is_open, bool());
	MOCK_METHOD0(close, void());
};

class CommFunction {
public:
	virtual ~CommFunction() 
	{}

	virtual istream& getline (istream& is, string& str) = 0;
};

class MockCommFunction : public CommFunction {
public:
	virtual ~MockCommFunction() 
	{}

	MOCK_METHOD2(getline, istream&(istream&, string&));
};

extern "C" istream& __wrap_getline (istream& is, string& str);

extern MOCKifstream* mIfstreamObj;
extern MockCommFunction* mCommFuncObj;

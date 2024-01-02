#include <gtest/gtest.h>
#include <iostream>
#include <tuple>

#include "iniFileReaderMock.h"
#include "IniInterface.h"
#include "IniDataHandler.h"
#include "IniFileHandler.h"
#include "Common.h"
#include "Error.h"
#include "Log.h"

const char* pIniFilePath = "/mnt/data/config.ini";
const char* pDumpFilePath = "/mnt/data/dumpp.ini";
const char* pTestData = "[sec1]\n\na=1\n\nb=test\n\n";

class InitIniReaderTest : public ::testing::Environment {
public:
	virtual void SetUp()
	{
		FILE* fp = fopen(pIniFilePath, "w");
		fputs(pTestData ,fp);
		fclose(fp);
	}
	virtual void TearDown()
	{
		remove(pIniFilePath);
		remove(pDumpFilePath);
		delete mIfstreamObj;
		delete mCommFuncObj;
	}
};

class Test_for_inifile_load : public ::testing::TestWithParam<
							  std::tuple<const char*, INIREADER, int>> {
protected:
	int iExpectedResult;
	const char* inifilepath;
	INIREADER inireader;

	virtual void SetUp()
	{
		inifilepath = std::get<0>(GetParam());
		inireader = std::get<1>(GetParam());
		iExpectedResult = std::get<2>(GetParam());
	}
	virtual void TearDown()
	{
		delete [] inireader;
	}
};

class Test_for_inifile_dump : public ::testing::TestWithParam<
							  std::tuple<INIREADER, FILE*, int>> {
protected:
	int iExpectedResult;
	FILE* dumpfile;
	INIREADER inireader;

	virtual void SetUp()
	{
		inireader = std::get<0>(GetParam());
		dumpfile = std::get<1>(GetParam());
		iExpectedResult = std::get<2>(GetParam());
		switch (iExpectedResult) {
			case RET_OK: {
				inifile_load(pIniFilePath, &inireader);	
				dumpfile = fopen(pDumpFilePath, "a+");
				break;	
			}
			case RET_ERROR: {
				inireader = NULL;
				dumpfile = fopen(pDumpFilePath, "a+");
				break;
			}
			default:
				break;
		}
	}
	virtual void TearDown()
	{
		delete [] inireader;
		fclose(dumpfile);
	}
};

class Test_for_inifile_getstring : public ::testing::TestWithParam<
								   std::tuple<INIREADER, const char*, 
								   const char*, char*, const char*, int>> {
protected:
	int iExpectedResult;
	INIREADER inireader;
	const char* section;
	const char* key;
	const char* def;
	char* value;

	virtual void SetUp()
	{
		inireader = std::get<0>(GetParam());
		section = std::get<1>(GetParam());
		key = std::get<2>(GetParam());
		value = std::get<3>(GetParam());
		def = std::get<4>(GetParam());
		iExpectedResult = std::get<5>(GetParam());
		switch (iExpectedResult) {
			case RET_ERROR: {
				inireader = NULL;
				break;
			}
			default: {
				inifile_load(pIniFilePath, &inireader);
				break;
			}
		}
	}
	virtual void TearDown()
	{
		delete [] inireader;
	}
};

class Test_for_inifile_getint : public ::testing::TestWithParam<
								std::tuple<INIREADER, const char*, 
								const char*, int*, int, int>> {
protected:
	int iExpectedResult;
	INIREADER inireader;
	const char* section;
	const char* key;
	int* value;
	int def;

	virtual void SetUp()
	{
		inireader = std::get<0>(GetParam());
		section = std::get<1>(GetParam());
		key = std::get<2>(GetParam());
		value = std::get<3>(GetParam());
		def = std::get<4>(GetParam());
		iExpectedResult = std::get<5>(GetParam());
		switch (iExpectedResult) {
			case RET_ERROR: {
				inireader = NULL;
				break;
			}
			default: {
				inifile_load(pIniFilePath, &inireader);
				break;
			}
		}
	}
	virtual void TearDown()
	{
		delete [] inireader;
	}
};

class Test_for_inifile_setstring : public ::testing::TestWithParam<
								   std::tuple<INIREADER, const char*, 
								   const char*, const char*, int>> {
protected:
	int iExpectedResult;
	INIREADER inireader;
	const char* section;
	const char* key;
	const char* value;

	virtual void SetUp()
	{
		inireader = std::get<0>(GetParam());
		section = std::get<1>(GetParam());
		key = std::get<2>(GetParam());
		value = std::get<3>(GetParam());
		iExpectedResult = std::get<4>(GetParam());
		switch (iExpectedResult) {
			case RET_ERROR: {
				inireader = NULL;
				break;
			}
			default: {
				inifile_load(pIniFilePath, &inireader);
				break;
			}
		}
	}
	virtual void TearDown()
	{
		delete [] inireader;
	}
};

class Test_for_inifile_setint : public ::testing::TestWithParam<
								std::tuple<INIREADER, const char*, 
								const char*, int, int>> {
protected:
	int iExpectedResult;
	INIREADER inireader;
	const char* section;
	const char* key;
	int value;

	virtual void SetUp()
	{
		inireader = std::get<0>(GetParam());
		section = std::get<1>(GetParam());
		key = std::get<2>(GetParam());
		value = std::get<3>(GetParam());
		iExpectedResult = std::get<4>(GetParam());
		switch (iExpectedResult) {
			case RET_ERROR: {
				inireader = NULL;
				break;
			}
			default: {
				inifile_load(pIniFilePath, &inireader);
				break;
			}
		}
	}
	virtual void TearDown()
	{
		delete [] inireader;
	}
};

class Test_for_inifile_release : public ::testing::TestWithParam<
								 std::tuple<INIREADER, int>> {
protected:
	int iExpectedResult;
	INIREADER inireader;

	virtual void SetUp()
	{
		inireader = std::get<0>(GetParam());
		iExpectedResult = std::get<1>(GetParam());
		switch (iExpectedResult) {
			case RET_OK: {
				inifile_load(pIniFilePath, &inireader);	
				break;	
			}
			case RET_ERROR: {
				inireader = NULL;
				break;
			}
			default:
				break;
		}
	}
	virtual void TearDown()
	{
	//	delete [] inireader;
	}
};

TEST_P(Test_for_inifile_load, Test_inifile_load) {
	EXPECT_EQ (iExpectedResult, inifile_load(inifilepath, &inireader));
}

TEST_P(Test_for_inifile_dump, Test_inifile_dump) {
	EXPECT_EQ (iExpectedResult, inifile_dump(inireader, dumpfile));
}

TEST_P(Test_for_inifile_getstring, Test_inifile_getstring) {
	EXPECT_EQ (iExpectedResult, inifile_getstring(inireader, 
				section, key, value, def));
}

TEST_P(Test_for_inifile_getint, Test_inifile_getint) {
	EXPECT_EQ (iExpectedResult, inifile_getint(inireader, 
				section, key, value, def));
}

TEST_P(Test_for_inifile_setstring, Test_inifile_setstring) {
	EXPECT_EQ (iExpectedResult, inifile_setstring(inireader, 
				section, key, value));
}

TEST_P(Test_for_inifile_setint, Test_inifile_setint) {
	EXPECT_EQ (iExpectedResult, inifile_setint(inireader, 
				section, key, value));
}

TEST_P(Test_for_inifile_release, Test_inifile_release) {
	EXPECT_EQ (iExpectedResult, inifile_release(inireader));
}

INIREADER inireader;
const char* pLongStr = "uEAHZFahkBPnQ99pYlGa9oNc329rliDAafihttE7Rc7uEqSTdT\
						OVogibItvWsxORvRFsN2aP1lgnQPypxzYgXw9DUxU7kwn6l0fd\
						rfRNCPN5ff6XC5sI4iGlZC9dijFq";

INSTANTIATE_TEST_CASE_P(Init_Param, Test_for_inifile_load, 
		testing::Values(make_tuple("test.ini", inireader, ERR_OPEN_FILE_FAILED),
						make_tuple(pIniFilePath, inireader, RET_OK)));

FILE* fp;
INSTANTIATE_TEST_CASE_P(Init_Param, Test_for_inifile_dump, 
		testing::Values(make_tuple(inireader, fp, RET_OK),
						make_tuple(inireader, fp, RET_ERROR)));

char mStr [128];
char* pChar = mStr;
INSTANTIATE_TEST_CASE_P(Init_Param, Test_for_inifile_getstring, 
		testing::Values(make_tuple(inireader, "sec1", "b", pChar, "abc", RET_OK),
						make_tuple(inireader, "sec1", "b", pChar, "abc", RET_ERROR),
						make_tuple(inireader, pLongStr, "b", pChar, "abc", ERR_SECTION_TOOLONG),
						make_tuple(inireader, "sec1", pLongStr, pChar, "abc", ERR_KEY_TOOLONG)));

int value = 5;
int* pInt = &value;
INSTANTIATE_TEST_CASE_P(Init_Param, Test_for_inifile_getint, 
		testing::Values(make_tuple(inireader, "sec1", "a", pInt, 5, RET_OK),
						make_tuple(inireader, "sec1", "a", pInt, 5, RET_ERROR),
						make_tuple(inireader, pLongStr, "a", pInt, 5, ERR_SECTION_TOOLONG),
						make_tuple(inireader, "sec1", pLongStr, pInt, 5, ERR_KEY_TOOLONG)));

INSTANTIATE_TEST_CASE_P(Init_Param, Test_for_inifile_setstring, 
		testing::Values(make_tuple(inireader, "sec2", "b", "cast", RET_OK),
						make_tuple(inireader, "sec2", "b", "cast", RET_ERROR),
						make_tuple(inireader, pLongStr, "b", "cast", ERR_SECTION_TOOLONG),
						make_tuple(inireader, "sec2", pLongStr, "cast", ERR_KEY_TOOLONG)));

INSTANTIATE_TEST_CASE_P(Init_Param, Test_for_inifile_setint, 
		testing::Values(make_tuple(inireader, "sec2", "a", 2, RET_OK),
						make_tuple(inireader, "sec2", "a", 2, RET_ERROR),
						make_tuple(inireader, pLongStr, "a", 2, ERR_SECTION_TOOLONG),
						make_tuple(inireader, "sec2", pLongStr, 2, ERR_KEY_TOOLONG)));

INSTANTIATE_TEST_CASE_P(Init_Param, Test_for_inifile_release, 
		testing::Values(make_tuple(inireader, RET_OK),
						make_tuple(inireader, RET_ERROR)));

int main(int argc, char* argv[])
{
	::testing::AddGlobalTestEnvironment(new InitIniReaderTest);
	::testing::InitGoogleMock(&argc, argv);
	return RUN_ALL_TESTS();
}

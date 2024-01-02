
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string>
#include <sstream>
#include <algorithm>
#include <cstdlib>
#include <gtest/gtest.h>

#define private public
#define protected public

#include "RouterConfig.h"
#include "MiniAPService.h"
#include "ATRequest.h"
#include "RouterManager.h"
#include "ATMain.h"
#include "ATRouter.h"
#include "RouterManager.h"
#include "ATIndicationRouter.h"
#include "SessionQueue.h"
#include "ATRLog.h"
#include <ctype.h>
#include "common.h"
#include "ATSession.h"
#include "ATParser.h"

using std::string;

ROUTER_INFO_T TestInfo;

class MyATSession: public testing::Test {
protected:
    virtual void SetUp()
    {
    }
    virtual void TearDown()
    {
    }
};
string ap_message = "AT+SERDS=5\r\n";
string cp_message = "AT\r\n";
//string data_message = "AT^NDISDUN=1,1\r\n";
string mux_message = "AT+CMUX=1\r\n";
string error_message = "PLEASE SWITCH ON THE UE\r\n";

TEST(MyATSession, DefaultConstructor) {
    ATMain * m_atmain = new ATMain();
    RouterManager * m_routermanager = new RouterManager(m_atmain);
    ATRouter * m_atrouter = new ATRouter(&TestInfo, m_routermanager);
    ATSession * m_atsession_ = new ATSession(m_atrouter);

    EXPECT_EQ(0, m_atsession_->GetPduMode());
}

TEST(MyATSession, GetATRequestTarget) {
    ATMain * m_atmain = new ATMain();
    RouterManager * m_routermanager = new RouterManager(m_atmain);
    ATRouter * m_atrouter = new ATRouter(&TestInfo, m_routermanager);
    ATSession * m_atsession_ = new ATSession(m_atrouter);
    ATRequest* m_request=new ATRequest();
    m_atmain->CreateModules();
    EXPECT_NE(CMD_TO_MINIAP,m_atsession_->GetATRequestTarget(m_request, ap_message));
//    EXPECT_NE(CMD_TO_DATA,m_atsession_->GetATRequestTarget(m_request, data_message));
    EXPECT_NE(CMD_TO_CMUX,m_atsession_->GetATRequestTarget(m_request, mux_message));
    EXPECT_NE(CMD_TO_CP,m_atsession_->GetATRequestTarget(m_request, mux_message));
   //EXPECT_NE(CMD_TO_ERROR,m_atsession_->GetATRequestTarget(m_request, error_message));
}

int main(int argc, char *argv[])
{
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}

#include <gtest/gtest.h>
#include <stdlib.h>
#include <string>
#include <iostream>
#include <cstdlib>
#include <cstring>
#include <algorithm>
using namespace std;

class qtdemoTest:public testing::Test {
    protected:
      virtual void SetUp() {
      }
      virtual void TearDown() {
      }
    };

const int N=300;
//calculator-test
TEST_F(qtdemoTest, calculator_test_cat) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("cat /mnt/data/yocto.log | grep 'Calculator'","r"))!=NULL)
    {
      ret = true;
      cout << "calculator-log1"<<endl;
    }
    else {
      ret = false;
      cout << "calculator-log2"<<endl ;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line),fp)!=NULL){
      cout << "calculator-log3" <<endl ;
      cout <<line <<endl;
    }
    pclose(fp);
    }

TEST_F(qtdemoTest, calculator_test_ps) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp=popen("ps -A | grep calculator | grep wayland","r"))!=NULL)
    {
        ret = true;
        cout << "calculator-ps1" <<endl;
    }
    else{
        ret = false;
        cout << "calculator-ps2" <<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "calculator-ps3" <<endl;
        cout <<line << endl;
    }
    pclose(fp);
}

//i18n-test
TEST_F(qtdemoTest, i18n_test_cat) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("cat /mnt/data/yocto.log | grep 'I18N'","r"))!=NULL)
    {
        ret = true;
        cout << "i18n-log1"<<endl;
    }
    else{
        ret = false;
        cout << "i18n-log2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "i18n-log3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}

TEST_F(qtdemoTest, i18n_test_grep) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("ps -A | grep i18n |grep wayland","r"))!=NULL)
    {
        ret =true;
        cout <<"i18n-ps1"<<endl;
    }
    else{
        ret = false;
        cout <<"i18n-ps2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "i18n-ps3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}

//svggenerator-test
TEST_F(qtdemoTest, svggenerator_test_cat) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("cat /mnt/data/yocto.log | grep 'SVG Generator'","r"))!=NULL)
    {
        ret = true;
        cout << "svggenerator-log1"<<endl;
    }
    else{
        ret = false;
        cout << "svggenerator-log2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "svggenerator-log3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}

TEST_F(qtdemoTest, svggenerator_test_grep) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("ps -A | grep svggenerator |grep wayland","r"))!=NULL)
    {
        ret =true;
        cout <<"svggenerator-ps1"<<endl;
    }
    else{
        ret = false;
        cout <<"svggenerator-ps2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "svggenerator-ps3"<<endl;
        cout << line<<endl;
    }
    pclose(fp);
}

//videowidget-test
TEST_F(qtdemoTest, videowidget_test_cat) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("cat /mnt/data/yocto.log | grep 'QtMultimedia'","r"))!=NULL)
    {
        ret = true;
        cout << "videowidget-log1"<<endl;
    }
    else{
        ret = false;
        cout << "videowidget-log2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "videowidget-log3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}

TEST_F(qtdemoTest, videowidget_test_grep) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("ps -A | grep videowidget |grep wayland","r"))!=NULL)
    {
        ret =true;
        cout <<"videowidget-ps1"<<endl;
    }
    else{
        ret = false;
        cout <<"videowidget-ps2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "videowidget-ps3"<<endl;
        cout << line<<endl;
    }
    pclose(fp);
}

//analogclosk-test
TEST_F(qtdemoTest, analogclosk_test_cat) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("cat /mnt/data/yocto.log | grep 'Analog Clock'","r"))!=NULL)
    {
        ret = true;
        cout << "analogclosk-log1"<<endl;
    }
    else{
        ret = false;
        cout << "analogclosk-log2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "analogclosk-log3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}
TEST_F(qtdemoTest, analogclosk_test_grep) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("ps -A | grep analogclosk |grep wayland","r"))!=NULL)
    {
        ret =true;
        cout <<"analogclosk-ps1"<<endl;
    }
    else{
        ret = false;
        cout <<"analogclosk-ps2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "analogclosk-ps3"<<endl;
        cout << line<<endl;
    }
    pclose(fp);
}

//chart-test
TEST_F(qtdemoTest, chart_test_cat) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("cat /mnt/data/yocto.log | grep 'Chart'","r"))!=NULL)
    {
        ret = true;
        cout << "chart-log1"<<endl;
    }
    else{
        ret = false;
        cout << "chart-log2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "chart-log3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}
TEST_F(qtdemoTest, chart_test_grep) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("ps -A | grep chart |grep wayland","r"))!=NULL)
    {
        ret =true;
        cout <<"chart-ps1"<<endl;
    }
    else{
        ret = false;
        cout <<"chart-ps2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "chart-ps3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}

//simple-cpp-test
TEST_F(qtdemoTest, simple_test_cat) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("cat /mnt/data/yocto.log | grep 'Qt3DCore'","r"))!=NULL)
    {
        ret = true;
        cout << "simple-log1"<<endl;
    }
    else{
        ret = false;
        cout << "simple-log2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "simple-log3"<<endl;
        cout <<line<<endl;
    }
    pclose(fp);
}

TEST_F(qtdemoTest, simple_test_grep) {
    char line[N];
    FILE *fp = NULL;
    bool ret;
    if((fp = popen("ps -A | grep simple |grep wayland","r"))!=NULL)
    {
        ret =true;
        cout <<"simple-ps1"<<endl;
    }
    else{
        ret = false;
        cout <<"simple-ps2"<<endl;
    }
    ASSERT_EQ(ret, true);
    while (fgets(line, sizeof(line)-1,fp)!=NULL){
        cout << "simple-ps3"<<endl;
        cout << line<<endl;
    }
    pclose(fp);
}

int main(int argc, char *argv[])
{
    ::testing::InitGoogleTest(&argc, argv);
    int ret = RUN_ALL_TESTS();
    return ret;
}
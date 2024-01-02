#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>

#include <gtest/gtest.h>
#include <gmock/gmock.h>
#include <glib.h>
#include <gio/gio.h>
#include <gio/gnetworking.h>

#include "bluez_gdbus.h"

using ::testing::_;
using ::testing::AtLeast;
using ::testing::Return;

class BluezEnvironment : public testing::Environment
{
	public:
		virtual void SetUp(){
			system("if $(ps -ef |grep -v \"grep\"|grep -q \"hciattach_sprd\");then echo;else hciattach_sprd -n /dev/ttyBT0 sprd & sleep 5; fi");
			system("rfkill unblock all");
			system("hciconfig hci0 down");
			system("hciconfig hci0 up");
		}
		virtual void TearDown(){
			system("ps -ef |grep ttyBT0 |awk '{print $2}'|xargs kill");
		}
};

class Bluez_AdapterTest:public testing::Test {
	protected:
		virtual void SetUp(){

		}
		virtual void TearDown(){

		}
};

extern GDBusConnection *con;

extern GVariant *result;
extern GList *devices;
extern GMainLoop *scan_loop;
extern GMainLoop *main_loop;
extern GDBusConnection *conn;
extern gboolean loop_exit;


TEST_F(Bluez_AdapterTest, bluez_test_StartDiscovery){

	int ret = 0;
	con = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	if(con == NULL) {
		g_print("Not able to get connection to system bus\n");
		ret = 1;
	}

	ret = bluez_adapter_call_method_sync("StartDiscovery");
	if(ret) {
		g_print("Not able to scan for new devices\n");
	}
	EXPECT_EQ(ret, 0);

	//g_object_unref(con);

}

TEST_F(Bluez_AdapterTest, bluez_test_StopDiscovery){

	int ret = 0;

	con = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	if(con == NULL) {
		g_print("Not able to get connection to system bus\n");
		ret = 1;
	}

	ret = bluez_adapter_call_method_sync("StartDiscovery");

	scan_loop = g_main_loop_new (NULL, FALSE);
	g_timeout_add_seconds(2, on_scan_timeout, NULL);
	g_main_loop_run(scan_loop);
	g_main_loop_unref(scan_loop);

	ret = enable_device_discovery(con, FALSE);
	//ret = bluez_adapter_call_method_sync("StopDiscovery");
	if(ret) {
		g_print("Not able to stop scanning\n");
	}
	EXPECT_EQ(ret, 0);

	//g_object_unref(con);

}

TEST_F(Bluez_AdapterTest, bluez_test_SetDiscoveryFilter){

	int ret = 0;

	con = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	if(con == NULL) {
		g_print("Not able to get connection to system bus\n");
		ret = 1;
	}

	ret = bluez_set_discovery_filter("auto", "100", "");
	if(ret) {
		g_print("Not able to set discovery filter\n");
	}
	EXPECT_EQ(ret, 0);

	//g_object_unref(con);

}


 TEST_F(Bluez_AdapterTest, bluez_test_GetDiscoveryFilters){
	int ret = 0;

	con = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	if(con == NULL) {
		g_print("Not able to get connection to system bus\n");
		ret = 1;
	}

	ret = bluez_set_discovery_filter("auto", "100", "");
	if(ret) {
		g_print("Not able to set discovery filter\n");
	}

	ret = bluez_adapter_call_method("GetDiscoveryFilters",
		NULL,
		bluez_get_discovery_filter_cb);
	
	if(ret) {
		g_print("Not able to get discovery filter\n");
		ret = 1;
	}

	EXPECT_EQ(ret, 0);
	g_object_unref(con);
 }

TEST_F(Bluez_AdapterTest, bluez_test_SetDiscoverble){

	int ret = 0;

	con = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	if(con == NULL) {
		g_print("Not able to get connection to system bus\n");
		ret = 1;
	}

	ret = bluez_adapter_set_property("Discoverable", g_variant_new("b", TRUE));
	if(ret) {
		g_print("Not able to set discoverble\n");
	}
	EXPECT_EQ(ret, 0);

	//g_object_unref(con);

}

TEST_F(Bluez_AdapterTest, bluez_test_SetAdapterPowerOff){
	int ret = 0;

	con = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	if(con == NULL) {
		g_print("Not able to get connection to system bus\n");
		ret = 1;
	}
	ret = bluez_adapter_set_property("Powered", g_variant_new("b", FALSE));
	if(ret) {
		g_print("Not able to disable the adapter\n");
	}
	EXPECT_EQ(ret, 0);

	//g_object_unref(con);
}

TEST_F(Bluez_AdapterTest, bluez_test_SetAdapterPowerOn){
	int ret = 0;

	con = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	if(con == NULL) {
		g_print("Not able to get connection to system bus\n");
		ret = 1;
	}
	ret = bluez_adapter_set_property("Powered", g_variant_new("b", TRUE));
	if(ret) {
		g_print("Not able to enable the adapter\n");
	}
	EXPECT_EQ(ret, 0);

	g_object_unref(con);
}

TEST_F(Bluez_AdapterTest, bluez_test_PairConnectDisconnect){

	int ret = 0;

	conn = g_bus_get_sync(G_BUS_TYPE_SYSTEM, NULL, NULL);
	// g_print(
    // "Starting discovery service for %d seconds...\n", SCAN_TIMEOUT_SECONDS);
	enable_device_discovery(conn, TRUE);
	// if(enable_device_discovery(conn, TRUE)<0){
	// 	g_print("failed\n");
	// } else g_print("ok\n");

	scan_loop = g_main_loop_new (NULL, FALSE);
	g_timeout_add_seconds(SCAN_TIMEOUT_SECONDS, on_scan_timeout, NULL);
	g_main_loop_run(scan_loop);
	g_main_loop_unref(scan_loop);
	//g_print("Terminating discovery service...\n");

	enable_device_discovery(conn, FALSE);
	// if (enable_device_discovery(conn, FALSE) < 0)
	// {
	// 	g_print("failed\n");
	// } else g_print("ok\n");

	gchar *device = select_device();

	//g_print("Connecting to %s\n", device);
	connect_device(conn, device, TRUE);
	EXPECT_EQ(ret, 0);

	//signal(SIGINT, on_sigint_received);
	//main_loop = g_main_loop_new (NULL, FALSE);
	//g_idle_add(on_loop_idle, NULL);
	//g_main_loop_run(main_loop);
	//g_main_loop_unref(main_loop);

	//g_print("\nDisconnecting from %s\n", device);

	//connect_device(conn, device, FALSE);
	g_object_unref(conn);
	g_list_free_full(devices, free_device_string);
}

TEST_F(Bluez_AdapterTest, bluez_test_SppConnect){
	//g_object_unref(con);
}

TEST_F(Bluez_AdapterTest, bluez_test_SppSendFiles){
	//g_object_unref(con);
}

int main(int argc, char *argv[])
{
	::testing::AddGlobalTestEnvironment(new BluezEnvironment);
	::testing::InitGoogleMock(&argc, argv);
	return RUN_ALL_TESTS();
}

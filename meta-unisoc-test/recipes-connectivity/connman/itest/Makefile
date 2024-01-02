LIBS = -ldbus-1 -lglib-2.0 -lgio-2.0 -lgnutls -lxtables -lgtest -lgmock -lpthread -lreadline -ldl
BUILD_DIR = $(WORKDIR)/build
SYS_ROOT = $(WORKDIR)/recipe-sysroot

LIBTOOL = $(BUILD_DIR)/arm-unisoc-linux-gnueabi-libtool
ifeq (aarch64-unisoc-linux-libtool,$(notdir $(wildcard $(BUILD_DIR)/aarch64-unisoc-linux-libtool)))
	LIBTOOL = $(BUILD_DIR)/aarch64-unisoc-linux-libtool
endif

SRC_DIR = $(WORKDIR)/../../../../../../source/unisoc/connman
INCLUDE_DIRS = -I$(SYS_ROOT)/usr/include \
	-I$(SYS_ROOT)/usr/include/glib-2.0 \
	-I$(SYS_ROOT)/usr/lib/glib-2.0/include \
	-I$(SYS_ROOT)/usr/include/dbus-1.0 \
	-I$(SYS_ROOT)/usr/lib/dbus-1.0/include \
	-I$(SRC_DIR)/client \
	-I$(SRC_DIR)/gdbus \
	-I./

bin_PROGRAMS = connman_itest$(EXEEXT)

connmanitest_OBJECTS =  \
	$(BUILD_DIR)/itest/itest.o \
	$(BUILD_DIR)/itest/cmd_cb_wrap.o \
	$(BUILD_DIR)/client/dbus_helpers.o \
	$(BUILD_DIR)/client/services.o \
	$(BUILD_DIR)/client/input.o \
	$(BUILD_DIR)/client/commands.o \
	$(BUILD_DIR)/client/agent.o \
	$(BUILD_DIR)/client/peers.o \
	$(BUILD_DIR)/client/tethering.o \
	$(BUILD_DIR)/client/vpnconnections.o

connmanitest_DEPENDENCIES =  \
	$(BUILD_DIR)/gdbus/libgdbus-internal.la

# connmanitest_SOURCES = $(SRC_DIR)/client/dbus_helpers.h $(SRC_DIR)/client/dbus_helpers.c \
			# $(SRC_DIR)/client/services.h $(SRC_DIR)/client/services.c \
			# $(SRC_DIR)/client/commands.h $(SRC_DIR)/client/commands.c \
			# $(SRC_DIR)/client/input.h $(SRC_DIR)/client/input.c \
			# $(SRC_DIR)/client/agent.h $(SRC_DIR)/client/agent.c \
			# $(SRC_DIR)/client/peers.h $(SRC_DIR)/client/peers.c \
			# $(SRC_DIR)/client/tethering.h $(SRC_DIR)/client/tethering.c \
			# $(SRC_DIR)/client/vpnconnections.h $(SRC_DIR)/client/vpnconnections.c \
			# ./itest.cpp

connmanitest_LDADD = $(BUILD_DIR)/gdbus/libgdbus-internal.la

LINK = $(LIBTOOL) --mode=link $(CXX) -o $@

$(bin_PROGRAMS):$(connmanitest_OBJECTS) $(connmanitest_DEPENDENCIES) $(BUILD_DIR)/client/$(am__dirstamp)
	$(LINK) $(connmanitest_OBJECTS) $(connmanitest_LDADD) $(LIBS)

$(BUILD_DIR)/itest/itest.o:./itest.cpp
	$(CXX) $(CFLAGS) $(INCLUDE_DIRS) -c $^ -o $@

$(BUILD_DIR)/itest/cmd_cb_wrap.o:./cmd_cb_wrap.c
	$(CC) $(CFLAGS) $(INCLUDE_DIRS) -c $^ -o $@

clean:
	rm -f $(bin_PROGRAMS)


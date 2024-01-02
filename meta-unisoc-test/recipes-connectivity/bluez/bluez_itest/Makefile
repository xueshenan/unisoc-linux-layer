LIBS = -ldbus-1 -lglib-2.0 -lgio-2.0 -lgobject-2.0 -lgtest -lgmock -lpthread -lreadline -ldl

BUILD_DIR = ${WORKDIR}/build
SYS_ROOT = $(WORKDIR)/recipe-sysroot
LIBTOOL = $(BUILD_DIR)/arm-unisoc-linux-gnueabi-libtool
ifeq (aarch64-unisoc-linux-libtool,$(notdir $(wildcard $(BUILD_DIR)/aarch64-unisoc-linux-libtool)))
    LIBTOOL = $(BUILD_DIR)/aarch64-unisoc-linux-libtool
endif
SRC_DIR = $(WORKDIR)/bluez-5.48
CXXFLAGS = -fpermissive
INCLUDE_DIRS = -I$(SYS_ROOT)/usr/include \
	-I$(SYS_ROOT)/usr/include/glib-2.0 \
	-I$(SYS_ROOT)/usr/lib/glib-2.0/include \
	-I$(SYS_ROOT)/usr/include/dbus-1.0 \
	-I$(SYS_ROOT)/usr/lib/dbus-1.0/include \
	-I$(SRC_DIR)/gdbus \
	-I$(SRC_DIR)/ \


bin_PROGRAMS = bluez_itest$(EXEEXT)

bluezitest_OBJECTS =  \
	$(BUILD_DIR)/bluez_itest/bluez_gdbus.o\
	$(BUILD_DIR)/bluez_itest/bluez_itest.o \


bluezitest_DEPENDENCIES =  \
	$(BUILD_DIR)/gdbus/libgdbus-internal.la

# bluezitest_SOURCES = $(SRC_DIR)/client/dbus_helpers.h $(SRC_DIR)/client/dbus_helpers.c \
# 			$(SRC_DIR)/client/services.h $(SRC_DIR)/client/services.c \
# 			$(SRC_DIR)/client/commands.h $(SRC_DIR)/client/commands.c \
# 			$(SRC_DIR)/client/input.h $(SRC_DIR)/client/input.c \
# 			$(SRC_DIR)/client/agent.h $(SRC_DIR)/client/agent.c \
# 			$(SRC_DIR)/client/peers.h $(SRC_DIR)/client/peers.c \
# 			$(SRC_DIR)/client/tethering.h $(SRC_DIR)/client/tethering.c \
# 			$(SRC_DIR)/client/vpnconnections.h $(SRC_DIR)/client/vpnconnections.c \
#			./bluez_itest.cpp


bluezitest_LDADD = $(BUILD_DIR)/gdbus/libgdbus-internal.la $(BUILD_DIR)/src/libshared-glib.la

LINK = $(LIBTOOL) --mode=link $(CXX) -o $@

$(bin_PROGRAMS):$(bluezitest_OBJECTS) $(bluezitest_DEPENDENCIES) $(BUILD_DIR)/client/$(am__dirstamp)
	$(LINK) $(bluezitest_OBJECTS) $(bluezitest_LDADD) $(LIBS)

$(BUILD_DIR)/bluez_itest/bluez_itest.o:./bluez_itest.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(INCLUDE_DIRS) -c $^ -o $@

$(BUILD_DIR)/bluez_itest/bluez_gdbus.o:./bluez_gdbus.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(INCLUDE_DIRS) -c $^ -o $@

clean:
	rm -f $(bin_PROGRAMS)
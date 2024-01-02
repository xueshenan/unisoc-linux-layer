CXXFLAGS = -Wall -g -std=c++11
INCLUDE_DIRS=-I$(BUILDDIR)/inc -I$(BUILDDIR)/lib/atcommonlib/include -I$(BUILDDIR)/lib/cptransportlib -I$(BUILDDIR)/lib/iniparser -I$(BUILDDIR)/lib/miniapservicelib/include
LIBS = -lpthread -lrt -ldl -lstdc++ -liniparser -lgtest -latcommon -lcptransport -ldbus-1 -lat-router -lsmartlink-client
TARGET = atroutergtest

OBJS =  $(BUILDDIR)/ATIndication.o \
		$(BUILDDIR)/ATMain.o \
		$(BUILDDIR)/ATRouter.o \
		$(BUILDDIR)/ATSession.o \
		$(BUILDDIR)/CPTransport.o \
		$(BUILDDIR)/UartChannel.o \
		$(BUILDDIR)/SessionQueue.o \
		$(BUILDDIR)/ATIndicationRouter.o \
		$(BUILDDIR)/MiniAPService.o \
		$(BUILDDIR)/RouterManager.o \
		$(BUILDDIR)/PPPTransport.o \
		$(BUILDDIR)/PTYPort.o \
		$(BUILDDIR)/CMUXHandler.o \
		$(BUILDDIR)/SocketHandler.o \
		$(BUILDDIR)/FormatHandler.o \
		$(BUILDDIR)/ATRLog.o \
		$(BUILDDIR)/BaseRouter.o \
		$(BUILDDIR)/UartPort.o \
		$(BUILDDIR)/ModemManager.o \
		$(BUILDDIR)/LibatIndication.o \
		$(BUILDDIR)/CPTransChan.o \
		$(BUILDDIR)/LibatTransport.o \
		$(BUILDDIR)/lib/atcommonlib/*.o \
		$(BUILDDIR)/DATAHandler.o \
		$(BUILDDIR)/ConnmanIndication.o \
		./gtest.o

all:$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

./gtest.o:./gtest.cpp
	$(CXX) $(CXXFLAGS) $(INCLUDE_DIRS) -c $^

install:
	cp $(TARGET) $(INSTALL_MOD_PATH)/sbin

clean:
	rm -f *.o
	rm -f $(TARGET)

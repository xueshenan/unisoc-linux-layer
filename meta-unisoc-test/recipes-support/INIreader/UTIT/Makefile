CXXFLAGS = -Wall -g -O -fPIC 
INCLUDE_DIRS= -I$(BUILDDIR) -I./inc

LIBS = -lpthread -lrt -ldl -lgtest -lgmock
TARGET = libinireader_test


OBJS = $(BUILDDIR)/IniDataHandler.o \
		$(BUILDDIR)/IniFileHandler.o \
		$(BUILDDIR)/IniInterface.o \
		./iniFileReaderMock.o \
		./iniFileReaderTest.o


SRCS = $(BUILDDIR)/IniDataHandler.cpp \
		$(BUILDDIR)/IniFileHandler.cpp \
		$(BUILDDIR)/IniInterface.cpp \
		./iniFileReaderMock.cpp \
		./iniFileReaderTest.cpp

$(TARGET):$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

$(OBJS):$(SRCS)
		$(CXX) -c $^ $(CXXFLAGS) $(INCLUDE_DIRS)

install:
	cp $(TARGET) $(INSTALL_MOD_PATH)/sbin

clean:
	rm -f *.o
	rm -f $(TARGET)

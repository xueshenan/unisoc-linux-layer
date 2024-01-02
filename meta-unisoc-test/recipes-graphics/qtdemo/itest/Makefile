TARGET	= qtdemotest
all:
	$(CXX) -c qtdemo.cpp
	$(CXX) qtdemo.o -o qtdemotest -lgtest -lpthread

clean:
	rm -f *.o
	rm -f $(TARGET)
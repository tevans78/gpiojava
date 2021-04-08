PROJ=net_evansdev_gpio_jni_Native
OUT=libgpionative
GPIO=$(PROJ)GPIO
CC=g++
SRC_GPIO=${GPIO}.cpp
LIBS=-lc -lgpiodcxx
CFLAGS=-D CONSUMER=\"$(PROJ)\" -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux

all:
	$(CC) $(SRC_GPIO) $(LIBS) $(CFLAGS) -o $(GPIO).o
	$(CC) -shared -fPIC -o $(OUT).so $(GPIO).o $(LIBS)

clean:
	rm -vf $(GPIO).o $(OUT).so

install:
	cp $(OUT).so /usr/java/packages/lib/

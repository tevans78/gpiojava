#include <gpiod.hpp>
#include "net_evansdev_gpio_jni_NativeGPIO.h"

#include <map>
#include <iostream>
using namespace std;

#ifndef	CONSUMER
#define	CONSUMER	"Consumer"
#endif

gpiod::chip my_chip;

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    newNativeChip
 * Signature: ()J
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_newNativeChip
(JNIEnv *, jobject) {
	const char *chipname = "gpiochip0";
	my_chip = gpiod::chip(chipname);
	std::cout << "New chip: " << my_chip.name() << std::endl;
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    deleteNativeChip
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_deleteNativeChip
(JNIEnv *, jobject) {
	my_chip.reset();
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    newNativePin
 * Signature: ()J
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_newNativePin
(JNIEnv *env, jobject thisObject, jint line_num) {
	gpiod::line my_line = my_chip.get_line(line_num);
	//std::cout << "Got line" << std::endl;
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    deleteNativePin
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_deleteNativePin
(JNIEnv *env, jobject thisObject, jint line_num) {
	gpiod::line my_line = my_chip.get_line(line_num);
	my_line.reset();
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    request
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_request
(JNIEnv* env, jobject thisObject, jint line_num) {
	gpiod::line my_line = my_chip.get_line(line_num);
	my_line.request( {
				CONSUMER,
				::gpiod::line_request::DIRECTION_OUTPUT,
				0
			});
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    release
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_release
(JNIEnv* env, jobject thisObject, jint line_num) {
	gpiod::line my_line = my_chip.get_line(line_num);
	my_line.release();
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    setDirection
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_setDirection
(JNIEnv* env, jobject thisObject, jint line_num, jint dir) {
	gpiod::line my_line = my_chip.get_line(line_num);
	if(dir==1) {
		my_line.set_direction_input();
		//std::cout << "Line: " << line_num << " Set Direction: Input" << std::endl;
	}
	else {
		my_line.set_direction_output();
		//std::cout << "Line: " << line_num << " Set Direction: Output" << std::endl;
	}

}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    getDirection
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_getDirection(
		JNIEnv *env, jobject thisObject, jint line_num) {
	gpiod::line my_line = my_chip.get_line(line_num);
	return my_line.direction();
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    setLevel
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_setLevel
(JNIEnv* env, jobject thisObject, jint line_num, jint level) {
	gpiod::line my_line = my_chip.get_line(line_num);
	my_line.set_value(level);
	//std::cout << "Line: " << line_num << " Set Value: " << level << std::endl;
}

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    getLevel
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_getLevel(
		JNIEnv *env, jobject thisObject, jint line_num) {
	gpiod::line my_line = my_chip.get_line(line_num);
	return my_line.get_value();
}


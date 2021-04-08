/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class net_evansdev_gpio_jni_NativeGPIO */

#ifndef _Included_net_evansdev_gpio_jni_NativeGPIO
#define _Included_net_evansdev_gpio_jni_NativeGPIO
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    newNativeChip
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_newNativeChip
  (JNIEnv *, jobject);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    deleteNativeChip
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_deleteNativeChip
  (JNIEnv *, jobject);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    newNativePin
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_newNativePin
  (JNIEnv *, jobject, jint);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    deleteNativePin
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_deleteNativePin
  (JNIEnv *, jobject, jint);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    request
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_request
  (JNIEnv *, jobject, jint);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    release
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_release
  (JNIEnv *, jobject, jint);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    setDirection
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_setDirection
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    getDirection
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_getDirection
  (JNIEnv *, jobject, jint);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    setLevel
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_setLevel
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     net_evansdev_gpio_jni_NativeGPIO
 * Method:    getLevel
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_net_evansdev_gpio_jni_NativeGPIO_getLevel
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
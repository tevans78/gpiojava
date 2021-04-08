package net.evansdev.gpio.jni;

import java.io.IOException;

import net.evansdev.gpio.AbstractGPIO;
import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.Pin;

public class NativeGPIO extends AbstractGPIO implements GPIO {

    static {
        System.loadLibrary("gpionative");
    }

    private boolean closed = false;

    /**
     * Set desirable pin for the GPIO class.
     */
    public NativeGPIO() {
        System.out.println("Native GPIO");
        newNativeChip();
    }

    /**
     * Disable access to GPIO.
     * 
     * @param pin GPIO pin to disable access.
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (!closed) {
            this.closed = true;
            closePins();
            deleteNativeChip();
        }
    }

    @Override
    public Pin newPin(int pin) {
        NativePin nativePin = new NativePin(this, pin);
        return nativePin;
    }

    native void newNativeChip();

    native void deleteNativeChip();

    native void newNativePin(int pin);

    native void deleteNativePin(int pin);

    native void request(int pin);

    native void release(int pin);

    native void setDirection(int pin, int direction);

    native int getDirection(int pin);

    native void setLevel(int pin, int level);

    native int getLevel(int pin);
}
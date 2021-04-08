package net.evansdev.gpio;

import java.io.Closeable;

public interface GPIO extends Closeable {

    /**
     * Get function of specific pin.
     * 
     * @param pin Desirable pin.
     */
    Pin getPin(int pin);

    Pin newPin(int pinNumber);

}
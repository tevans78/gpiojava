package net.evansdev.gpio;

import java.io.Closeable;

import net.evansdev.gpio.AbstractPin.Direction;
import net.evansdev.gpio.AbstractPin.Level;

public interface Pin extends Closeable {

    /**
     * Get pin direction.
     * 
     * @param pin Desirable pin.
     * @returns Direction of pin in -> Input. out -> Output.
     */
    Direction getDirection();

    /**
     * Get pin value.
     * 
     * @param pin Desirable pin.
     * @returns Value of pin 0 -> Low Level. 1 -> High Level
     */
    Level getLevel();

    /**
     * Set pin as high.
     */
    void high();

    /**
     * Set pin as low.
     */
    void low();

    /**
     * Set pin as output.
     */
    void out();

    /**
     * Set pin as input.
     * 
     * @param pin - Desirable pin.
     */
    void in();

}
package net.evansdev.gpio.jni;

import net.evansdev.gpio.AbstractPin;
import net.evansdev.gpio.Pin;

public class NativePin extends AbstractPin implements Pin {

    private boolean closed = false;
    private NativeGPIO gpio;

    /**
     * Set desirable pin for the GPIO class.
     * 
     * @param nativeGPIO
     */
    public NativePin(NativeGPIO gpio, int pin) {
        super(pin);
        gpio.newNativePin(pin);
        this.gpio = gpio;
        this.gpio.request(pin);
    }

    /**
     * Disable access to GPIO.
     * 
     * @param pin GPIO pin to disable access.
     */
    @Override
    public void close() {
        if (!closed) {
            this.closed = true;
            gpio.release(getPinNumber());
            gpio.deleteNativePin(getPinNumber());
        }
    }

    /**
     * Set pin direction.
     * 
     * @param pin Desirable pin.
     * @param pin Direction of pin. in -> Input. out -> Output.
     * 
     */
    @Override
    protected void setDirection(Direction direction) {
        gpio.setDirection(getPinNumber(), direction.ordinal());
    }

    /**
     * Get pin direction.
     * 
     * @param pin Desirable pin.
     * @returns Direction of pin in -> Input. out -> Output.
     */
    @Override
    public Direction getDirection() {
        int dir = gpio.getDirection(getPinNumber());
        Direction direction = Direction.valueOfInt(dir);
        return direction;
    }

    /**
     * Set pin value.
     * 
     * @param pin   Desirable pin.
     * @param value Value of pin. 0 -> Low Level. 1 -> High Level
     */
    @Override
    protected void setLevel(Level level) {
        gpio.setLevel(getPinNumber(), level.ordinal());
    }

    /**
     * Get pin value.
     * 
     * @param pin Desirable pin.
     * @returns Value of pin 0 -> Low Level. 1 -> High Level
     */
    @Override
    public Level getLevel() {
        int level = gpio.getLevel(getPinNumber());
        return Level.valueOfInt(level);
    }
}
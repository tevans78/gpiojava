package net.evansdev.gpio;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGPIO implements GPIO {

    private final Map<Integer, Pin> pinCache = new HashMap<Integer, Pin>();

    /**
     * Get function of specific pin.
     * 
     * @param pinNumber The GPIO Pin number.
     */
    @Override
    public Pin getPin(int pinNumber) {
        Pin pin = pinCache.get(pinNumber);
        if (pin == null) {
            pin = newPin(pinNumber);
            pinCache.putIfAbsent(pinNumber, pin);
        }

        return pin;
    }

    public void closePins() throws IOException {
        for (Pin pin : pinCache.values()) {
            pin.close();
        }
        pinCache.clear();
    }
}
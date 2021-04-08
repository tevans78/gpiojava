package net.evansdev.gpio.file;

import java.io.IOException;

import net.evansdev.gpio.AbstractGPIO;
import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.Pin;

public class FileGPIO extends AbstractGPIO implements GPIO {

    private boolean closed = false;

    @Override
    public Pin newPin(int pinNumber) {
        return new FilePin(pinNumber);
    }

    @Override
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            closePins();
        }
    }

}
package net.evansdev.gpio.modules;

import java.io.Closeable;
import java.io.IOException;

import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.Pin;

public class LED implements Closeable {

    private Pin gpioPin;

    public LED(GPIO gpio) {
        gpioPin = gpio.getPin(4);
        gpioPin.out();
    }

    public void on() {
        gpioPin.high();
    }

    public void off() {
        gpioPin.low();
    }

    @Override
    public void close() throws IOException {
        gpioPin.close();
    }

}
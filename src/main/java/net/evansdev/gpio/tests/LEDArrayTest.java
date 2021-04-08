package net.evansdev.gpio.tests;

import java.io.Closeable;
import java.io.IOException;

import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.file.FileGPIO;
import net.evansdev.gpio.modules.Button;
import net.evansdev.gpio.modules.ButtonListener;
import net.evansdev.gpio.modules.LEDArray;

public class LEDArrayTest implements ButtonListener, Closeable {
    private LEDArray led;
    private Button button;
    private int speed = 0;
    private GPIO gpio;

    public LEDArrayTest() {
        gpio = new FileGPIO();
        led = new LEDArray(gpio);
        button = new Button(gpio, 27);

        button.addListener(this);

        Thread ledThread = new Thread(led);
        ledThread.start();
    }

    @Override
    public void released() {
        this.speed++;
        if (this.speed == 11) {
            this.speed = 0;
        }
        System.out.println("Setting speed to " + this.speed);
        led.setSpeed(this.speed);
    }

    @Override
    public void close() throws IOException {
        button.close();
        led.close();
        gpio.close();
    }

}
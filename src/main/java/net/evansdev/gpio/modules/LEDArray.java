package net.evansdev.gpio.modules;

import java.io.Closeable;

import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.Pin;

public class LEDArray implements Runnable, Closeable {

    private volatile boolean run = true;
    private int speed = 0;
    private int count = 0;
    private int dir = 1;

    private Pin[] gpioPin = new Pin[10];

    public LEDArray(GPIO gpio) {
        gpioPin[0] = gpio.getPin(4);
        gpioPin[1] = gpio.getPin(5);
        gpioPin[2] = gpio.getPin(6);
        gpioPin[3] = gpio.getPin(12);
        gpioPin[4] = gpio.getPin(13);
        gpioPin[5] = gpio.getPin(16);
        gpioPin[6] = gpio.getPin(17);
        gpioPin[7] = gpio.getPin(18);
        gpioPin[8] = gpio.getPin(19);
        gpioPin[9] = gpio.getPin(20);

        for (int i = 0; i < 10; i++) {
            gpioPin[i].out();
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void close() {
        run = false;
    }

    private void sleep(int speed) {
        if (speed > 0) {
            try {
                Thread.sleep(500 / speed);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (run) {
            if (this.speed > 0) {
                for (int i = 0; i < 10; i++) {
                    if (run) {
                        transition();
                        sleep(this.speed);
                    }
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private void transition() {
        count = count + dir;
        if (count == 11 || count == 0) {
            dir = dir * -1;
            count = count + dir;
        }
        for (int i = 0; i < 10; i++) {
            if (run) {
                if (i == (count - 1)) {
                    gpioPin[i].high();
                } else {
                    gpioPin[i].low();
                }
            }
        }
    }

}
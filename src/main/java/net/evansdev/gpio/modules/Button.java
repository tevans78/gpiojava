package net.evansdev.gpio.modules;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.evansdev.gpio.AbstractPin.Level;
import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.Pin;
import net.evansdev.gpio.Utils;

public class Button implements Runnable, Closeable {
    private volatile boolean run = true;
    private boolean pressed = false;

    private Pin gpioPinIn;
    private List<ButtonListener> listeners = Collections.synchronizedList(new ArrayList<>());
    private Thread buttonThread;

    public Button(GPIO gpio, int pinNumber) {
        gpioPinIn = gpio.getPin(pinNumber);
        gpioPinIn.in();

    }

    public void addListener(ButtonListener listener) {
        this.listeners.add(listener);
        if (buttonThread == null) {
            buttonThread = new Thread(this);
            buttonThread.start();
        }
    }

    @Override
    public void run() {
        while (run) {

            Level button = gpioPinIn.getLevel();
            // System.out.println("Button Get Level: " + button);
            if (button == Level.HIGH) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (!run) {
                    return;
                }
                button = gpioPinIn.getLevel();
                if (button == Level.HIGH) {
                    pressed();
                }
            } else {
                if (pressed) {
                    released();
                }
            }

            Utils.sleep(5);
        }
    }

    private void pressed() {
        System.out.println("Button Pressed");
        pressed = true;
    }

    private void released() {
        System.out.println("Button Released");
        pressed = false;
        // notify listeners
        listeners.forEach((a) -> a.released());
    }

    @Override
    public void close() {
        run = false;
    }

}
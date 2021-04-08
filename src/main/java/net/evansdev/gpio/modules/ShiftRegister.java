package net.evansdev.gpio.modules;

import java.io.Closeable;

import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.Pin;
import net.evansdev.gpio.Utils;

public class ShiftRegister implements Closeable {

    private static final int INPUT_PIN = 4;
    private static final int SHIFT_PIN = 5;
    private static final int LATCH_PIN = 21;

    private Pin input;
    private Pin shift;
    private Pin latch;
    private volatile boolean closed = false;

    public ShiftRegister(GPIO gpio) {
        input = gpio.getPin(INPUT_PIN);
        shift = gpio.getPin(SHIFT_PIN);
        latch = gpio.getPin(LATCH_PIN);
        input.out();
        shift.out();
        latch.out();
        input.low();
        shift.low();
        latch.low();
        Utils.sleep(100);
    }

    public void setData(int data) {
        for (int i = 7; i >= 0; i--) {
            if (closed)
                return;
            int value = data & Bit.get(i).value();
            setValue(value != Bit.get(i).value());
            shift();
        }
    }

    public void setValue(boolean high) {
        // System.out.print(high ? "1" : "0");
        if (high) {
            input.high();
        } else {
            input.low();
        }
        // Utils.sleep(100);
    }

    public void shift() {
        shift.high();
        // Utils.sleep(100);
        shift.low();
    }

    public void latch() {
        // System.out.println();
        latch.high();
        // Utils.sleep(100);
        latch.low();
    }

    @Override
    public void close() {
        this.closed = true;
    }

}

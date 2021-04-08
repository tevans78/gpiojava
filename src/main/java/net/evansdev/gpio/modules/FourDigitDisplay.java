package net.evansdev.gpio.modules;

import java.io.Closeable;

import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.Pin;

public class FourDigitDisplay implements Closeable {

    private static final int DIGIT_PIN0 = 23;
    private static final int DIGIT_PIN1 = 24;
    private static final int DIGIT_PIN2 = 25;
    private static final int DIGIT_PIN3 = 26;

    private Pin[] digits = new Pin[4];
    private ShiftRegister shiftRegister;
    private volatile boolean closed = false;
    private Thread loop;

    public FourDigitDisplay(GPIO gpio) {
        shiftRegister = new ShiftRegister(gpio);
        digits[0] = gpio.getPin(DIGIT_PIN0);
        digits[1] = gpio.getPin(DIGIT_PIN1);
        digits[2] = gpio.getPin(DIGIT_PIN2);
        digits[3] = gpio.getPin(DIGIT_PIN3);
        digits[0].out();
        digits[1].out();
        digits[2].out();
        digits[3].out();
        digits[0].high();
        digits[1].high();
        digits[2].high();
        digits[3].high();
        loop = new Thread(new DisplayUpdateLoop());
        loop.start();
    }

    @Override
    public void close() {
        closed = true;
        try {
            loop.join(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        shiftRegister.close();
    }

    private volatile Digit[] values = { Number.BLANK, Number.BLANK, Number.BLANK, Number.BLANK };

    public void setDisplay(Digit[] values) {
        System.arraycopy(values, 0, this.values, 0, 4);
    }

    public void setDisplay(Digit one, Digit two, Digit three, Digit four) {
        values[0] = one;
        values[1] = two;
        values[2] = three;
        values[3] = four;
    }

    private class DisplayUpdateLoop implements Runnable {
        @Override
        public void run() {
            while (!closed) {
                setDigit(0, values[0]);
                setDigit(1, values[1]);
                setDigit(2, values[2]);
                setDigit(3, values[3]);
                // Utils.sleep(500);
            }
        }
    }

    private void selectDigit(int digit) {
        for (int i = 0; i < 4; i++) {
            if (i == digit) {
                digits[i].low();
            } else {
                digits[i].high();
            }
        }
    }

    private void deselectDigit() {
        for (int i = 0; i < 4; i++) {
            digits[i].high();
        }
    }

    public void setDigit(int digit, Digit number) {
        setDigit(digit, number.value());
    }

    public void setDigit(int digit, int data) {
        shiftRegister.setData(data);
        deselectDigit();
        shiftRegister.latch();
        selectDigit(digit);
    }
}

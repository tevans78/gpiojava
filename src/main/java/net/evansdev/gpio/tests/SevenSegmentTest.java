package net.evansdev.gpio.tests;

import java.io.Closeable;
import java.io.IOException;

import net.evansdev.gpio.Utils;
import net.evansdev.gpio.modules.Button;
import net.evansdev.gpio.modules.ButtonListener;
import net.evansdev.gpio.modules.Digit;
import net.evansdev.gpio.modules.FourDigitDisplay;
import net.evansdev.gpio.modules.Number;

public class SevenSegmentTest extends AbstractTest implements Closeable, ButtonListener {

    public static final long INTERVAL = 100; // 0.1 seconds
    public static final long MAX = 9999;
    private FourDigitDisplay display;
    private Thread timer;
    private volatile boolean closed;
    private Button button;
    private long start;

    public SevenSegmentTest(boolean file) {
        super(file);
        button = new Button(getGPIO(), 20);
        button.addListener(this);
        display = new FourDigitDisplay(getGPIO());
        timer = new Thread(new Timer());
    }

    public void test() throws InterruptedException {
        timer.start();
    }

    @Override
    public void close() throws IOException {
        if (!closed()) {
            this.closed = true;
            try {
                timer.join(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            display.close();
            super.close();
        }
    }

    public class Timer implements Runnable {
        private volatile Digit[] values = { Digit.BLANK, Digit.BLANK, Digit.BLANK, Digit.BLANK };

        @Override
        public void run() {
            start = System.currentTimeMillis();
            while (!closed) {
                long current = System.currentTimeMillis();
                long elapsed = (current - start) / INTERVAL;

                if (elapsed > MAX) {
                    start = System.currentTimeMillis();
                    current = System.currentTimeMillis();
                    elapsed = (current - start) / INTERVAL;
                }

                System.out.println("Elapsed = " + elapsed);
                for (int i = 0; i < 4; i++) {
                    if (closed)
                        return;

                    int n = (int) Math.pow(10, 3 - i);
                    int value = (int) (elapsed / n);

                    Digit number = Digit.BLANK;
                    if (value == 0) {
                        boolean blank = true;
                        for (int j = i; j >= 0; j--) {
                            blank = blank & (values[j] == Digit.BLANK);
                        }
                        if (!blank) {
                            number = Number.ZERO;
                        }
                    } else {
                        number = Number.get(value);
                    }
                    if (i == 2) {
                        number = number.withDP();
                    }
                    values[i] = number;
                    // System.out.print(values[i] + " ");
                    elapsed = elapsed - (value * n);
                }
                // System.out.println("");

                Utils.sleep(INTERVAL / 2);

                display.setDisplay(values);
            }
        }
    }

    @Override
    public void released() {
        this.start = System.currentTimeMillis();
    }
}

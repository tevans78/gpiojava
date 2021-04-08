package net.evansdev.gpio.tests;

import java.io.Closeable;
import java.io.IOException;

import net.evansdev.gpio.Utils;
import net.evansdev.gpio.modules.LED;

public class LEDTest extends AbstractTest implements Closeable {
    private LED led;
    private volatile boolean closed = false;
    private Thread testThread;

    public LEDTest(boolean file) {
        super(file);
        led = new LED(getGPIO());
        testThread = new Thread(new Loop());
    }

    public void test() {
        testThread.start();
    }

    public class Loop implements Runnable {

        @Override
        public void run() {
            boolean on = false;
            while (!closed) {
                if (on) {
                    led.off();
                    on = false;
                } else {
                    led.on();
                    on = true;
                }
                Utils.sleep(200);
            }
        }

    }

    @Override
    public void close() throws IOException {
        if (!closed) {
            this.closed = true;
            try {
                testThread.join(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            led.close();
            super.close();
        }
    }

}
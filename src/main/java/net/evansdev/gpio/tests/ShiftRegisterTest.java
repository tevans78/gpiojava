package net.evansdev.gpio.tests;

import java.io.Closeable;
import java.io.IOException;

import net.evansdev.gpio.modules.ShiftRegister;

public class ShiftRegisterTest extends AbstractTest implements Closeable {

    public static final long INTERVAL = 1000; // 1 seconds
    public static final long MAX = 9;
    private ShiftRegister reg;
    private Thread timer;
    private volatile boolean closed;

    public ShiftRegisterTest(boolean file) {
        super(file);

        reg = new ShiftRegister(getGPIO());
        // timer = new Thread(new Timer2());
    }

    public void test() {
        next();
    }

    private int x = 0;

    public void next() {
        for (int i = 0; i < 8; i++) {
            reg.setValue(i == x);
            reg.shift();
        }
        reg.latch();
        x++;
        if (x >= 8) {
            x = 0;
        }
    }

    @Override
    public void close() throws IOException {
        if (!closed()) {
            this.closed = true;
//            try {
//                timer.join(2000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            reg.close();
            super.close();
        }
    }
}

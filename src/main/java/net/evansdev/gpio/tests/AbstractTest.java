package net.evansdev.gpio.tests;

import java.io.Closeable;
import java.io.IOException;

import net.evansdev.gpio.GPIO;
import net.evansdev.gpio.file.FileGPIO;
import net.evansdev.gpio.jni.NativeGPIO;

public class AbstractTest implements Closeable {

    private GPIO gpio;
    private volatile boolean closed;

    public AbstractTest(boolean file) {
        if (file) {
            this.gpio = new FileGPIO();
        } else {
            this.gpio = new NativeGPIO();
        }
    }

    public GPIO getGPIO() {
        return gpio;
    }

    public boolean closed() {
        return this.closed;
    }

    @Override
    public void close() throws IOException {
        if (!closed) {
            closed = true;
            gpio.close();
        }
    }
}

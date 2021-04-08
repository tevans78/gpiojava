package net.evansdev.gpio.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.evansdev.gpio.AbstractPin;
import net.evansdev.gpio.Pin;

public class FilePin extends AbstractPin implements Pin {
    private static final String PATH = "/sys/class/gpio";
    private static final String EXPORT_PATH = PATH + "/export";
    private static final String UNEXPORT_PATH = PATH + "/unexport";
    private static final String GPIO_PATH = PATH + "/gpio";
    private static final String DIRECTION_SUB_PATH = "/direction";
    private static final String VALUE_SUB_PATH = "/value";
    private boolean closed = false;

    /**
     * Set desirable pin for the GPIO class.
     */
    public FilePin(int pin) {
        super(pin);
        System.out.println("Initializing File Pin " + pin);
        exportPin();
    }

    private void write(String path, String value) {
        // System.out.println("Writing to: " + path + " = " + value);
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, false); // t
            out = new BufferedWriter(fstream);
            out.write(value);
            out.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private String read(String path) {
        BufferedReader br;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return line;
    }

    /**
     * Enable access to GPIO.
     * 
     * @param pin GPIO pin to access.
     */
    private void exportPin() {
        // System.out.println("Exporting Pin");
        write(EXPORT_PATH, String.valueOf(getPinNumber()));
    }

    /**
     * Disable access to GPIO.
     * 
     * @param pin GPIO pin to disable access.
     */
    private void unexport() {
        // System.out.println("unExporting Pin");
        write(UNEXPORT_PATH, String.valueOf(getPinNumber()));
    }

    /**
     * Disable access to GPIO.
     * 
     * @param pin GPIO pin to disable access.
     */
    @Override
    public void close() {
        if (!closed) {
            unexport();
            this.closed = true;
        }
    }

    private String getPinPath() {
        return GPIO_PATH + getPinNumber();
    }

    private String getDirectionPath() {
        return getPinPath() + DIRECTION_SUB_PATH;
    }

    private String getValuePath() {
        return getPinPath() + VALUE_SUB_PATH;
    }

    /**
     * Set pin direction.
     * 
     * @param pin Desirable pin.
     * @param pin Direction of pin. in -> Input. out -> Output.
     * 
     */
    @Override
    protected void setDirection(Direction direction) {
        // System.out.println("Setting Direction");
        write(getDirectionPath(), direction.value());
    }

    /**
     * Set pin value.
     * 
     * @param pin   Desirable pin.
     * @param value Value of pin. 0 -> Low Level. 1 -> High Level
     */
    @Override
    protected void setLevel(Level level) {
        // System.out.println("Setting Value");
        write(getValuePath(), level.value());
    }

    /**
     * Get pin direction.
     * 
     * @param pin Desirable pin.
     * @returns Direction of pin in -> Input. out -> Output.
     */
    @Override
    public Direction getDirection() {
        String line = read(getDirectionPath());
        Direction direction = Direction.valueOf(line.toUpperCase());
        return direction;
    }

    /**
     * Get pin value.
     * 
     * @param pin Desirable pin.
     * @returns Value of pin 0 -> Low Level. 1 -> High Level
     */
    @Override
    public Level getLevel() {
        String line = read(getValuePath());
        return Level.valueOfIntString(line);
    }

}
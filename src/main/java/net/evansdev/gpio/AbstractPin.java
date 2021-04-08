package net.evansdev.gpio;

public abstract class AbstractPin implements Pin {

    public enum Level {
        LOW, HIGH;

        public String value() {
            return String.valueOf(this.ordinal());
        }

        public static Level valueOfIntString(String value) {
            if ("1".equals(value)) {
                return HIGH;
            } else {
                return LOW;
            }
        }

        public static Level valueOfInt(int value) {
            if (1 == value) {
                return HIGH;
            } else {
                return LOW;
            }
        }
    }

    public enum Direction {
        OUT, IN;

        public String value() {
            return this.name().toLowerCase();
        }

        public static Direction valueOfInt(int value) {
            if (1 == value) {
                return IN;
            } else {
                return OUT;
            }
        }
    };

    private final int pin;

    /**
     * Set desirable pin for the GPIO class.
     */
    public AbstractPin(int pin) {
        this.pin = pin;
    }

    protected int getPinNumber() {
        return this.pin;
    }

    protected abstract void setLevel(Level level);

    protected abstract void setDirection(Direction direction);

    /**
     * Set pin as high.
     */
    @Override
    public void high() {
        setLevel(Level.HIGH);
    }

    /**
     * Set pin as low.
     */
    @Override
    public void low() {
        setLevel(Level.LOW);
    }

    /**
     * Set pin as output.
     */
    @Override
    public void out() {
        setDirection(Direction.OUT);
    }

    /**
     * Set pin as input.
     * 
     * @param pin - Desirable pin.
     */
    @Override
    public void in() {
        setDirection(Direction.IN);
    }
}
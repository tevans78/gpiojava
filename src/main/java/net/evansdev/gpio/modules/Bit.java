package net.evansdev.gpio.modules;

public enum Bit {

    ZERO(1 << 7), ONE(1 << 6), TWO(1 << 5), THREE(1 << 4), FOUR(1 << 3), FIVE(1 << 2), SIX(1 << 1), SEVEN(1);

    private int value;

    Bit(int value) {
        this.value = value;
    }

    public static Bit get(int ordinal) {
        switch (ordinal) {
        case 0:
            return ZERO;
        case 1:
            return ONE;
        case 2:
            return TWO;
        case 3:
            return THREE;
        case 4:
            return FOUR;
        case 5:
            return FIVE;
        case 6:
            return SIX;
        case 7:
            return SEVEN;
        }

        throw new IllegalArgumentException("Unknown ordinal: " + ordinal);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toBinaryString(value);
    }
}

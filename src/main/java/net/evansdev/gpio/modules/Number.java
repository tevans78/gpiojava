package net.evansdev.gpio.modules;

public class Number extends Digit {

    public static final Number ZERO = new Number("0", A, B, C, D, E, F);
    public static final Number ONE = new Number("1", B, C);
    public static final Number TWO = new Number("2", A, B, D, E, G);
    public static final Number THREE = new Number("3", A, B, C, D, G);
    public static final Number FOUR = new Number("4", B, C, F, G);
    public static final Number FIVE = new Number("5", A, C, D, F, G);
    public static final Number SIX = new Number("6", A, C, D, E, F, G);
    public static final Number SEVEN = new Number("7", A, B, C);
    public static final Number EIGHT = new Number("8", A, B, C, D, E, F, G);
    public static final Number NINE = new Number("9", A, B, C, F, G);

    private Number(String name, Segment... segments) {
        super(name, segments);
    }

    public static Number get(int ordinal) {
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
        case 8:
            return EIGHT;
        case 9:
            return NINE;
        }

        throw new IllegalArgumentException("Unknown ordinal: " + ordinal);
    }
}

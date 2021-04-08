package net.evansdev.gpio.modules;

public class Digit {

    public static final Segment A = Segment.A;
    public static final Segment B = Segment.B;
    public static final Segment C = Segment.C;
    public static final Segment D = Segment.D;
    public static final Segment E = Segment.E;
    public static final Segment F = Segment.F;
    public static final Segment G = Segment.G;
    public static final Segment DP = Segment.DP;

    public static final Digit DASH = new Digit("-", G);
    public static final Digit BLANK = new Digit("X");

    private int value;
    private String name;

    public Digit(String name, Segment... segments) {
        this(name, or(segments));
    }

    public Digit(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static int or(Segment... segments) {
        int value = 0;
        for (Segment segment : segments) {
            value = value | segment.value();
        }
        return value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Digit withDP() {
        return new Digit(this.name, this.value | DP.value());
    }
}

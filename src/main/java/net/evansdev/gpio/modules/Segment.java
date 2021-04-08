package net.evansdev.gpio.modules;

public enum Segment {

    A(Bit.ZERO), B(Bit.ONE), C(Bit.TWO), D(Bit.THREE), E(Bit.FOUR), F(Bit.FIVE), G(Bit.SIX), DP(Bit.SEVEN);

    private Bit value;

    Segment(Bit value) {
        this.value = value;
    }

    public int value() {
        return value.value();
    }
}

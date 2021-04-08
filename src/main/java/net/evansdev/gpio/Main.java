package net.evansdev.gpio;

import java.io.IOException;
import java.util.Scanner;

import net.evansdev.gpio.tests.SevenSegmentTest;

public class Main {

    public Main() throws IOException, InterruptedException {

//        LEDTest test = new LEDTest(false);
//        test.test();
        SevenSegmentTest test = new SevenSegmentTest(false);
        test.test();
//        ShiftRegisterTest test = new ShiftRegisterTest(true);
//        test.test();

        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            if ("x".equals(line.trim())) {
                break;
            }
        }
        in.close();

        test.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Main();
    }
}

package org.example;

public class Demo {

    private static final Demo DEMO = new Demo();

    private Demo() {
    }
    public static Demo getDemo() {
        return DEMO;
    }
}

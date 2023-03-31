package org.example;

public class Demo {


    private static final Demo INSTANCE = new Demo();

    private Demo() {

    }

    public static Demo getInstance() {
        return INSTANCE;
    }
}

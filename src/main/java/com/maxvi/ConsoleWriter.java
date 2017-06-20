package com.maxvi;

public final class ConsoleWriter {
    private static ConsoleWriter mInstance;

    private ConsoleWriter() {}

    public static synchronized ConsoleWriter getInstance() {
        if (mInstance == null) {
            mInstance = new ConsoleWriter();
        }
        return mInstance;
    }

    public void write(final String message) {
        System.out.println(message);
    }
}

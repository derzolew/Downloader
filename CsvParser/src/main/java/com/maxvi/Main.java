package com.maxvi;

import com.maxvi.core.CsvFacade;

public final class Main {

    public static void main(final String[] args) {
        final CsvFacade facade = new CsvFacade(args);
        facade.getResult();
    }
}
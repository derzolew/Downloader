package com.maxvi;

import com.maxvi.core.CsvFacade;

public class Main {

    public static void main(final String[] args) {
        CsvFacade facade = new CsvFacade(args);
        facade.makeSearch();
    }
}
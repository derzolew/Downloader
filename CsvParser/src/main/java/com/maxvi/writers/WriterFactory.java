package com.maxvi.writers;

import com.maxvi.Constants;

public final class WriterFactory {

    public static IWritable getWriter(final String fileType) {
        switch (fileType) {
            case Constants.FILE_EXT_CSV:
                return new CsvWriter();
            case Constants.FILE_EXT_TXT:
                return new TxtWriter();
            default:
                return null;
        }
    }
}
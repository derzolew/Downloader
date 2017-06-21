package com.maxvi.parsers;

import com.maxvi.Constants;

public final class ParserFactory {

    public static IParsable getParser(final String fileType) {
        switch (fileType) {
            case Constants.FILE_EXT_JSON:
                return new JsonParser();
            case Constants.FILE_EXT_CSV:
                return new CsvParser();
            case Constants.FILE_EXT_XML:
                return new XmlParser();
            default:
                return null;
        }
    }
}

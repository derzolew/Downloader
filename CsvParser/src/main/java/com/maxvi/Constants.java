package com.maxvi;

public final class Constants {

    public static final String ARG_START = "-";
    public static final String ARG_SOURCE = "-i";
    public static final String ARG_QUERY = "-q";
    public static final String ARG_OUTPUT = "-o";

    public static final String FILE_EXT_TXT = "txt";
    public static final String FILE_EXT_CSV = "csv";

    public static final String ERR_FILE_TYPE = "File type is not supported. Use csv or txt";
    public static final String ERR_NOT_CSV = "File type is not supported. Use csv";
    public static final String ERR_COMMON = "ERROR: Check your arguments. Example:\n" +
            "java -jar CsvParser-1.0.jar -i input.csv -q one -o output.csv";
    public static final String ERR_LINES_CSV = "ERROR: Check your input csv file. Lines length may be different";

    public static final String SUCCESS_MESSAGE = "Operation successfully done!";
}

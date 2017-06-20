package com.maxvi;

public final class Constants {
    public static final String ARG_START = "-";
    public static final String ARG_LINK = "-l";
    public static final String ARG_PATH = "-p";
    public static final String ARG_FILE = "-f";
    public static final String ARG_FILE_NAME = "-n";

    public static final String FILE_EXT_JSON = "json";
    public static final String FILE_EXT_XML = "xml";
    public static final String FILE_EXT_CSV = "csv";

    private static final String ERROR_MSG = "ERROR: ";
    public static final String ERROR_URL = ERROR_MSG + "URL format";
    public static final String ERROR_IO = ERROR_MSG + "Can't download";
    public static final String ERROR_SECURITY = ERROR_MSG + " No folder access";
    public static final String ERROR_SPECIFY_URL = ERROR_MSG + "You have to specify URL with '-l' argument";
    public static final String ERROR_ARGS = ERROR_MSG + "Check your arguments";
    public static final String ERROR_FILE_NOT_FOUND = ERROR_MSG + "File not found";

    public static final String SUCCESS_MESSAGE = "Files downloaded successfully!";
}

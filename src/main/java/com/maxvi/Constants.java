package com.maxvi;

public final class Constants {
    public static final String ARG_START = "-";
    public static final String ARG_LINK = "-l";
    public static final String ARG_PATH = "-p";
    public static final String ARG_FILE_NAME = "-n";

    private static final String ERROR_MSG = "ERROR: ";
    public static final String ERROR_URL = ERROR_MSG + "URL format";
    public static final String ERROR_IO = ERROR_MSG + "Can't download";
    public static final String ERROR_SECURITY = ERROR_MSG + " No folder access";
    public static final String ERROR_SPECIFY_URL = ERROR_MSG + "You have to specify URL with '-l' argument";

    public static final String SUCCESS_MESSAGE = "Files downloaded successfully!";
}

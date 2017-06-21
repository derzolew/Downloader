package com.maxvi.utilities;

import java.io.IOException;

public final class FileUtil {

    public static String getFileExtension(final String fileName) throws IOException {
        String extension = "";

        final int dotLastIndex = fileName.lastIndexOf('.');
        final int slashLastIndex = Math.max(fileName.lastIndexOf('/'),
                fileName.lastIndexOf('\\'));

        if (dotLastIndex > slashLastIndex) {
            extension = fileName.substring(dotLastIndex + 1);
        }
        return extension;
    }
}

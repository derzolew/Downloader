package com.maxvi.utilities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtil {

    public static String readAllFile(final String filename, final Charset pCharset) {
        try {
            final byte[] encoded = Files.readAllBytes(Paths.get(filename));
            return new String(encoded, pCharset);
        } catch (final IOException pE) {
            return null;
        }
    }

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

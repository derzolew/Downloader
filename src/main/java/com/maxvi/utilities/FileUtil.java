package com.maxvi.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileUtil {

    private final String mFilePath;
    private static final String REGEX = ".{1,255}[ ]{1}.{1,255}";

    public FileUtil(final String pFilePath) {
        mFilePath = pFilePath;
    }

    public Map<String, String> readFileToMap() throws FileNotFoundException {
        Map<String, String> linksMap = null;
        try {
            final Scanner scanner = new Scanner(new FileReader(mFilePath));
            linksMap = new HashMap<>();
            while (scanner.hasNext()) {
                linksMap.put(scanner.next(), scanner.next());
            }
        } catch (final NoSuchElementException pE) {
            System.out.println("No suck element exception");
        }
        return linksMap;
    }

    public static String readAllFile(final String filename, final Charset pCharset) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(filename));
        return new String(encoded, pCharset);
    }

    public static String getFileExtension(final String fileName) {
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

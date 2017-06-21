package com.maxvi.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TxtWriter implements IWritable {

    @Override
    public void write(final List<String[]> lines, final String path) throws IOException {
        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String[] line : lines) {
            for (final String word : line) {
                stringBuilder.append(word);
                stringBuilder.append(System.lineSeparator());
            }
        }
        bufferedWriter.write(stringBuilder.toString());
        bufferedWriter.close();
    }
}

package com.maxvi.writers;

import java.io.IOException;
import java.util.List;

public interface IWritable {

    void write(List<String[]> lines, String path) throws IOException;
}

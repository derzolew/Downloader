package com.maxvi.parsers;

import java.io.FileNotFoundException;
import java.util.List;

public interface IParsable {

    List<String[]> parseFile(String filePath) throws FileNotFoundException;
}

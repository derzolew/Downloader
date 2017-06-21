package com.maxvi.parsers;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements IParsable {

    @Override
    public List<String[]> parseFile(final String filePath) throws FileNotFoundException {
        final CSVReader csvReader;
        csvReader = new CSVReader(new FileReader(filePath));
        String[] line;
        final List<String[]> lineList = new ArrayList<>();
        try {
            while ((line = csvReader.readNext()) != null) {
                lineList.add(line);
            }
        } catch (final IOException pE) {
            pE.printStackTrace();
        }
        return lineList;
    }
}

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
        if (checkIfAllLinesEqual(lineList)) {
            return lineList;
        }
        return null;
    }

    private boolean checkIfAllLinesEqual(final List<String[]> pLineList) {
        boolean flag = true;
        for (final String[] line : pLineList) {
            if (line.length != pLineList.get(0).length) {
                flag = false;
            }
        }
        return flag;
    }
}

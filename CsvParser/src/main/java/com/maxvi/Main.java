package com.maxvi;

import com.maxvi.parsers.CsvParser;
import com.maxvi.parsers.IParsable;
import com.maxvi.utilities.ArgsUtil;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(final String[] args) {
        String path = "X:\\Projects\\git\\CsvParser\\CsvParser\\src\\main\\resources\\input.csv";


        Map<String, List<String>> argsMap = ArgsUtil.parseArgs(args);
        IParsable parser = new CsvParser();
        try {
            List<String[]> lineList = parser.parseFile(path);
            ColumnSearcher columnSearcher = new ColumnSearcher();
            columnSearcher.searchColumns(lineList, "one");
        } catch (FileNotFoundException pE) {
            pE.printStackTrace();
        }
        System.out.println("test change");
    }
}

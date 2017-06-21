package com.maxvi.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OutputDataProvider {

    private final List<String[]> outputList;

    public OutputDataProvider() {
        outputList = new ArrayList<>();
    }

    public void makeOutputList(final Collection<String[]> rawList, final Iterable<Integer> necessaryColumns) {
        for (final Integer columnNumber : necessaryColumns) {
            final String[] outLine = new String[rawList.size()];
            int i = 0;
            for (final String[] line : rawList) {
                outLine[i] = line[columnNumber];
                i++;
            }
            outputList.add(outLine);
        }
    }

    public List<String[]> getOutputList() {
        return outputList;
    }
}

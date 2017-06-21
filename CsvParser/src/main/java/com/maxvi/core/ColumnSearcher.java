package com.maxvi.core;

import java.util.ArrayList;
import java.util.List;

public class ColumnSearcher {

    private final List<Integer> mColumnsNums;

    public ColumnSearcher() {
        mColumnsNums = new ArrayList<>();
    }

    public void searchColumns(final Iterable<String[]> pLineList, final String pQuery) {
        for (final String[] line : pLineList) {
            for (int j = 0; j < line.length; j++) {
                if ((line[j].equals(pQuery) || line[j].matches(pQuery)) && !mColumnsNums.contains(j)) {
                    mColumnsNums.add(j);
                }
            }
        }
    }

    public List<Integer> getColumnsNums() {
        return mColumnsNums;
    }
}

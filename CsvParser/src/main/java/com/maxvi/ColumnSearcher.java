package com.maxvi;

import java.util.ArrayList;
import java.util.List;

public class ColumnSearcher {
    private List<Integer> mColumnsNums;

    public ColumnSearcher() {
        mColumnsNums = new ArrayList<>();
    }

    public void searchColumns(final List<String[]> pLineList, final String pQuery) {
        for (int i = 0; i < pLineList.size(); i++) {
            final String[] line = pLineList.get(i);

            for (int j = 0; j < line.length; j++) {
                if ((line[j].equals(pQuery) || line[j].matches(pQuery)) && !mColumnsNums.contains(j)) {
                    mColumnsNums.add(j);
                }
            }
        }
        System.out.println(mColumnsNums);
    }
}

package com.maxvi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadManager {
    private List<Thread> mThreadList;
    private final int mThreadsNumber;

    public ThreadManager(final int pThreadsNumber) {
        mThreadsNumber = pThreadsNumber;
        mThreadList = new ArrayList<>();
    }

    public void divideDownloadsByThreads(final Map<String, String> linksMap) {
        int linksForOneThread = linksMap.size() / mThreadsNumber;
        for (int i = 0; i < mThreadsNumber; i++) {
            Map<String, String> bufferLinks = new HashMap<>();
            for (int j = 0; j < linksForOneThread; j++) {
                bufferLinks.entrySet().add(linksMap.entrySet().iterator().next());
            }

        }
    }
}

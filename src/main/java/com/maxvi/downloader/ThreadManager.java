package com.maxvi;

import com.maxvi.models.DownloadObject;
import com.maxvi.models.DownloadObjectArray;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {

    private final List<Thread> mThreadList;
    private final int mThreadsNumber;

    public ThreadManager(final int pThreadsNumber) {
        mThreadsNumber = pThreadsNumber;
        mThreadList = new ArrayList<>();
    }

    public void divideDownloadsByThreads(final DownloadObjectArray pDownloadObjectArray) {
        final DownloadObject[] downloadObjects = pDownloadObjectArray.getLinksArray();
        final int downloadObjectsNumber = downloadObjects.length;
        final int chunkSize = (downloadObjectsNumber + mThreadsNumber - 1) / mThreadsNumber;
        if (mThreadsNumber <= downloadObjects.length) {
            for (int i = 0; i < mThreadsNumber; i++) {
                final int beginIndex = chunkSize * i;
                final int endIndex = Math.min(beginIndex + chunkSize, downloadObjectsNumber);
                final Thread thread = new Thread(() ->
                        downloadOperation(pDownloadObjectArray, beginIndex, endIndex));
                mThreadList.add(thread);
            }

        }
        startThreads();

    }

    public void startThreads() {
        for (final Thread thread : mThreadList) {
            thread.start();
        }
    }

    private void downloadOperation(final DownloadObjectArray pDownloadObjectArray,
                                   final int pBeginIndex,
                                   final int pEndIndex) {
        final DownloadObject[] downloadObjects = pDownloadObjectArray.getLinksArray();
        for (int i = pBeginIndex; i < pEndIndex; i++) {
            System.out.println(downloadObjects[i].getName() + " " + downloadObjects[i].getUrl());
        }

    }
}

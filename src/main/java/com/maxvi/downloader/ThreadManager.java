package com.maxvi.downloader;

import com.maxvi.ConsoleWriter;
import com.maxvi.models.DownloadObject;
import com.maxvi.models.DownloadObjectArray;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {

    private final List<Thread> mThreadList;
    private int mThreadsNumber;
    private ConsoleWriter mWriter;

    public ThreadManager(final int pThreadsNumber) {
        mThreadsNumber = pThreadsNumber;
        mWriter = ConsoleWriter.getInstance();
        mThreadList = new ArrayList<>();
    }

    public void divideDownloadsByThreads(final DownloadObjectArray pDownloadObjectArray) {
        final DownloadObject[] downloadObjects = pDownloadObjectArray.getLinksArray();
        final int downloadObjectsNumber = downloadObjects.length;
        if (mThreadsNumber > downloadObjectsNumber) {
            mThreadsNumber = downloadObjectsNumber;
        }
        final int chunkSize = (downloadObjectsNumber + mThreadsNumber - 1) / mThreadsNumber;
        initThreads(pDownloadObjectArray, downloadObjectsNumber, chunkSize);
        startThreads();
    }

    private void initThreads(final DownloadObjectArray pDownloadObjectArray,
                             final int pDownloadObjectsNumber,
                             final int pChunkSize) {
        for (int i = 0; i < mThreadsNumber; i++) {
            final int beginIndex = pChunkSize * i;
            final int endIndex = Math.min(beginIndex + pChunkSize, pDownloadObjectsNumber);
            final Thread thread = new Thread(() ->
                    downloadOperation(pDownloadObjectArray, beginIndex, endIndex));
            mThreadList.add(thread);
        }
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
            mWriter.write(pBeginIndex + " " + DownloaderFacade.download(downloadObjects[i].getUrl(),
                    downloadObjects[i].getName()));
        }
    }
}

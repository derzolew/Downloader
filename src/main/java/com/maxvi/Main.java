package com.maxvi;

import com.maxvi.downloader.DownloaderFacade;

public class Main {

    public static void main(final String[] args) {
        final DownloaderFacade facade = new DownloaderFacade(args);
        facade.requestDownload();

    }
}

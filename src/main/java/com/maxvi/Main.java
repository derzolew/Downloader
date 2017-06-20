package com.maxvi;

public class Main {

    public static void main(final String[] args) {
        final DownloaderFacade facade = new DownloaderFacade(args);
        facade.requestDownload();
    }
}

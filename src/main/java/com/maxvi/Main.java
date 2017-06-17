package com.maxvi;

public class Main {


    public static void main(final String[] args) {
        DownloaderFacade downloaderFacade = new DownloaderFacade(args);
        System.out.println(downloaderFacade.download());

    }
}

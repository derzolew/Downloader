package com.maxvi.downloader;

import com.maxvi.ConsoleWriter;
import com.maxvi.Constants;
import com.maxvi.models.DownloadObjectArray;
import com.maxvi.parsers.IParsable;
import com.maxvi.parsers.ParserFactory;
import com.maxvi.utilities.ArgsUtil;
import com.maxvi.utilities.FileUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

public class DownloaderFacade {

    private final ConsoleWriter mWriter;
    private IParsable mParser;
    private ThreadManager mThreadManager;
    private static Map<String, List<String>> mArgsMap;
    private final String[] mArgs;

    public DownloaderFacade(final String[] pArgs) {
        mArgs = pArgs;
        mWriter = ConsoleWriter.getInstance();
    }

    private void makeArgsMap(final String[] args) {
        mArgsMap = ArgsUtil.parseArgs(args);
    }

    public void requestDownload() {
        makeArgsMap(mArgs);
        if (ArgsUtil.checkIfFromFile(mArgsMap)) {
            if (mArgsMap.containsKey(Constants.ARG_THREADS)) {
                try {
                    final int numOfThreads = Integer.parseInt(mArgsMap.get(Constants.ARG_THREADS).get(0));
                    mThreadManager = new ThreadManager(numOfThreads);
                    downloadFromFile();
                } catch (final NumberFormatException pE) {
                    mWriter.write(Constants.ERROR_ARGS_FORMAT);
                } catch (final IndexOutOfBoundsException pE) {
                    mWriter.write(Constants.ERROR_ARGS_NOT_SPECIFIED + Constants.ARG_THREADS);
                }
            } else {
                final byte numOfThreads = 1;
                mThreadManager = new ThreadManager(numOfThreads);
                downloadFromFile();
            }

        } else if (ArgsUtil.checkIfFromLink(mArgsMap)) {
            downloadFromLink();
        } else {
            mWriter.write(Constants.ERROR_ARGS);
        }
    }

    private void downloadFromFile() {
        try {
            final String filePath = mArgsMap.get(Constants.ARG_FILE).get(0);
            final String fileExtension = FileUtil.getFileExtension(filePath);
            mParser = ParserFactory.getParser(fileExtension);
            if (mParser != null) {
                final DownloadObjectArray downloadObjectArray = mParser.parseFile(filePath);
                if (downloadObjectArray != null) {
                    mThreadManager.divideDownloadsByThreads(downloadObjectArray);
                } else {
                    mWriter.write(Constants.ERROR_PARSING);
                }
            } else {
                mWriter.write(Constants.ERROR_ARGS + Constants.ARG_FILE);
            }
        } catch (final IndexOutOfBoundsException pE) {
            mWriter.write(Constants.ERROR_ARGS_NOT_SPECIFIED + Constants.ARG_FILE);
        } catch (final IOException pE) {
            mWriter.write(Constants.ERROR_ARGS);
        }
    }

    private void downloadFromLink() {
        try {
            mWriter.write(download(mArgsMap.get(Constants.ARG_LINK).get(0),
                    mArgsMap.get(Constants.ARG_FILE_NAME).get(0)));
        } catch (final IndexOutOfBoundsException pE) {
            mWriter.write(Constants.ERROR_ARGS_NOT_SPECIFIED);
        }
    }

    static String download(final String pLink, final String pName) {
        try {
            final URL url = new URL(pLink);
            final File file = new File(mArgsMap.get(Constants.ARG_PATH).get(0));
            if (!file.exists()) {
                file.mkdirs();
            }
            final Path path = new File(file + File.separator + pName).toPath();
            Files.copy(url.openStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (final MalformedURLException pE) {
            return Constants.ERROR_URL + pLink;
        } catch (SecurityException pE) {
            return Constants.ERROR_SECURITY;
        } catch (final IOException pE) {
            return Constants.ERROR_IO + pLink;
        } catch (final IndexOutOfBoundsException pE) {
            return Constants.ERROR_ARGS_NOT_SPECIFIED;
        }
        return Constants.SUCCESS_MESSAGE + pLink;
    }
}

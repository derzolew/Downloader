package com.maxvi;

import com.maxvi.models.DownloadObject;
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

    private IParsable mParser;
    private Map<String, List<String>> mArgsMap;
    private final String[] mArgs;

    public DownloaderFacade(final String[] pArgs) {
        mArgs = pArgs;
    }

    private void getArgsMap(final String[] args) {
        mArgsMap = ArgsUtil.parseArgs(args);
    }

    public void requestDownload() {
        getArgsMap(mArgs);
        if (ArgsUtil.checkIfFromFile(mArgsMap)) {
            downloadFromFile();
        } else if (ArgsUtil.checkIfFromLink(mArgsMap)) {
            downloadFromLink();
        }
    }

    private void downloadFromFile() {
        final String filePath = mArgsMap.get(Constants.ARG_FILE).get(0);
        final String fileExtension = FileUtil.getFileExtension(filePath);
        mParser = ParserFactory.getParser(fileExtension);
        if (mParser != null) {
            final DownloadObjectArray downloadObjectArray = mParser.parseFile(filePath);
            final String path = mArgsMap.get(Constants.ARG_PATH).get(0);
            for (final DownloadObject downloadObject : downloadObjectArray.getLinksArray()) {
                download(downloadObject.getUrl(), path, downloadObject.getName());
            }
        }
    }

    private void downloadFromLink() {
        download(mArgsMap.get(Constants.ARG_LINK).get(0), mArgsMap.get(Constants.ARG_PATH).get(0),
                mArgsMap.get(Constants.ARG_FILE_NAME).get(0));
    }

    private String download(final String pLink, final String pPath, final String pName) {
        try {
            final URL url = new URL(pLink);
            final Path path = new File(pPath + "\\" + pName).toPath();
            Files.copy(url.openStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (final MalformedURLException pE) {
            return Constants.ERROR_URL + pLink;
        } catch (final IOException pE) {
            return Constants.ERROR_IO + pLink;
        }
        return Constants.SUCCESS_MESSAGE + pLink;
    }
}

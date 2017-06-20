package com.maxvi;

import com.maxvi.utilities.ArgsUtil;
import com.maxvi.utilities.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DownloaderFacade {

    private final ArgsUtil mArgsUtil;
    private final String[] mArgs;

    public DownloaderFacade(final String[] pArgs) {
        mArgs = pArgs.clone();
        mArgsUtil = new ArgsUtil();
    }

    private boolean checkIfFromLink(final Map<String, List<String>> pArgsMap) {
        return pArgsMap.containsKey(Constants.ARG_LINK) && pArgsMap.containsKey(Constants.ARG_PATH)
                && pArgsMap.containsKey(Constants.ARG_FILE_NAME)
                && !pArgsMap.containsKey(Constants.ARG_FILE);
    }

    private boolean checkIfFromFile(final Map<String, List<String>> pArgsMap) {
        return pArgsMap.containsKey(Constants.ARG_FILE) && pArgsMap.containsKey(Constants.ARG_PATH)
                && !pArgsMap.containsKey(Constants.ARG_LINK)
                && !pArgsMap.containsKey(Constants.ARG_FILE_NAME);
    }

    public String requestDownload() {
        final Map<String, List<String>> argsMap = mArgsUtil.parseArgs(mArgs);
        System.out.println(argsMap);
        final String result;
        if (checkIfFromLink(argsMap)) {
            result = downloadFromLink(argsMap);
        } else if (checkIfFromFile(argsMap)) {
            result = downloadFromFile(argsMap);
        } else {
            result = Constants.ERROR_ARGS;
        }
        return result;
    }

    private String downloadFromFile(final Map<String, List<String>> pArgsMap) {
        if (!pArgsMap.get(Constants.ARG_FILE).isEmpty()) {
            final String filePath = pArgsMap.get(Constants.ARG_FILE).get(0);
            final FileUtil fileUtil = new FileUtil(filePath);
            final Map<String, String> urlMap;
            try {
                urlMap = fileUtil.readFileToMap();
                for (final Map.Entry<String, String> entry : urlMap.entrySet()) {
                    try {
                        final URL url = new URL(entry.getKey());
                        final String fileName = entry.getValue();
                        final Path targetPath = new File(pArgsMap.get(Constants.ARG_PATH).get(0) + "\\" + fileName).toPath();
                        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (final MalformedURLException pE) {
                        return Constants.ERROR_URL;
                    } catch (final SecurityException pE) {
                        return Constants.ERROR_SECURITY;
                    } catch (final IOException pE) {
                        return Constants.ERROR_IO;
                    }
                }
            } catch (final FileNotFoundException pE) {
                return Constants.ERROR_FILE_NOT_FOUND;
            }

        }
        return Constants.SUCCESS_MESSAGE;
    }

    private String downloadFromLink(final Map<String, List<String>> pArgsMap) {
        try {
            if (!pArgsMap.get(Constants.ARG_LINK).isEmpty() &&
                    !pArgsMap.get(Constants.ARG_PATH).isEmpty() &&
                    !pArgsMap.get(Constants.ARG_FILE_NAME).isEmpty()) {
                final String urlStr = pArgsMap.get(Constants.ARG_LINK).get(0);
                final URL url = new URL(urlStr);
                final String fileName = pArgsMap.get(Constants.ARG_FILE_NAME).get(0);
                final Path targetPath = new File(pArgsMap.get(Constants.ARG_PATH).get(0) + "\\" + fileName).toPath();
                Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                return Constants.ERROR_ARGS;
            }
        } catch (final MalformedURLException pE) {
            return Constants.ERROR_URL;
        } catch (final IOException pE) {
            return Constants.ERROR_IO;
        }
        return Constants.SUCCESS_MESSAGE;
    }

    private List<URL> getURLList(final Collection<String> urlEntry) throws MalformedURLException {
        List<URL> urlList = null;
        if (!urlEntry.isEmpty()) {
            urlList = new ArrayList<>();
            for (final String urlString : urlEntry) {
                urlList.add(new URL(urlString));
            }
        }
        return urlList;
    }
}

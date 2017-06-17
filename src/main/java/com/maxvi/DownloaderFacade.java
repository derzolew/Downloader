package com.maxvi;

import java.io.File;
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

    private ArgsUtil mArgsUtil;
    private String[] mArgs;

    public DownloaderFacade(final String[] pArgs) {
        mArgs = pArgs.clone();
        mArgsUtil = new ArgsUtil();
    }

    public String download() {
        final Map<String, List<String>> argsMap = mArgsUtil.parseArgs(mArgs);
        try {
            List<URL> urlList;
            if (argsMap.containsKey(Constants.ARG_LINK)) {
                urlList = getURLList(argsMap.get(Constants.ARG_LINK));
            } else {
                return Constants.ERROR_SPECIFY_URL;
            }

            int i = 0;
            for (final URL url : urlList) {
                final String fileName = i + argsMap.get(Constants.ARG_FILE_NAME).get(0);
                i++;
                final Path targetPath = new File(argsMap.get(Constants.ARG_PATH).get(0) + "\\" + fileName).toPath();
                Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (final MalformedURLException pE) {
            return Constants.ERROR_URL;
        } catch (final IOException pE) {
            return Constants.ERROR_IO;
        } catch (final SecurityException pE) {
            return Constants.ERROR_SECURITY;
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

package com.maxvi.parsers;

import com.maxvi.models.DownloadObjectArray;

public interface IParsable {

    DownloadObjectArray parseFile(final String filePath);
}

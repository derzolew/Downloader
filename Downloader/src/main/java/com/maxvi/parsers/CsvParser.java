package com.maxvi.parsers;

import com.maxvi.models.DownloadObject;
import com.maxvi.models.DownloadObjectArray;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements IParsable {

    @Override
    public DownloadObjectArray parseFile(final String filePath) {
        final CSVReader csvReader;
        DownloadObjectArray downloadObjectArray = null;
        try {
            csvReader = new CSVReader(new FileReader(filePath));
            String[] line;
            final List<DownloadObject> downloadObjectList = new ArrayList<>();
            while ((line = csvReader.readNext()) != null) {
                final DownloadObject downloadObject = new DownloadObject();
                downloadObject.setUrl(line[0]);
                downloadObject.setName(line[1]);
                downloadObjectList.add(downloadObject);
            }
            downloadObjectArray = convertFromListToArray(downloadObjectList);
        } catch (final IOException pE) {
            pE.printStackTrace();
        }

        return downloadObjectArray;
    }

    private DownloadObjectArray convertFromListToArray(final List<DownloadObject> pDownloadObjectList) {
        final DownloadObject[] downloadObjects = new DownloadObject[pDownloadObjectList.size()];
        for (int i = 0; i < pDownloadObjectList.size(); i++) {
            downloadObjects[i] = pDownloadObjectList.get(i);
        }
        final DownloadObjectArray downloadObjectArray = new DownloadObjectArray();
        downloadObjectArray.setLinksArray(downloadObjects);
        return downloadObjectArray;
    }
}

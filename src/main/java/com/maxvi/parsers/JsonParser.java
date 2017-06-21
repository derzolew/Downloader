package com.maxvi.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.maxvi.models.DownloadObjectArray;
import com.maxvi.utilities.FileUtil;

import java.nio.charset.StandardCharsets;

public class JsonParser implements IParsable {

    @Override
    public DownloadObjectArray parseFile(final String filePath) {
        final String rawFile = FileUtil.readAllFile(filePath, StandardCharsets.UTF_8);
        try {
            final Gson gson = new GsonBuilder().create();
            return gson.fromJson(rawFile, DownloadObjectArray.class);
        } catch (final JsonSyntaxException pE) {
            return null;
        }
    }
}

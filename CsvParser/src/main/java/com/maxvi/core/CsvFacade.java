package com.maxvi.core;

import com.maxvi.Constants;
import com.maxvi.parsers.CsvParser;
import com.maxvi.parsers.IParsable;
import com.maxvi.utilities.ArgsUtil;
import com.maxvi.utilities.FileUtil;
import com.maxvi.writers.IWritable;
import com.maxvi.writers.WriterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CsvFacade {

    private ColumnSearcher mColumnSearcher;
    private OutputDataProvider mOutputDataProvider;
    private IParsable mParser;
    private IWritable mFileWriter;
    private Map<String, List<String>> mArgs;

    public CsvFacade(final String[] args) {
        makeArgs(args);
    }

    private void makeArgs(final String[] args) {
        mArgs = ArgsUtil.parseArgs(args);
    }

    public void getResult() {
        try {
            final String filePath = mArgs.get(Constants.ARG_SOURCE).get(0);
            List<String[]> linesToOutput = getLinesToOutput(filePath);
            for (String[] line : linesToOutput) {
                System.out.println(Arrays.toString(line));
            }
            String outputPath = mArgs.get(Constants.ARG_OUTPUT).get(0);
            mFileWriter = WriterFactory.getWriter(FileUtil.getFileExtension(outputPath));
            mFileWriter.write(linesToOutput, outputPath);
        } catch (final IndexOutOfBoundsException pE) {
            System.out.println("index out of bound");
        } catch (final IOException pE) {
            System.out.println("IO exception");
        } catch (final NullPointerException pE) {
            System.out.println("Check args");
        }
    }

    private List<String[]> getLinesToOutput(final String pFilePath) throws IOException {
        if (FileUtil.getFileExtension(pFilePath).equals(Constants.FILE_EXT_CSV)) {
            mParser = new CsvParser();
            final List<String[]> rawLineList = mParser.parseFile(pFilePath);
            if (rawLineList != null) {
                mColumnSearcher = new ColumnSearcher();
                final String query = mArgs.get(Constants.ARG_QUERY).get(0);
                mColumnSearcher.searchColumns(rawLineList, query);
                mOutputDataProvider = new OutputDataProvider();
                mOutputDataProvider.makeOutputList(rawLineList, mColumnSearcher.getColumnsNums());
                return mOutputDataProvider.getOutputList();
            }
        } else {
            System.out.println("Format has to be csv");
        }
        return null;
    }

}

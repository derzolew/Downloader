package com.maxvi.core;

import com.maxvi.ConsoleWriter;
import com.maxvi.Constants;
import com.maxvi.parsers.CsvParser;
import com.maxvi.parsers.IParsable;
import com.maxvi.utilities.ArgsUtil;
import com.maxvi.utilities.FileUtil;
import com.maxvi.writers.IWritable;
import com.maxvi.writers.WriterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CsvFacade {

    private ColumnSearcher mColumnSearcher;
    private OutputDataProvider mOutputDataProvider;
    private IParsable mParser;
    private IWritable mFileWriter;
    private Map<String, List<String>> mArgs;
    private ConsoleWriter mConsoleWriter;

    public CsvFacade(final String[] args) {
        makeArgs(args);
        mConsoleWriter = ConsoleWriter.getInstance();
    }

    private void makeArgs(final String[] args) {
        mArgs = ArgsUtil.parseArgs(args);
    }

    public void getResult() {
        try {
            final String filePath = mArgs.get(Constants.ARG_SOURCE).get(0);
            final List<String[]> linesToOutput = getLinesToOutput(filePath);

            final String outputPath = mArgs.get(Constants.ARG_OUTPUT).get(0);
            mFileWriter = WriterFactory.getWriter(FileUtil.getFileExtension(outputPath));
            if (mFileWriter != null) {
                mFileWriter.write(linesToOutput, outputPath);
                mConsoleWriter.write(Constants.SUCCESS_MESSAGE);
            } else {
                mConsoleWriter.write(Constants.ERR_FILE_TYPE);
            }
        } catch (final IndexOutOfBoundsException | NullPointerException | IOException pE) {
            mConsoleWriter.write(Constants.ERR_COMMON);
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
            } else {
                mConsoleWriter.write(Constants.ERR_LINES_CSV);
            }
        } else {
            mConsoleWriter.write(Constants.ERR_NOT_CSV);
        }
        return null;
    }

}

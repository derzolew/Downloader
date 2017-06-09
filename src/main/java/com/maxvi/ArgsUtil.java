package com.maxvi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsUtil {

    private static final String ARG_START = "-";
    private static final String ARG_LINK = "-l";
    private static final String ARG_PATH = "-p";
    private static final String ARG_FILE_NAME = "-n";

    private final Map<String, List<String>> argsMap = new HashMap<>();

    public void parseArgs(final String[] args) {
        List<String> argValues = null;
        for (final String arg : args) {
            if ((arg.equals(ARG_LINK) || arg.equals(ARG_PATH) || arg.equals(ARG_FILE_NAME))) {
                argValues = new ArrayList<>();
                argsMap.put(arg, argValues);
            } else {
                if (argValues != null && !arg.startsWith(ARG_START)) {
                    argValues.add(arg);
                }
            }

        }
    }

    public Map<String, List<String>> getArgsMap() {
        return argsMap;
    }
}

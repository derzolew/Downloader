package com.maxvi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsUtil {

    public Map<String, List<String>> parseArgs(final String[] args) {
        final Map<String, List<String>> argsMap = new HashMap<>();
        List<String> argValues = null;
        for (final String arg : args) {
            if ((arg.equals(Constants.ARG_LINK) ||
                    arg.equals(Constants.ARG_PATH) ||
                    arg.equals(Constants.ARG_FILE_NAME))) {
                argValues = new ArrayList<>();
                argsMap.put(arg, argValues);
            } else {
                if (argValues != null && !arg.startsWith(Constants.ARG_START)) {
                    argValues.add(arg);
                }
            }

        }
        return argsMap;
    }

}
